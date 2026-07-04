package com.gym.mapper;

import com.gym.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    Integer selectTotalCount();
    List<Employee> findAll();
    Boolean insertEmployee(Employee employee);
    Boolean deleteByEmployeeAccount(Integer employeeAccount);
    Boolean updateEmployeeByEmployeeAccount(Employee employee);
    List<Employee> selectByEmployeeAccount(Integer employeeAccount);
}
