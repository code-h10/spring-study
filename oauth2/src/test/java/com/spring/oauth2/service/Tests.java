package com.spring.oauth2.service;

import com.spring.oauth2.domain.Department;
import org.assertj.core.internal.Longs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.NumberUtils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Tests {

    @Autowired
    DepartmentsService departmentsService;

    public int binarySearch(int arr[], int l, int r, int x) {

        if (r >= 1) {

            int mid = l + (r - 1) / 2;

            if (arr[mid] == x) {
                return mid;
            }

            if (arr[mid] > x) {
                return binarySearch(arr, l, mid -1, x);
            }

            return binarySearch(arr, mid + 1, r, x);
        }

        return -1;

    }



    @Test
    public void testGetDepartments() {

        List<Department> departments = departmentsService.getDepartments();
        System.out.println(departments);

        departmentsService.updateDeptNameByDeptNo();

    }



}
