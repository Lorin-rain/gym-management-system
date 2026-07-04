package com.gym.controller;

import com.gym.pojo.Member;
import com.gym.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api/member")
public class ApiMemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/selMember")
    public Map<String, Object> selMember() {
        Map<String, Object> map = new HashMap<>();
        List<Member> members = memberService.findAll();
        map.put("success", true);
        map.put("memberList", members);
        return map;
    }

    @PostMapping("/addMember")
    public ResponseEntity<Map<String, Object>> addMember(Member member) {
        Random random = new Random();
        String account1 = "2021";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);

        String password = "123456";

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);

        Integer nextClass = member.getCardClass();

        member.setMemberAccount(account);
        member.setMemberPassword(password);
        member.setCardTime(nowDay);
        member.setCardNextClass(nextClass);

        memberService.insertMember(member);

        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/delMember")
    public ResponseEntity<Map<String, Object>> deleteMember(Integer memberAccount) {
        memberService.deleteByMemberAccount(memberAccount);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/toUpdateMember")
    public Map<String, Object> toUpdateMember(Integer memberAccount) {
        List<Member> memberList = memberService.selectByMemberAccount(memberAccount);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("memberList", memberList);
        return resp;
    }

    @PostMapping("/updateMember")
    public ResponseEntity<Map<String, Object>> updateMember(Member member) {
        memberService.updateMemberByMemberAccount(member);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        return ResponseEntity.ok(resp);
    }

}
