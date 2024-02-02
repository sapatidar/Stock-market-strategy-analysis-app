package com.ltp.workbook;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ltp.model.Listings;
import com.ltp.workbook.StrategyResponseService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/getAnalysis")
@RequiredArgsConstructor
public class WebController {
    private final WebService service;

    @PostMapping
    public String calculate(@RequestBody WebRequestJson resquest){
        JsonObject response = new JsonObject();
        List<String> companies = Listings.getList1();
        companies.addAll(Listings.getList2());
        companies.addAll(Listings.getList3());
        companies.addAll(Listings.getList4());
        List<String> selectedCompanies = WebRequestJson.getSelectedCompanies(resquest);

        if(resquest.getRsi().equals("true")){
            JsonArray rsi = service.calculateRsi(selectedCompanies);
            response.add("rsi", rsi);
        }
        if(resquest.getMt().equals("true")){
            JsonArray sentiments = service.calculateSentiments(selectedCompanies);
            response.add("mt",sentiments);
        }
       if(resquest.getAc().equals("true")){
           JsonArray accuracy = service.calculateAccuracy(selectedCompanies);
            response.add("ac",accuracy);
        }

        return response.toString();
    }
}