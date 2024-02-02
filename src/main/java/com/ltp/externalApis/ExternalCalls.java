package com.ltp.externalApis;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ExternalCalls{

    public static String apiKey = "apikey=BUXTP5SEVY0MXFGW";
    public static String functionString = "function=";
    public static String baseURL = "https://www.alphavantage.co/query?";
    public static String and = "&";
    public static boolean checkMarketStatus(){
        boolean status = false;
        JSONObject apiResponse = apiCall("MARKET_STATUS", new String[]{});
        JSONArray markets = apiResponse.getJSONArray("markets");
        JSONObject nyse = markets.getJSONObject(0);
        if(nyse.getString("current_status").equals("open")){
            status = true;
        }
        return status;
    }

    public static JSONObject getLatestQuote(String symbol){
        JSONObject response = null;
        String parameters[] = new String[1];
        parameters[0] = "symbol=" + symbol;
        response = apiCall("GLOBAL_QUOTE", parameters).getJSONObject("Global Quote");
        return response;
    }

    public static JSONObject getDayHighLow(String symbol, String date){
        JSONObject response = null;
        String parameters[] = new String[1];
        parameters[0] = "symbol=" + symbol;
        response = apiCall("TIME_SERIES_DAILY", parameters).getJSONObject("Time Series (Daily)").getJSONObject(date);
        return response;
    }

    public static JSONObject getNews(String symbol){
        JSONObject response = null;
        String parameters[] = new String[1];
        parameters[0] = "tickers=" + symbol;
        try{response = apiCall("NEWS_SENTIMENT", parameters).getJSONArray("feed").getJSONObject(0);}
        catch(Exception e){
            response = new JSONObject();
            response.put("title","");
            response.put("summary","");
            response.put("source","");
        }
        return response;
    }

    public static JSONObject apiCall(String function, String[] parameters){
        JSONObject response = null;

        String urlString = baseURL + functionString + function;
        for(int i=0; i<parameters.length; i++){
            urlString = urlString + and + parameters[i];
        }
        urlString = urlString + and + apiKey;

        try {
            URL alpha = new URL(urlString);
            String str= IOUtils.toString(new InputStreamReader(alpha.openStream()));
            response = new JSONObject(str);
        } catch (IOException e) {
            // add logger
        }
        return response;
    }

}
