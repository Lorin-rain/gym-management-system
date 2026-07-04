package com.gym.controller;

import com.gym.pojo.Employee;
import com.gym.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api/employee")
public class ApiEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/selEmployee")
    public Map<String, Object> selEmployee() {
        Map<String, Object> map = new HashMap<>();
        List<Employee> employees = employeeService.findAll();
        map.put("success", true);
        map.put("employeeList", employees);
        return map;
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Map<String, Object>> addEmployee(Employee employee) {
        Random random = new Random();
        String account1 = "1010";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        int account = Integer.parseInt(account1);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);

        employee.setEmployeeAccount(account);
        employee.setEntryTime(nowDay);

        employeeService.insertEmployee(employee);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/delEmployee")
    public ResponseEntity<Map<String, Object>> delEmployee(Integer employeeAccount) {
        employeeService.deleteByEmployeeAccount(employeeAccount);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<Map<String, Object>> updateEmployee(Employee employee) {
        employeeService.updateEmployeeByEmployeeAccount(employee);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/toUpdateEmployee")
    public ResponseEntity<Map<String, Object>> toUpdateEmployee(Integer employeeAccount) {
        List<Employee> employeeList = employeeService.selectByEmployeeAccount(employeeAccount);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("employeeList", employeeList);
        return ResponseEntity.ok(map);
    }
}
