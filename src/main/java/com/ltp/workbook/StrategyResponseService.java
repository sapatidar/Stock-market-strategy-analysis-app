package com.ltp.workbook;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ltp.externalApis.ExternalCalls;
import com.ltp.model.Listings;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StrategyResponseService {
    private final StrategyResponseRepository repository;
    private final MongoTemplate template;

    public String insert(StrategyResponse obj){
        return repository.save(obj).getSymbol();
    }

    public StrategyResponseList getLatestRecord(String key){
        Query query = new Query();
        query.addCriteria(Criteria.where("searchKey").is(key));
        List<StrategyResponse> response = template.find(query,StrategyResponse.class);
        StrategyResponseList result = new StrategyResponseList();
        result.setResponseList(response);
        return result;
    }

    public void updateTargetHits(JSONObject obj){
        //
    }

    public JsonObject calculateRsi(String symbol){
        RSI result = repository.calculateRsi(symbol);

        JSONObject latestQuote = ExternalCalls.getLatestQuote(symbol);
        double current_Price = Double.parseDouble(latestQuote.getString("05. price"));
        result.setCurrentPrice(current_Price);
        double close = Double.parseDouble(latestQuote.getString("08. previous close"));
        result.setPreviousClose(close);
        double change = Double.parseDouble(latestQuote.getString("09. change"));
        result.setPriceChange(change);
        result.setSymbol(symbol);
        result.setCompany(Listings.getMapCompany().get(symbol));

        Gson gson = new Gson();
        String str = gson.toJson(result);
        JsonObject obj = JsonParser.parseString(str).getAsJsonObject();
        return obj;
    }
    public JsonObject calculateTrend(String symbol){
        List<String> positiveKeywords = new ArrayList<>();
        positiveKeywords.add("report");
        List<String> negativeKeywords = new ArrayList<>();
        negativeKeywords.add("weaker");
        String positive = String.join("|", positiveKeywords);
        String negative = String.join("|",negativeKeywords);

        MarketTrend response = repository.calculateMarketTrend(symbol,positive,negative);
        Gson gson = new Gson();
        String str = gson.toJson(response);
        JsonObject obj = JsonParser.parseString(str).getAsJsonObject();
        return obj;
    }

    public AggregationResults<Document> getAccuracy(String symbol, int strategyNumber){

        String genTar = (strategyNumber == 1)? "generatedTarget1":"generatedTarget2";
        String tarHit = (strategyNumber == 1)? "targetHitIndicator1":"targetHitIndicator2";
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(
                new Criteria().andOperator(
                        Criteria.where("searchKey").regex("^"+ "Thu"),
                        Criteria.where(genTar).ne(0)
                )
        ), Aggregation.group()
                .count().as("totalDocuments")
                .sum(ConditionalOperators.Cond.newBuilder()
                        .when(Criteria.where(tarHit).is(true))
                        .then(1)
                        .otherwise(0))
                .as("matchingDocuments"),
                Aggregation.project()
                .andExclude("_id")
                .and("$_id").as("date")
                .and("$matchingDocuments").as("targetsHit")
                .and("$totalDocuments").as("targetsGenerated")
                .andExpression("matchingDocuments * 100 / totalDocuments").as("accuracy"));
        AggregationResults<Document> results = template.aggregate(aggregation, StrategyResponse.class, Document.class);
        return results;
    }

}