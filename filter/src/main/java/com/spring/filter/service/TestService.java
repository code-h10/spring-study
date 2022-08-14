package com.spring.filter.service;

import com.spring.filter.repository.TestRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestService {

    @Autowired private TestRepository testRepository;

    public Map findUserById(int id) {
        return testRepository.findUserById(id);
    }


    public void setUser(Map user) {
        testRepository.setUser(user);
    }

}
