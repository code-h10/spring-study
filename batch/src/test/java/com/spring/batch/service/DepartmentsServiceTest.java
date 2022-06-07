package com.spring.batch.service;

import com.spring.batch.domain.DeptManager;
import com.spring.batch.repository.DepartmentsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DepartmentsServiceTest {

    @Autowired
    DepartmentsRepository departmentsRepository;



    @Test
    public void test1() throws Exception {

        System.out.println("test");
    }

}