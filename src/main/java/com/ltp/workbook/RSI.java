package com.ltp.workbook;

public class RSI {
    private Double RSI;
    public String symbol="";
    public String company="";
    public double currentPrice;
    public double priceChange;
    public double previousClose;



    public double getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }

    public Double getRSI() {
        return RSI;
    }

    public void setRSI(Double RSI) {
        this.RSI = RSI;
    }
}
