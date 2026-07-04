package com.gym.service.impl;

import com.gym.mapper.EquipmentMapper;
import com.gym.pojo.Employee;
import com.gym.pojo.Equipment;
import com.gym.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentMapper equipmentMapper;


    @Override
    public Integer selectTotalCount() {
        return equipmentMapper.selectTotalCount();
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentMapper.findAll();
    }

    @Override
    public Boolean insertEquipment(Equipment equipment) {
        return equipmentMapper.insertEquipment(equipment);
    }

    @Override
    public Boolean updateEquipmentByEquipmentId(Equipment equipment) {
        return equipmentMapper.updateEquipmentByEquipmentId(equipment);
    }

    @Override
    public List<Equipment> selectByEquipmentId(Integer equipmentId) {
        return equipmentMapper.selectByEquipmentId(equipmentId);
    }

    @Override
    public Boolean deleteByEquipmentId(Integer equipmentId) {
        return equipmentMapper.deleteByEquipmentId(equipmentId);
    }
}
