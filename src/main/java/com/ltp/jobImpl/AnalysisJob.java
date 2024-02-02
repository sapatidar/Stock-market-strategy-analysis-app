package com.ltp.jobImpl;

import com.ltp.model.Listings;
import com.ltp.workbook.StrategyResponse;
import com.ltp.workbook.StrategyResponseList;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnalysisJob{
    public static String runAnalysis(String key){
        RestTemplate template = new RestTemplateBuilder().build();
        StrategyResponseList responseList = template.getForObject(
                "http://localhost:9090/strategyResponseController/{key}", StrategyResponseList.class, key);
        List<StrategyResponse> companyData = responseList.getResponseList();
        Date time = new Date();
        String generatedString = time.toString();
        ExecutorService es = Executors.newFixedThreadPool(10);
        companyData.forEach((company)->{
            company.setTime(time);
            company.setSearchKey(generatedString);
            es.execute(new JobInstance(company));
        });
        return generatedString;
    }
}