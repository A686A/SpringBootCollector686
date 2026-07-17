package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.constant.MessageId;
import com.example.springbootdemo.common.log.SLogger;
import com.example.springbootdemo.common.log.SLoggerFactory;
import com.example.springbootdemo.common.model.Result;
import com.example.springbootdemo.entity.Organization;
import com.example.springbootdemo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OrganizationController {
    private static final SLogger LOGGER = SLoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/organization/add")
    public Result<String> addOrganization(@RequestBody Organization organization) {
        LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "addOrganization request");
        return organizationService.addOrganization(organization);
    }
}
