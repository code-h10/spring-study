package com.logging.controller;

import com.logging.service.EmployeesService;
import com.spring.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;

    @GetMapping("/employees")
    public Response getEmployees() {
        return Response.success(200, true, "success", employeesService.getEmployees());
    }

    @GetMapping("/employee")
    public Response getEmployeesByFirstName(@RequestParam String firstName) {
        return Response.success(200, true, "success", employeesService.getEmployeesByFirstName(firstName));
    }
}
