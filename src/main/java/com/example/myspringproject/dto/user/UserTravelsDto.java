package com.example.myspringproject.dto.user;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User Travels Model")
public class UserTravelsDto {
    @ApiModelProperty(value = "User Travels Primary ID")
    private Long id;
    @ApiModelProperty(value = "The Courier ID")
    private int courierId;
    @ApiModelProperty(value = "Courier Store ID")
    private int storeId;
    @ApiModelProperty(value = "Travel lat")
    private double lat;
    @ApiModelProperty(value = "Travel lng")
    private double lng;
    @ApiModelProperty(value = "Travel time")
    private String time;
}
