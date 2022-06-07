package com.spring.oauth2.repository;


import com.spring.oauth2.domain.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentsRepository {

    List<Department> getDepartments();

    int updateDeptNameByDeptNo();
}
