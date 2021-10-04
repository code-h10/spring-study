package com.spring.batch.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentsRepository {

    List<Map> getDepartments();
}
