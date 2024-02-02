package com.ltp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Listings{

    public static HashMap<String, String> mapCompany = new HashMap<>(){{
        put("IBM","IBM");
        put("AAPL", "APPLE");
        put("AMZN", "AMAZON");
        put("TSLA", "TESLA");
        put("MSFT", "MICROSOFT");
        put("UBER","UBER");
        put("DASH", "DOORDASH");
        put("WMT", "WALMART");
        put("NVDA", "NVIDIA");
        put("NKE", "NIKE");
        put("NFLX","NETFLIX");
        put("BX", "BLACKSTONE");
        put("T", "AT&T");
        put("AMD", "AMD");
        put("SBUX", "STARBUCKS");
        put("DIS","DISNEY");
        put("F", "FORD");
        put("META", "META");
        put("GE", "GENERAL ELECTRONICS");
        put("LMT", "LOCKHEED MARTIN");
    }};

    public static HashMap<String, String> getMapCompany() {
        return mapCompany;
    }

    static List<String> list1 = new ArrayList<>(){{
        add("IBM");
        add("AAPL");
        add("AMZN");
        add("TSLA");
        add("MSFT");
    }};
    static List<String> list2 = new ArrayList<>(){{
        add("UBER");
        add("DASH");
        add("NVDA");
        add("WMT");
        add("NKE");
    }};
    static List<String> list3 = new ArrayList<>(){{
        add("NFLX");
        add("BX");
        add("T");
        add("AMD");
        add("SBUX");
    }};
    static List<String> list4 = new ArrayList<>(){{
        add("DIS");
        add("F");
        add("META");
        add("GE");
        add("LMT");
    }};

    public static List<String> getList1() {
        return list1;
    }
    public static List<String> getList2() {
        return list2;
    }
    public static List<String> getList3() { return list3; }
    public static List<String> getList4() { return list4; }
}