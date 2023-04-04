package com.spring.oauth2.service;

import com.spring.oauth2.model.Department;
import com.spring.oauth2.repository.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DepartmentsService {

    @Autowired
    DepartmentsRepository departmentsRepository;

    public List<Department> getDepartments() {

        List<Department> deptManagers = departmentsRepository.getDepartments();
        return deptManagers;
    }

    @Transactional
    public void updateDeptNameByDeptNo() {
        int updateCount = departmentsRepository.updateDeptNameByDeptNo();
        System.out.println(updateCount);
    }


}
