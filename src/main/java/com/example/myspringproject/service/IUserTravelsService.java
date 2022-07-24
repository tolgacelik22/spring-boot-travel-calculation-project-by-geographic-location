package com.example.myspringproject.service;

import com.example.myspringproject.dto.UserTravelsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IUserTravelsService {
    UserTravelsDto save(UserTravelsDto userDto) throws IOException, ParseException;

    void delete(long id);

    List<UserTravelsDto> getAll();

    Page<UserTravelsDto> getAll(Pageable pageable);
}
