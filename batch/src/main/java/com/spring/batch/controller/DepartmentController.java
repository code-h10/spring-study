package com.spring.batch.controller;

import com.spring.batch.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/oauth2")
public class DepartmentController {

    @Autowired
    DepartmentsService departmentsService;

    @GetMapping(value = "/test3")
    public List<Map> getDepartments() {
        return departmentsService.getDepartments();
    }
}
