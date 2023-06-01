package com.spring.aspect.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/aspect")
public class AspectController {

    @GetMapping("/apple")
    public ResponseEntity<String> getApple(HttpServletRequest request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/apple")
    public ResponseEntity<Void> saveApple(HttpServletRequest request, @RequestBody String body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/banana")
    public ResponseEntity<String> getBanana(HttpServletRequest request) throws Exception {
        throw new RuntimeException();
    }
}
