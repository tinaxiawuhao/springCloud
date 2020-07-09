package com.example.ribbonnativedemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HouseInfo {
    private Long id;
    private String city;
    private String region;
    private String name;
}
