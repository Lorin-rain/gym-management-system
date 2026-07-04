package com.gym.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    private Integer equipmentId;
    private String equipmentName;
    private String equipmentLocation;
    private String equipmentStatus;
    private String equipmentMessage;
}
