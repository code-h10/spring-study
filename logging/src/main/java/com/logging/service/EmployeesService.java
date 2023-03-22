package com.logging.service;

import com.logging.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepository employeesRepository;

    public List<Map> getEmployees() {
        log.debug("all of employees");
        return employeesRepository.getEmployees();
    }
    public List<Map> getEmployeesByFirstName(String firstName) {
        log.debug("find employees by first name");
        return employeesRepository.getEmployeesByFirstName(firstName);
    }
}
