package com.example.myspringproject.service.user;

import com.example.myspringproject.dto.user.UserTravelDataDto;
import com.example.myspringproject.entity.user.UserTravelData;
import com.example.myspringproject.repository.user.UserTravelDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserTravelDataService implements IUserTravelDataService {
    private final UserTravelDataRepository userTravelDataRepository;
    @Override
    public UserTravelDataDto save(UserTravelDataDto userTravelDataDto) throws IOException {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<UserTravelData> findByCourierId(int courierId) {
        return Optional.ofNullable(userTravelDataRepository.findByCourierId(courierId));
    }

    @Override
    public List<UserTravelDataDto> getAll() {
        List<UserTravelData> data = userTravelDataRepository.findAll();
        List<UserTravelDataDto> userTravelDataDtos = new ArrayList<>();

        data.forEach(item -> {
            UserTravelDataDto userTravelDataDto = new UserTravelDataDto();
            userTravelDataDto.setId(item.getId());
            userTravelDataDto.setCourierId(item.getCourierId());
            userTravelDataDto.setTotalTravel(item.getTotalTravel());

            userTravelDataDtos.add(userTravelDataDto);
        });

        return userTravelDataDtos;
    }

    @Override
    public Page<UserTravelDataDto> getAll(Pageable pageable) {
        return null;
    }
}
