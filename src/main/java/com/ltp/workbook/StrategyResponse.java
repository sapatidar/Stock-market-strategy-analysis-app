package com.ltp.workbook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Document
public class StrategyResponse {

    @Id
    String id;
    String searchKey="";

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String symbol="";
    double currentPrice;
    double openingPrice;
    double closingPrice;
    double high;
    double low;
    int volume;
    double priceChange;
    double ema12;
    double ema26;
    double ema200;
    double sma;
    double macdLine;
    double signalLine;
    double histogram;
    double zeroLine;
    double bolingerMid;
    double bolingerLow;
    double bolingerHigh;
    double standardDeviation;
    double absoluteBandwidth;
    double percBandwith;
    double generatedTarget1;
    double targetProfitPercent1;
    double stopLoss1;
    double targetLossPercent1;
    double generatedTarget2;
    double targetProfitPercent2;
    double stopLoss2;
    double targetLossPercent2;
    boolean targetHitIndicator1 = false;
    boolean targetHitIndicator2 = false;
    String targetType1="";
    String targetType2="";
    Date time;
    double averageGain;
    double averageLoss;
    String newsHeadline="";
    String newsSummary="";
    String newsSource="";

    public String getNewsHeadline() {
        return newsHeadline;
    }

    public void setNewsHeadline(String newsHeadline) {
        this.newsHeadline = newsHeadline;
    }

    public String getNewsSummary() {
        return newsSummary;
    }

    public void setNewsSummary(String newsSummary) {
        this.newsSummary = newsSummary;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getTargetType2() {
        return targetType2;
    }

    public void setTargetType2(String targetType2) {
        this.targetType2 = targetType2;
    }

    public double getAverageGain() {
        return averageGain;
    }

    public void setAverageGain(double averageGain) {
        this.averageGain = averageGain;
    }

    public double getAverageLoss() {
        return averageLoss;
    }

    public void setAverageLoss(double averageLoss) {
        this.averageLoss = averageLoss;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public StrategyResponse() {
    }

    public String getTargetType1() {
        return targetType1;
    }
    public void setTargetType1(String targetType1) {
        this.targetType1 = targetType1;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }

    public double getEma12() {
        return ema12;
    }

    public void setEma12(double ema12) {
        this.ema12 = ema12;
    }

    public double getEma26() {
        return ema26;
    }

    public void setEma26(double ema26) {
        this.ema26 = ema26;
    }

    public double getEma200() {
        return ema200;
    }

    public void setEma200(double ema200) {
        this.ema200 = ema200;
    }

    public double getSma() {
        return sma;
    }

    public void setSma(double sma) {
        this.sma = sma;
    }

    public double getMacdLine() {
        return macdLine;
    }

    public void setMacdLine(double macdLine) {
        this.macdLine = macdLine;
    }

    public double getSignalLine() {
        return signalLine;
    }

    public void setSignalLine(double signalLine) {
        this.signalLine = signalLine;
    }

    public double getHistogram() {
        return histogram;
    }

    public void setHistogram(double histogram) {
        this.histogram = histogram;
    }

    public double getZeroLine() {
        return zeroLine;
    }

    public void setZeroLine(double zeroLine) {
        this.zeroLine = zeroLine;
    }

    public double getBolingerMid() {
        return bolingerMid;
    }

    public void setBolingerMid(double bolingerMid) {
        this.bolingerMid = bolingerMid;
    }

    public double getBolingerLow() {
        return bolingerLow;
    }

    public void setBolingerLow(double bolingerLow) {
        this.bolingerLow = bolingerLow;
    }

    public double getBolingerHigh() {
        return bolingerHigh;
    }

    public void setBolingerHigh(double bolingerHigh) {
        this.bolingerHigh = bolingerHigh;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getAbsoluteBandwidth() {
        return absoluteBandwidth;
    }

    public void setAbsoluteBandwidth(double absoluteBandwidth) {
        this.absoluteBandwidth = absoluteBandwidth;
    }

    public double getPercBandwith() {
        return percBandwith;
    }

    public void setPercBandwith(double percBandwith) {
        this.percBandwith = percBandwith;
    }

    public double getGeneratedTarget1() {
        return generatedTarget1;
    }

    public void setGeneratedTarget1(double generatedTarget1) {
        this.generatedTarget1 = generatedTarget1;
    }

    public double getTargetProfitPercent1() {
        return targetProfitPercent1;
    }

    public void setTargetProfitPercent1(double targetProfitPercent1) {
        this.targetProfitPercent1 = targetProfitPercent1;
    }

    public double getStopLoss1() {
        return stopLoss1;
    }

    public void setStopLoss1(double stopLoss1) {
        this.stopLoss1 = stopLoss1;
    }

    public double getTargetLossPercent1() {
        return targetLossPercent1;
    }

    public void setTargetLossPercent1(double targetLossPercent1) {
        this.targetLossPercent1 = targetLossPercent1;
    }

    public double getGeneratedTarget2() {
        return generatedTarget2;
    }

    public void setGeneratedTarget2(double generatedTarget2) {
        this.generatedTarget2 = generatedTarget2;
    }

    public double getTargetProfitPercent2() {
        return targetProfitPercent2;
    }

    public void setTargetProfitPercent2(double targetProfitPercent2) {
        this.targetProfitPercent2 = targetProfitPercent2;
    }

    public double getStopLoss2() {
        return stopLoss2;
    }

    public void setStopLoss2(double stopLoss2) {
        this.stopLoss2 = stopLoss2;
    }

    public double getTargetLossPercent2() {
        return targetLossPercent2;
    }

    public void setTargetLossPercent2(double targetLossPercent2) {
        this.targetLossPercent2 = targetLossPercent2;
    }

    public boolean isTargetHitIndicator1() {
        return targetHitIndicator1;
    }

    public void setTargetHitIndicator1(boolean targetHitIndicator1) {
        this.targetHitIndicator1 = targetHitIndicator1;
    }

    public boolean isTargetHitIndicator2() {
        return targetHitIndicator2;
    }

    public void setTargetHitIndicator2(boolean targetHitIndicator2) {
        this.targetHitIndicator2 = targetHitIndicator2;
    }
}