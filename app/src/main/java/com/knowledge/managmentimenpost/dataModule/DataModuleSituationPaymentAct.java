package com.knowledge.managmentimenpost.dataModule;

public class DataModuleSituationPaymentAct {
    private String codeFactor;
    private String factorPrice;
    private String receiver;
    private String infoPost;
    private String sender;
    private String isPay;
    private String dateFactor;

    public String getCodeFactor() {
        return codeFactor;
    }

    public void setCodeFactor(String codeFactor) {
        this.codeFactor = codeFactor;
    }

    public String getFactorPrice() {
        return factorPrice;
    }

    public void setFactorPrice(String factorPrice) {
        this.factorPrice = factorPrice;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDateFactor() {
        return dateFactor;
    }

    public void setDateFactor(String dateFactor) {
        this.dateFactor = dateFactor;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getInfoPost() {
        return infoPost;
    }

    public void setInfoPost(String infoPost) {
        this.infoPost = infoPost;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }
}
