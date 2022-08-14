package com.spring.filter.confing;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.MultipartFilter;

@Component
@Order(0)
public class XssMultiPartFilter extends MultipartFilter {

}
