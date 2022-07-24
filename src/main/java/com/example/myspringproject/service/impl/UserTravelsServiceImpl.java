package com.example.myspringproject.service.impl;

import com.example.myspringproject.dto.UserTravelsDto;
import com.example.myspringproject.entity.StoreEnterances;
import com.example.myspringproject.entity.Stores;
import com.example.myspringproject.entity.UserTravelData;
import com.example.myspringproject.model.UserModel;
import com.example.myspringproject.entity.UserTravels;
import com.example.myspringproject.repo.StoreEnterancesRepository;
import com.example.myspringproject.repo.UserTravelDataRepository;
import com.example.myspringproject.repo.UserTravelsRepository;
import com.example.myspringproject.service.IUserTravelsService;
import com.example.myspringproject.shared.DistanceCalculator;
import com.example.myspringproject.shared.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class UserTravelsServiceImpl implements IUserTravelsService, Serializable {

    private final UserTravelsRepository userRepository;
    private final StoreEnterancesRepository storeEnterancesRepository;
    private final UserTravelDataRepository userTravelDataRepository;
    private static final String USERS_PREFIX = "C_USERS:";
    @Autowired
    private RedisTemplate redisTemplate;

    private AtomicReference<String> storeName = new AtomicReference<>("");
    private int storeId;

    @Override
    @Transactional
    public UserTravelsDto save(UserTravelsDto userTravelsDto) throws ParseException {

        HashOperations hashOperations = redisTemplate.opsForHash();
        int courierId = userTravelsDto.getCourierId();

        String dateStop = userTravelsDto.getTime();
        String dateStart = (String) hashOperations.get(USERS_PREFIX + courierId, UserModel.U_LAST_TIME);

        if (dateStart == null) {
            dateStart = dateStop;
        }

        long diffMinutes = Utils.getDiffMinutes(dateStart, dateStop);
        AtomicInteger distance = calculateDistance(userTravelsDto);

        if (distance.get() < 100 && diffMinutes >= 1) {
            saveStoreEnternace(userTravelsDto);
        }

        saveTotalTravel(userTravelsDto, hashOperations, courierId);
        final UserTravels userDb = saveAndGetUserTravels(userTravelsDto);

        userTravelsDto.setId(userDb.getId());

        return userTravelsDto;
    }

    private void saveTotalTravel(UserTravelsDto userTravelsDto, HashOperations hashOperations, int courierId) {
        double total;
        Double lastLat = (Double) hashOperations.get(USERS_PREFIX + courierId, UserModel.U_LAST_LAT);
        Double lastLng = (Double) hashOperations.get(USERS_PREFIX + courierId, UserModel.U_LAST_LNG);

        if (lastLat != null && lastLng != null) {
            total = DistanceCalculator.distFrom(userTravelsDto.getLat(), userTravelsDto.getLng(), lastLat, lastLng);
            total = total / 1000;

            UserTravelData travelData = userTravelDataRepository.findByCourierId(courierId);

            if (Objects.isNull(travelData)) {
                UserTravelData userTravelData = new UserTravelData();
                userTravelData.setCourierId(userTravelsDto.getCourierId());
                userTravelData.setTotalTravel((long) total);

                userTravelDataRepository.save(userTravelData);
            } else {
                total = travelData.getTotalTravel() + total;
                userTravelDataRepository.setTotalTravelByCourierId((long) total, courierId);
            }
        }
    }

    private UserTravels saveAndGetUserTravels(UserTravelsDto userTravelsDto) {

        UserTravels userTravels = new UserTravels();
        userTravels.setCourierId(userTravelsDto.getCourierId());
        userTravels.setLat(userTravelsDto.getLat());
        userTravels.setLng(userTravelsDto.getLng());
        userTravels.setTime(userTravelsDto.getTime());

        //Saving to Redis begin
        HashOperations hashOperations = redisTemplate.opsForHash();

        hashOperations.put(USERS_PREFIX + userTravelsDto.getCourierId(), UserModel.U_LAST_LAT, userTravelsDto.getLat());
        hashOperations.put(USERS_PREFIX + userTravelsDto.getCourierId(), UserModel.U_LAST_LNG, userTravelsDto.getLng());
        hashOperations.put(USERS_PREFIX + userTravelsDto.getCourierId(), UserModel.U_LAST_TIME, userTravelsDto.getTime());
        //Saving to Redis end

        //Saving to DB begin
        final UserTravels userTravelsFromDb = userRepository.save(userTravels);
        //Saving to DB end

        return userTravelsFromDb;
    }

    private void saveStoreEnternace(UserTravelsDto userTravelsDto) {
        StoreEnterances storeEnterances = new StoreEnterances();
        storeEnterances.setStoreName(String.valueOf(storeName));
        storeEnterances.setStoreId(storeId);
        storeEnterances.setCourierId(userTravelsDto.getCourierId());
        storeEnterances.setTime(userTravelsDto.getTime());

        storeEnterancesRepository.save(storeEnterances);
    }

    private AtomicInteger calculateDistance(UserTravelsDto userTravelsDto) {

        AtomicInteger distance = new AtomicInteger();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Stores>> typeReference = new TypeReference<List<Stores>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/stores.json");

        try {
            List<Stores> stores = mapper.readValue(inputStream, typeReference);
            stores.forEach(item -> {
                if (item.getId() == userTravelsDto.getStoreId()) {
                    distance.set((int) DistanceCalculator
                            .distFrom(userTravelsDto.getLat(), userTravelsDto.getLng(), item.getLat(), item.getLng()));
                    storeName.set(item.getName());
                    storeId = userTravelsDto.getStoreId();
                }
            });
        } catch (IOException e) {
            System.out.println("Unable to list stores: " + e.getMessage());
        }
        return distance;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<UserTravelsDto> getAll() {
        List<UserTravels> users = userRepository.findAll();
        List<UserTravelsDto> userTravelsDtos = new ArrayList<>();

        users.forEach(item -> {
            UserTravelsDto userTravelsDto = new UserTravelsDto();
            userTravelsDto.setId(item.getId());
            userTravelsDto.setCourierId(item.getCourierId());
            userTravelsDto.setLat(item.getLat());
            userTravelsDto.setLng(item.getLng());
            userTravelsDto.setTime(item.getTime());

            userTravelsDtos.add(userTravelsDto);
        });

        return userTravelsDtos;
    }

    @Override
    public Page<UserTravelsDto> getAll(Pageable pageable) {
        return null;
    }
}
