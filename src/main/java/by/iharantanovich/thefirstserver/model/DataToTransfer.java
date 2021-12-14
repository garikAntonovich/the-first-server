package by.iharantanovich.thefirstserver.model;

import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.BankPayAndRcp;
import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.InfPayAndRcp;

public class DataToTransfer {

    private String docNum;
    private String docDate;
    private String docGUID;
    private String operType;
    private Double amountOut;
    private InfPayAndRcp infPay;
    private BankPayAndRcp bankPay;
    private InfPayAndRcp infRcp;
    private BankPayAndRcp bankRcp;
    private String purpose;

    public DataToTransfer() {
    }

    public DataToTransfer(String docNum, String docDate, String docGUID, String operType, Double amountOut, InfPayAndRcp infPay,
                          BankPayAndRcp bankPay, InfPayAndRcp infRcp, BankPayAndRcp bankRcp, String purpose) {
        this.docNum = docNum;
        this.docDate = docDate;
        this.docGUID = docGUID;
        this.operType = operType;
        this.amountOut = amountOut;
        this.infPay = infPay;
        this.bankPay = bankPay;
        this.infRcp = infRcp;
        this.bankRcp = bankRcp;
        this.purpose = purpose;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getDocGUID() {
        return docGUID;
    }

    public void setDocGUID(String docGUID) {
        this.docGUID = docGUID;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public Double getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(Double amountOut) {
        this.amountOut = amountOut;
    }

    public InfPayAndRcp getInfPay() {
        return infPay;
    }

    public void setInfPay(InfPayAndRcp infPay) {
        this.infPay = infPay;
    }

    public BankPayAndRcp getBankPay() {
        return bankPay;
    }

    public void setBankPay(BankPayAndRcp bankPay) {
        this.bankPay = bankPay;
    }

    public InfPayAndRcp getInfRcp() {
        return infRcp;
    }

    public void setInfRcp(InfPayAndRcp infRcp) {
        this.infRcp = infRcp;
    }

    public BankPayAndRcp getBankRcp() {
        return bankRcp;
    }

    public void setBankRcp(BankPayAndRcp bankRcp) {
        this.bankRcp = bankRcp;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "{" +
                "docNum=" + docNum +
                ", docDate='" + docDate + '\'' +
                ", docGUID='" + docGUID + '\'' +
                ", operType='" + operType + '\'' +
                ", amountOut=" + amountOut +
                ", infPay=" + infPay +
                ", bankPay=" + bankPay +
                ", infRcp=" + infRcp +
                ", bankRcp=" + bankRcp +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
