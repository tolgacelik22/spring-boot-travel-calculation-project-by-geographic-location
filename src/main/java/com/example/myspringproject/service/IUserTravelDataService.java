package com.example.myspringproject.service;

import com.example.myspringproject.dto.UserTravelDataDto;
import com.example.myspringproject.entity.UserTravelData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserTravelDataService {
    UserTravelDataDto save(UserTravelDataDto userTravelDataDto) throws IOException;

    void delete(Long id);

    Optional<UserTravelData> findByCourierId(int courierId);

    List<UserTravelDataDto> getAll();

    Page<UserTravelDataDto> getAll(Pageable pageable);
}
