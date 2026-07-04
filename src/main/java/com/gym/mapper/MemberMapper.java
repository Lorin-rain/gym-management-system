package com.gym.mapper;

import com.gym.pojo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    Integer selectTotalCount();
    List<Member> findAll();
    List<Member> selectByMemberAccount(Integer memberAccount);
    Boolean insertMember(Member member);
    Boolean updateMemberByMemberAccount(Member member);
    Boolean deleteByMemberAccount(Integer memberAccount);
    Member selectByAccountAndPassword(Member member);
}
