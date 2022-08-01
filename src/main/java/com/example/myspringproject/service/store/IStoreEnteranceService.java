package com.example.myspringproject.service.store;

import com.example.myspringproject.dto.store.StoreEnterancesDto;
import com.example.myspringproject.dto.user.UserTravelsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStoreEnteranceService {
    StoreEnterancesDto save(StoreEnterancesDto storeEnterancesDto, UserTravelsDto userTravelsDto);

    void delete(Long id);

    List<StoreEnterancesDto> getAll();

    Page<StoreEnterancesDto> getAll(Pageable pageable);
}
