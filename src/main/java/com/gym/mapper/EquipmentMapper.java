package com.gym.mapper;

import com.gym.pojo.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    Integer selectTotalCount();
    List<Equipment> findAll();
    Boolean insertEquipment(Equipment equipment);
    Boolean updateEquipmentByEquipmentId(Equipment equipment);
    List<Equipment> selectByEquipmentId(Integer equipmentId);
    Boolean deleteByEquipmentId(Integer equipmentId);
}
