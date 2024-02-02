package com.ltp.workbook;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/strategyResponseController")
@RequiredArgsConstructor
public class StrategyResponseController{

    private final StrategyResponseService service;

    @PostMapping
    public void insert(@RequestBody StrategyResponse obj){
        service.insert(obj);
    }

    @GetMapping("/{key}")
    public StrategyResponseList getLatestRecord(@PathVariable("key") String key){
        return service.getLatestRecord(key);
    }
}