package com.ahmtkzk.aopworkspace.controller;

import com.ahmtkzk.aopworkspace.aspect.annotation.MyLogger;
import com.ahmtkzk.aopworkspace.aspect.annotation.NineCheck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @MyLogger
    @GetMapping("/demo")
    public ResponseEntity<String> firstMethod(@RequestParam(required = false) @NineCheck String firstParam) {
        return ResponseEntity.ok("OK");
    }

}
