package com.example.myspringproject.service.store;

import com.example.myspringproject.dto.store.StoreEnterancesDto;
import com.example.myspringproject.dto.user.UserTravelsDto;
import com.example.myspringproject.entity.store.StoreEnterances;
import com.example.myspringproject.repository.store.StoreEnteranceRepository;
import com.example.myspringproject.shared.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreEnteranceService implements IStoreEnteranceService {

    private final StoreEnteranceRepository storeEnteranceRepository;

    @Override
    @Transactional
    public StoreEnterancesDto save(StoreEnterancesDto storeEnterancesDto, UserTravelsDto userTravelsDto) {

        int distance = getDistance(userTravelsDto);

        if(distance < 100){
            StoreEnterances storeEnterances = new StoreEnterances();
            storeEnterances.setCourierId(userTravelsDto.getCourierId());
            storeEnterances.setTime(userTravelsDto.getTime());

            storeEnteranceRepository.save(storeEnterances);
        }

        return storeEnterancesDto;
    }

    private int getDistance(UserTravelsDto userTravelsDto) {
        int distance = (int)DistanceCalculator.distance(userTravelsDto.getLat(), userTravelsDto.getLng(), 40.9923307, 29.1244229, "K");
        return distance;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<StoreEnterancesDto> getAll() {
        List<StoreEnterances> storeEnterances = storeEnteranceRepository.findAll();
        List<StoreEnterancesDto> storeEnterancesDtos = new ArrayList<>();

        storeEnterances.forEach(item -> {
            StoreEnterancesDto storeEnterancesDto = new StoreEnterancesDto();
            storeEnterancesDto.setId(item.getId());
            storeEnterancesDto.setCourierId(item.getCourierId());
            storeEnterancesDto.setTime(item.getTime());
            storeEnterancesDto.setStoreId(item.getStoreId());
            storeEnterancesDto.setStoreName(item.getStoreName());

            storeEnterancesDtos.add(storeEnterancesDto);
        });

        return storeEnterancesDtos;
    }

    @Override
    public Page<StoreEnterancesDto> getAll(Pageable pageable) {
        return null;
    }
}
