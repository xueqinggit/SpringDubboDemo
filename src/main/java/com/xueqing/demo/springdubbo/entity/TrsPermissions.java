package com.xueqing.demo.springdubbo.entity;

public class TrsPermissions {
    private String transactionid;

    private String transactionname;

    private String registerflag;

    private String tStaticrules;

    private String sStaticrules;

    private String rStaticrules;

    private String tDynamicrules;

    private String sDynamicrules;

    private String rDynamicrules;

    private String minversion;

    private String maxversionpass;

    private String enable;

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid == null ? null : transactionid.trim();
    }

    public String getTransactionname() {
        return transactionname;
    }

    public void setTransactionname(String transactionname) {
        this.transactionname = transactionname == null ? null : transactionname.trim();
    }

    public String getRegisterflag() {
        return registerflag;
    }

    public void setRegisterflag(String registerflag) {
        this.registerflag = registerflag == null ? null : registerflag.trim();
    }

    public String gettStaticrules() {
        return tStaticrules;
    }

    public void settStaticrules(String tStaticrules) {
        this.tStaticrules = tStaticrules == null ? null : tStaticrules.trim();
    }

    public String getsStaticrules() {
        return sStaticrules;
    }

    public void setsStaticrules(String sStaticrules) {
        this.sStaticrules = sStaticrules == null ? null : sStaticrules.trim();
    }

    public String getrStaticrules() {
        return rStaticrules;
    }

    public void setrStaticrules(String rStaticrules) {
        this.rStaticrules = rStaticrules == null ? null : rStaticrules.trim();
    }

    public String gettDynamicrules() {
        return tDynamicrules;
    }

    public void settDynamicrules(String tDynamicrules) {
        this.tDynamicrules = tDynamicrules == null ? null : tDynamicrules.trim();
    }

    public String getsDynamicrules() {
        return sDynamicrules;
    }

    public void setsDynamicrules(String sDynamicrules) {
        this.sDynamicrules = sDynamicrules == null ? null : sDynamicrules.trim();
    }

    public String getrDynamicrules() {
        return rDynamicrules;
    }

    public void setrDynamicrules(String rDynamicrules) {
        this.rDynamicrules = rDynamicrules == null ? null : rDynamicrules.trim();
    }

    public String getMinversion() {
        return minversion;
    }

    public void setMinversion(String minversion) {
        this.minversion = minversion == null ? null : minversion.trim();
    }

    public String getMaxversionpass() {
        return maxversionpass;
    }

    public void setMaxversionpass(String maxversionpass) {
        this.maxversionpass = maxversionpass == null ? null : maxversionpass.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }
}