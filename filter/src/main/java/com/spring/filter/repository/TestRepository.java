package com.spring.filter.repository;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TestRepository {

    Map findUserById(int id);

    void setUser(Map user);
}
