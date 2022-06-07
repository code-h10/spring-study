package com.spring.batch.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.common.enums.Common;
import com.spring.common.enums.UserStatus;
import lombok.*;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeptManager {

    private String deptNo;
    private int empNo;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date fromDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date toDate;

    private Common.UserStatus status;

}
