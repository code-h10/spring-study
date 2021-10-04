package com.spring.oauth2.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class LoginServiceTest {

    @Test
    public void test() {

        List<Map> list = new ArrayList<>();
        Map object1 = new HashMap();
        Map object2 = new HashMap();
        Map object3 = new HashMap();
        Map object4 = new HashMap();
        Map object5 = new HashMap();

        object1.put("id", 12);
        object1.put("type", "AA");


        object2.put("id", 13);
        object2.put("type", "AB");

        object3.put("id", 14);
        object3.put("type", "AC");

        object4.put("id", 15);
        object4.put("type", "AD");

        object5.put("id", 16);
        object5.put("type", "AE");


        list.add(object1);
        list.add(object2);
        list.add(object3);
        list.add(object4);
        list.add(object5);





        Map object6 = new HashMap();
        object6.put("id", 12);
        object6.put("type", "AA");


        object6.putIfAbsent("4", "aa");
        object6.compute("ids", (k, v) -> v);

        object2.putIfAbsent("4", "bb");

        object6.computeIfAbsent("5", k -> null);

        object6.computeIfPresent("6", (k, v) -> v);

        String temp = "hello";


        System.out.println(object6);

        
    }


}