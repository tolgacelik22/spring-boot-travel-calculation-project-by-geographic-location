package com.example.myspringproject.controller;

import com.example.myspringproject.dto.UserTravelDataDto;
import com.example.myspringproject.entity.UserTravelData;
import com.example.myspringproject.service.IUserTravelDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-travel-data")
@RequiredArgsConstructor
@Api(value = "User Travels Data API", tags = "2- User Travels Data API", description = "Get courier travel data by courierId or list all travel data.")
public class UserTravelDataController {
    private final IUserTravelDataService IUserTravelDataService;

    @GetMapping
    @ApiOperation(value = "A method to get all travel data.")
    public ResponseEntity<List<UserTravelDataDto>> listAll(){
        return ResponseEntity.ok(IUserTravelDataService.getAll());
    }

    @RequestMapping("/user/{courierId}")
    @ApiOperation(value = "A method to get travel data by courierId.", httpMethod = "GET")
    public Optional<UserTravelData> getCourierTravel(@PathVariable int courierId)
    {
        return IUserTravelDataService.findByCourierId(courierId);
    }
}
