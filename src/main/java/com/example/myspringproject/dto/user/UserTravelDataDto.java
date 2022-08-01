package com.example.myspringproject.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "User Travel Data Model")
public class UserTravelDataDto {
    private Long id;
    private int courierId;
    private long totalTravel;
}
