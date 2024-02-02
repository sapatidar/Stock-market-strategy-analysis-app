package com.ltp.workbook;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ltp.model.Listings;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.json.JSONArray;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebService {

    private final StrategyResponseService service;
    public JsonArray calculateRsi(List<String> companies){
        JsonArray result = new JsonArray();
        companies.forEach((symbol)->{
            result.add(service.calculateRsi(symbol));
        });
        return result;
    }

    public JsonArray calculateAccuracy(List<String> companies){
        JsonArray response = new JsonArray();
        companies.forEach((symbol)->{
            Accuracy result = new Accuracy();
            result.setSymbol(symbol);
            result.setCompanyName(Listings.getMapCompany().get(symbol));
            AggregationResults<Document> doc = service.getAccuracy(symbol,1);
            result.setTargetsGenerated(doc.getMappedResults().get(0).getInteger("targetsGenerated"));
            result.setTargetsHit(doc.getMappedResults().get(0).getInteger("targetsHit"));
            result.setAccuracy(doc.getMappedResults().get(0).getDouble("accuracy"));
            doc = service.getAccuracy(symbol,2);
            result.setTargetsGenerated2(doc.getMappedResults().get(0).getInteger("targetsGenerated"));
            result.setTargetsHit2(doc.getMappedResults().get(0).getInteger("targetsHit"));
            result.setAccuracy2(doc.getMappedResults().get(0).getDouble("accuracy"));
            Gson gson = new Gson();
            String str = gson.toJson(result);
            JsonObject obj = JsonParser.parseString(str).getAsJsonObject();
            response.add(obj);
        });
        return response;
    }

    public JsonArray calculateSentiments(List<String> companies){
        JsonArray result = new JsonArray();
        companies.forEach((symbol)->{
            result.add(service.calculateTrend(symbol));
        });
        return result;
    }
}