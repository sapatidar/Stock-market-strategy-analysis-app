package com.ltp.workbook;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/targetHitsController")
@RequiredArgsConstructor
public class TargetHitsController{

    private final StrategyResponseService service;

    @PostMapping
    public void updateTargetHits(@RequestBody JSONObject obj){
        //service.updateBuyTargets(obj.getString("symbol"), obj.getDouble("2. high"));
        //service.updateSellTargets(obj.getString("symbol"), obj.getDouble("3. low"));
        service.updateTargetHits(obj);
    }
}