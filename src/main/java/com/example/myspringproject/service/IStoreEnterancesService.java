package com.example.myspringproject.service;

import com.example.myspringproject.dto.StoreEnterancesDto;
import com.example.myspringproject.dto.UserTravelsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStoreEnterancesService {
    StoreEnterancesDto save(StoreEnterancesDto storeEnterancesDto, UserTravelsDto userTravelsDto);

    void delete(Long id);

    List<StoreEnterancesDto> getAll();

    Page<StoreEnterancesDto> getAll(Pageable pageable);
}
