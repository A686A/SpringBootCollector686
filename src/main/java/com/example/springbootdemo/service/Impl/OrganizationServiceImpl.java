package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.common.constant.MessageId;
import com.example.springbootdemo.common.log.SLogger;
import com.example.springbootdemo.common.log.SLoggerFactory;
import com.example.springbootdemo.common.model.Result;
import com.example.springbootdemo.entity.Member;
import com.example.springbootdemo.entity.Organization;
import com.example.springbootdemo.mapper.MemberMapper;
import com.example.springbootdemo.mapper.OrganizationMapper;
import com.example.springbootdemo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private static final SLogger LOGGER = SLoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> addOrganization(Organization organization) {
        LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "addOrganization start");
        try {
            if (organization == null || !StringUtils.hasText(organization.getId())) {
                return Result.error("400", "organization id cannot be empty");
            }

            Map<String, Member> members = organization.getMembers();
            Result<String> memberCheckResult = checkMembers(members);
            if (memberCheckResult != null) {
                return memberCheckResult;
            }

            organizationMapper.insert(organization);
            int memberCount = insertMembers(organization.getId(), members);

            return Result.success("insert organization success, member count: " + memberCount);
        } catch (RuntimeException e) {
            LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), e, "addOrganization error");
            throw e;
        } finally {
            LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "addOrganization end");
        }
    }

    private Result<String> checkMembers(Map<String, Member> members) {
        if (members == null || members.isEmpty()) {
            return null;
        }

        for (Member member : members.values()) {
            if (member == null) {
                continue;
            }
            if (!StringUtils.hasText(member.getId())) {
                return Result.error("400", "member id cannot be empty");
            }
        }
        return null;
    }

    private int insertMembers(String organizationId, Map<String, Member> members) {
        if (members == null || members.isEmpty()) {
            return 0;
        }

        int memberCount = 0;
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        for (Member member : members.values()) {
            if (member == null) {
                continue;
            }
            member.setOrganizationId(organizationId);
            if (!StringUtils.hasText(member.getCreatedAt())) {
                member.setCreatedAt(now);
            }
            memberMapper.insertOrganizationMember(member);
            memberCount++;
        }
        return memberCount;
    }
}
