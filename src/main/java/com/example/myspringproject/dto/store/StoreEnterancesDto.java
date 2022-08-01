package com.example.myspringproject.dto.store;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Store Enterances Model")
public class StoreEnterancesDto {
    private Long id;
    private int courierId;
    private String time;
    private String storeName;
    private int storeId;
}
