package com.example.myspringproject.controller;

import com.example.myspringproject.dto.UserTravelsDto;
import com.example.myspringproject.service.IUserTravelsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user-travels")
@RequiredArgsConstructor
@Api(value = "User Travels API", tags = "1- User Travels API", description = "Add a new travel record or list all travel records.")
public class UserTravelsController {
    private final IUserTravelsService userTravelsService;

    @PostMapping
    @ApiOperation(value = "A method to add a new travel record.")
    public ResponseEntity<UserTravelsDto> save(@RequestBody @ApiParam UserTravelsDto userTravelsDto) throws IOException, ParseException {
        return ResponseEntity.ok(userTravelsService.save(userTravelsDto));
    }
    @ApiOperation(value = "A method to get all travels.")
    @GetMapping
    public ResponseEntity<List<UserTravelsDto>> listAll(){
        return ResponseEntity.ok(userTravelsService.getAll());
    }
}
