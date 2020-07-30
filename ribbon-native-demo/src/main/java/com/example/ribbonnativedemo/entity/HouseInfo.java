package com.example.ribbonnativedemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel(value = "com.example.ribbonnativedemo.entity.HouseInfo", description = "获取用户参数")
public class HouseInfo {
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "区域")
    private String region;
    @ApiModelProperty(value = "姓名")
    private String name;
}
