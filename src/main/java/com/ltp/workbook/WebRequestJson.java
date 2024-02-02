package com.ltp.workbook;

import java.util.ArrayList;
import java.util.List;

public class WebRequestJson {
    public String ibm="";
    public String rsi="";
    public String mt="";
    public String ac="";
    public String aapl="";
    public String amzn="";
    public String tsla="";
    public String msft="";
    public String uber="";
    public String dash="";
    public String wmt="";
    public String nke="";
    public String nflx="";
    public String bx="";
    public String t="";
    public String amd="";
    public String sbux="";
    public String dis="";
    public String f="";
    public String meta="";
    public String ge="";
    public String lmt="";
    public String nvda="";

    public String getNvda() {
        return nvda;
    }

    public void setNvda(String nvda) {
        this.nvda = nvda;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getAapl() {
        return aapl;
    }

    public void setAapl(String aapl) {
        this.aapl = aapl;
    }

    public String getAmzn() {
        return amzn;
    }

    public void setAmzn(String amzn) {
        this.amzn = amzn;
    }

    public String getTsla() {
        return tsla;
    }

    public void setTsla(String tsla) {
        this.tsla = tsla;
    }

    public String getMsft() {
        return msft;
    }

    public void setMsft(String msft) {
        this.msft = msft;
    }

    public String getUber() {
        return uber;
    }

    public void setUber(String uber) {
        this.uber = uber;
    }

    public String getDash() {
        return dash;
    }

    public void setDash(String dash) {
        this.dash = dash;
    }

    public String getWmt() {
        return wmt;
    }

    public void setWmt(String wmt) {
        this.wmt = wmt;
    }

    public String getNke() {
        return nke;
    }

    public void setNke(String nke) {
        this.nke = nke;
    }

    public String getNflx() {
        return nflx;
    }

    public void setNflx(String nflx) {
        this.nflx = nflx;
    }

    public String getBx() {
        return bx;
    }

    public void setBx(String bx) {
        this.bx = bx;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getAmd() {
        return amd;
    }

    public void setAmd(String amd) {
        this.amd = amd;
    }

    public String getSbux() {
        return sbux;
    }

    public void setSbux(String sbux) {
        this.sbux = sbux;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getGe() {
        return ge;
    }

    public void setGe(String ge) {
        this.ge = ge;
    }

    public String getLmt() {
        return lmt;
    }

    public void setLmt(String lmt) {
        this.lmt = lmt;
    }

    public String getIbm() {
        return ibm;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public void setIbm(String ibm) {
        this.ibm = ibm;
    }

    public String getRsi() {
        return rsi;
    }

    public void setRsi(String rsi) {
        this.rsi = rsi;
    }
    public static List<String> getSelectedCompanies(WebRequestJson req){
        List<String> res = new ArrayList<>();
        if(!req.getIbm().equals("false")){res.add("IBM");}
        if(!req.getAapl().equals("false")){res.add("AAPL");}
        if(!req.getAmzn().equals("false")){res.add("AMZN");}
        if(!req.getTsla().equals("false")){res.add("TSLA");}
        if(!req.getMsft().equals("false")){res.add("MSFT");}
        if(!req.getUber().equals("false")){res.add("UBER");}
        if(!req.getDash().equals("false")){res.add("DASH");}
        if(!req.getWmt().equals("false")){res.add("WMT");}
        if(!req.getNvda().equals("false")){res.add("NVDA");}
        if(!req.getNke().equals("false")){res.add("NKE");}
        if(!req.getNflx().equals("false")){res.add("NFLX");}
        if(!req.getBx().equals("false")){res.add("BX");}
        if(!req.getT().equals("false")){res.add("T");}
        if(!req.getAmd().equals("false")){res.add("AMD");}
        if(!req.getSbux().equals("false")){res.add("SBUX");}
        if(!req.getDis().equals("false")){res.add("DIS");}
        if(!req.getF().equals("false")){res.add("F");}
        if(!req.getMeta().equals("false")){res.add("META");}
        if(!req.getGe().equals("false")){res.add("GE");}
        if(!req.getLmt().equals("false")){res.add("LMT");}

        return res;
    }
}