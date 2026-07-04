package com.gym.service;

import com.gym.pojo.Employee;
import com.gym.pojo.Equipment;

import java.util.List;

public interface EquipmentService {
    Integer selectTotalCount();
    List<Equipment> findAll();
    Boolean insertEquipment(Equipment equipment);
    Boolean updateEquipmentByEquipmentId(Equipment equipment);
    List<Equipment> selectByEquipmentId(Integer equipmentId);
    Boolean deleteByEquipmentId(Integer equipmentId);
}
