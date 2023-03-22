package com.logging.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface EmployeesRepository {

    List<Map> getEmployees();
    List<Map> getEmployeesByFirstName(String firstName);

}
