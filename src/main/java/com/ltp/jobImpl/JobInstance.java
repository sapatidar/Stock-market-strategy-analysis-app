package com.ltp.jobImpl;

import com.ltp.externalApis.ExternalCalls;
import com.ltp.model.Listings;
import com.ltp.workbook.StrategyResponse;
import com.ltp.strategies.Strategy1;
import com.ltp.strategies.Strategy2;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class JobInstance implements Runnable{
    public StrategyResponse companyData;
    public JobInstance(StrategyResponse companyData){
        this.companyData = companyData;
    }
    public void run(){
        //method will call both strategies for single company and store the results in db as well.
        StrategyResponse response = new StrategyResponse();
        response.setSymbol(companyData.getSymbol());
        response.setTime(companyData.getTime());
        response.setId(new Random().toString());
        response.setSearchKey(companyData.getSearchKey());

        JSONObject latestQuote = ExternalCalls.getLatestQuote(companyData.getSymbol());
        double current_Price = Double.parseDouble(latestQuote.getString("05. price"));
        response.setCurrentPrice(current_Price);
        double high = Double.parseDouble(latestQuote.getString("03. high"));
        response.setHigh(high);
        double low = Double.parseDouble(latestQuote.getString("04. low"));
        response.setLow(low);
        double open = Double.parseDouble(latestQuote.getString("02. open"));
        response.setOpeningPrice(open);
        double close = Double.parseDouble(latestQuote.getString("08. previous close"));
        response.setClosingPrice(close);
        int volume = Integer.parseInt(latestQuote.getString("06. volume"));
        response.setVolume(volume);
        double change = Double.parseDouble(latestQuote.getString("09. change"));
        response.setPriceChange(change);

        JSONObject news = ExternalCalls.getNews(companyData.getSymbol());
        response.setNewsHeadline(news.getString("title"));
        response.setNewsSummary(news.getString("summary"));
        response.setNewsSource(news.getString("source"));

        Strategy1.runStrategy(companyData, response);
        Strategy2.runStrategy(companyData, response);
        RestTemplate template = new RestTemplateBuilder().build();
        try{template.postForObject("http://localhost:9090//strategyResponseController", response, StrategyResponse.class);}
        catch(Exception e){
            System.out.println("Error while saving record(post)");
        }
    }
}