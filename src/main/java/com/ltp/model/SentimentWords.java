package com.ltp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentimentWords {
    public static String[] positive = {
            "Bullish", "Upward", "Prosperous", "Booming", "Strong",
            "Robust", "Growing", "Thriving", "Positive", "Bull market",
            "Optimistic", "Surging", "Rally", "Gaining", "Advancing",
            "Lucrative", "Profitable", "Expansion", "Success", "Flourishing",
            "Golden", "Winning", "Elevated", "High-flying", "Radiant",
            "Triumphant", "Victorious", "Upbeat", "Prolific", "Promising",
            "Dynamic", "Favorable", "Upturn", "Wealthy", "Buoyant",
            "Prominent", "Stable", "Grand", "Exuberant", "Vibrant",
            "Optimal", "Innovative", "Energetic", "Enriching", "Sustainable"
    };

    public static String[] negative = {
            "Bearish", "Downward", "Declining", "Slumping", "Weak",
            "Volatile", "Downturn", "Struggling", "Negative", "Bear market",
            "Pessimistic", "Plummeting", "Retreat", "Losing", "Decline",
            "Unstable", "Unprofitable", "Contracting", "Loss", "Downtrend",
            "Turbulent", "Falling", "Depressed", "Low", "Underperforming",
            "Stagnant", "Challenging", "Adverse", "Risk", "Downswing",
            "Faltering", "Losing streak", "Dismal", "Bearish trend", "Worsening",
            "Troubled", "Sinking", "Weakness", "Downslide", "Bearish market",
            "Dull", "Debacle", "Gloomy", "Reduction", "Unsettled"
    };

    public static List<String> getPositiveWords(){
        List<String> positiveWordsList = new ArrayList<>(Arrays.asList(positive));
        return positiveWordsList;
    }
    public static List<String> getNegativeWords(){
        List<String> negativeWordsList = new ArrayList<>(Arrays.asList(negative));
        return negativeWordsList;
    }

}