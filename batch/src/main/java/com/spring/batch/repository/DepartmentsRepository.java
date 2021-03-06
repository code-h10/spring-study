package com.spring.batch.repository;

import com.spring.batch.domain.DeptManager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DepartmentsRepository {

    List<Map> getDepartments();

    List<DeptManager> getDeptManager();


}
