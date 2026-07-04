package com.gym.service;

import com.gym.pojo.Member;

import java.util.List;

public interface MemberService {
    Integer selectTotalCount();
    List<Member> findAll();
    Boolean insertMember(Member member);
    List<Member> selectByMemberAccount(Integer memberAccount);
    Boolean deleteByMemberAccount(Integer memberAccount);
    Boolean updateMemberByMemberAccount(Member member);
    Member userLogin(Member member);
}
