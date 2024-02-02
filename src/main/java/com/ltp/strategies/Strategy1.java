package com.ltp.strategies;

import com.ltp.workbook.StrategyResponse;

import java.util.Random;

public class Strategy1{
    public static double smoothingFactor = 2.0;

    public static StrategyResponse runStrategy(StrategyResponse input, StrategyResponse output){
        double ema12 = calculateEma(input.getEma12(), 12, output.getCurrentPrice());
        output.setEma12(ema12);
        double ema26 = calculateEma(input.getEma26(), 26, output.getCurrentPrice());
        output.setEma26(ema26);
        double ema200 = calculateEma(input.getEma200(), 200, output.getCurrentPrice());
        output.setEma200(ema200);
        double macdLine = ema12 - ema26;
        output.setMacdLine(macdLine);
        double signalLine = calculateEma(input.getMacdLine(), 9, macdLine);
        output.setSignalLine(signalLine);
        double histogram = macdLine - signalLine;
        output.setHistogram(histogram);

        if(checkBuyTarget(input,output)){
            double target = output.getCurrentPrice() * (1.015);
            output.setGeneratedTarget1(target);
            output.setStopLoss1(output.getCurrentPrice() * (0.98));
            output.setTargetProfitPercent1(1.5);
            output.setTargetLossPercent1(2);
            output.setTargetType1("buy");
        }else if(checkSellTarget(input,output)){
            double target = output.getCurrentPrice() * (0.985);
            output.setGeneratedTarget1(target);
            output.setStopLoss1(output.getCurrentPrice() * (1.02));
            output.setTargetProfitPercent1(1.5);
            output.setTargetLossPercent1(2);
            output.setTargetType1("sell");
        }else{
            output.setGeneratedTarget1(0);
            output.setStopLoss1(0);
            output.setTargetProfitPercent1(0);
            output.setTargetLossPercent1(0);
            output.setTargetType1("N/A");
        }
        return output;
    }

    public static boolean checkSellTarget(StrategyResponse input, StrategyResponse output) {

        if(output.getMacdLine()<0 && output.getHistogram()>0 && output.getCurrentPrice()>output.getEma200()){
            return true;
        }
            return false;
    }

    public static boolean checkBuyTarget(StrategyResponse input, StrategyResponse output) {

        if(output.getMacdLine()>0 && output.getHistogram()<0){
            return true;
        }
        return false;
    }

    public static double calculateEma(double previous, int period, double currentPrice){
        double result =0;
        double step = smoothingFactor/(period+1);
        result = currentPrice * step + previous * (1-step);
        return result;
    }
}