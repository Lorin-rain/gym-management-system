package com.gym.controller;

import com.gym.mapper.EquipmentMapper;
import com.gym.pojo.Admin;
import com.gym.pojo.Member;
import com.gym.service.AdminService;
import com.gym.service.EmployeeService;
import com.gym.service.EquipmentService;
import com.gym.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiLoginController {

    private static final String SESSION_ADMIN = "admin";
    private static final String SESSION_USER = "user";

    private final AdminService adminService;
    private final MemberService memberService;
    private final EmployeeService employeeService;
    private final EquipmentService equipmentService;

    public ApiLoginController(AdminService adminService, MemberService memberService, EmployeeService employeeService, EquipmentService equipmentService) {
        this.adminService = adminService;
        this.memberService = memberService;
        this.employeeService = employeeService;
        this.equipmentService = equipmentService;
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<Map<String, Object>> adminLogin(Admin admin, HttpSession session) {
        Admin loggedIn = adminService.adminLogin(admin);
        if (loggedIn == null) {
            return unauthorized("账号或密码有误！");
        }
        putAdminMainDataInSession(session, loggedIn);
        return ResponseEntity.ok(singleSuccess());
    }

    @GetMapping("/toAdminMain")
    public ResponseEntity<Map<String, Object>> toAdminMain(HttpSession session) {
        Map<String, Object> body = new HashMap<>();
        body.put("humanTotal", session.getAttribute("humanTotalCount"));
        body.put("memberTotal", session.getAttribute("memberTotalCount"));
        body.put("employeeTotal", session.getAttribute("employeeTotalCount"));
        body.put("equipmentTotal", session.getAttribute("equipmentTotalCount"));
        body.put("success", true);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/toUserMain")
    public ResponseEntity<Map<String, Object>> toUserMain(HttpSession session) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("member", session.getAttribute(SESSION_USER));
        return ResponseEntity.ok(body);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(singleSuccess());
    }

    @PostMapping("/userLogin")
    public ResponseEntity<Map<String, Object>> userLogin(Member member, HttpSession session) {
        Member loggedIn = memberService.userLogin(member);
        if (loggedIn == null) {
            return unauthorized("账号或密码有误");
        }
        session.setAttribute(SESSION_USER, loggedIn);
        return ResponseEntity.ok(singleSuccess());
    }

    private static Map<String, Object> singleSuccess() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    private static ResponseEntity<Map<String, Object>> unauthorized(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("message", msg);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }

    private void putAdminMainDataInSession(HttpSession session, Admin admin) {
        session.setAttribute(SESSION_ADMIN, admin);
        Integer memberTotalCount = memberService.selectTotalCount();
        Integer employeeTotalCount = employeeService.selectTotalCount();
        Integer equipmentTotalCount = equipmentService.selectTotalCount();
        Integer humanTotalCount = memberTotalCount + employeeTotalCount;
        session.setAttribute("humanTotalCount", humanTotalCount);
        session.setAttribute("memberTotalCount", memberTotalCount);
        session.setAttribute("employeeTotalCount", employeeTotalCount);
        session.setAttribute("equipmentTotalCount", equipmentTotalCount);
    }
}
