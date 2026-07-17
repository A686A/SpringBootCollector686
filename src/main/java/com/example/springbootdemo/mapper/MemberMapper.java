package com.example.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    @Insert("insert into member (organization_id, id, username, email, created_at) " +
            "values (#{organizationId}, #{id}, #{username}, #{email}, #{createdAt})")
    int insertOrganizationMember(Member member);
}
