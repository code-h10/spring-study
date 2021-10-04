package com.spring.batch.service;

import com.spring.batch.repository.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentsService {

    @Autowired
    DepartmentsRepository departmentsRepository;

    public List<Map> getDepartments() {
        List<Map> dept = departmentsRepository.getDepartments();

        return dept;
    }
}
