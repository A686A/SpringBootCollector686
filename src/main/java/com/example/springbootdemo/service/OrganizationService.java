package com.example.springbootdemo.service;

import com.example.springbootdemo.common.model.Result;
import com.example.springbootdemo.entity.Organization;

public interface OrganizationService {

    Result<String> addOrganization(Organization organization);
}
