package com.ltp.strategies;

import com.ltp.workbook.StrategyResponse;

import java.util.Random;

public class Strategy2 {
    static double standardDeviationMultiplier = 2.0;
    static int period = 14;

    public static void runStrategy(StrategyResponse input, StrategyResponse output) {
        double sma = calculateSMA(input.getSma(), period, output.getCurrentPrice());
        output.setBolingerMid(sma);
        output.setSma(sma);
        double standardDeviation = calculateSD(input.getStandardDeviation(), sma, period, output.getCurrentPrice());
        output.setStandardDeviation(standardDeviation);
        output.setBolingerHigh(sma + (standardDeviationMultiplier * standardDeviation));
        output.setBolingerLow(sma - (standardDeviationMultiplier * standardDeviation));

        double rsi = calculateRSI(output.getCurrentPrice(), input.getClosingPrice(), input.getAverageGain(), input.getAverageLoss(), period);
        //output.setRsi(rsi);

        if (checkBuyTarget(input, output)) {
            double target = output.getCurrentPrice() * (1.015);
            output.setGeneratedTarget2(target);
            output.setStopLoss2(output.getCurrentPrice() * (0.98));
            output.setTargetProfitPercent2(1.5);
            output.setTargetLossPercent2(2);
            output.setTargetType2("buy");
        } else if (checkSellTarget(input, output)) {
            double target = output.getCurrentPrice() * (0.985);
            output.setGeneratedTarget2(target);
            output.setStopLoss2(output.getCurrentPrice() * (1.02));
            output.setTargetProfitPercent2(1.5);
            output.setTargetLossPercent2(2);
            output.setTargetType1("sell");
        } else {
            output.setGeneratedTarget2(0);
            output.setStopLoss2(0);
            output.setTargetProfitPercent2(0);
            output.setTargetLossPercent2(0);
            output.setTargetType1("N/A");
        }

    }

    public static boolean checkSellTarget(StrategyResponse input, StrategyResponse output) {
        if (output.getCurrentPrice() > output.getBolingerHigh() ){
        //&& output.getRsi() > 70) {
            return true;
        } else
            return false;
    }

    public static boolean checkBuyTarget(StrategyResponse input, StrategyResponse output) {
        if (output.getCurrentPrice() < output.getBolingerLow() ){
                //&& output.getRsi() < 30) {
            return true;
        }
        return false;
    }


    public static double calculateSMA(double previousSMA, int period, double currentPrice) {
        return ((previousSMA * (period - 1)) + currentPrice) / period;
    }

    public static double calculateSD(double previousSD, double sma, int period, double currentPrice) {
        double Diff = currentPrice - sma;
        double psd = previousSD;
        psd = psd + (Diff * Diff);
        return Math.sqrt(psd / period);
    }

    public static double calculateRSI(double currentPrice, double lastPrice, double averageGain, double averageLoss, int period) {
        double currentGain = Math.max(0, currentPrice - lastPrice);
        double currentLoss = Math.max(0, lastPrice - currentPrice);

        averageGain = ((averageGain * (period - 1)) + currentGain) / period;
        averageLoss = ((averageLoss * (period - 1)) + currentLoss) / period;

        double rs = 0;
        try {
            rs = averageGain / averageLoss;
        } catch (Exception e) {
        }
        double rsi = 100 - (100 / (1 + rs));
        return rsi;
    }
}