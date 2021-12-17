package by.iharantanovich.thefirstserver.parser.jaxb.model.mainXmlFile;

import javax.xml.bind.annotation.XmlElement;

public class Doc {

    private Integer lineNum;
    private String docNum;
    private String docDate;
    private String docGUID;
    private String operType;
    private Integer bicCorr;
    private Double amountIn;
    private Double amountOut;
    private String sendAcc;
    private String recipAcc;
    private String purpPay;

    @XmlElement(name = "Line_Num")
    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    @XmlElement(name = "DocNum")
    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    @XmlElement(name = "DocDate")
    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    @XmlElement(name = "DocGUID")
    public String getDocGUID() {
        return docGUID;
    }

    public void setDocGUID(String docGUID) {
        this.docGUID = docGUID;
    }

    @XmlElement(name = "OperType")
    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    @XmlElement(name = "Bic_Corr")
    public Integer getBicCorr() {
        return bicCorr;
    }

    public void setBicCorr(Integer bicCorr) {
        this.bicCorr = bicCorr;
    }

    @XmlElement(name = "AmountIn")
    public Double getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(Double amountIn) {
        this.amountIn = amountIn;
    }

    @XmlElement(name = "AmountOut")
    public Double getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(Double amountOut) {
        this.amountOut = amountOut;
    }

    @XmlElement(name = "SendAcc")
    public String getSendAcc() {
        return sendAcc;
    }

    public void setSendAcc(String sendAcc) {
        this.sendAcc = sendAcc;
    }

    @XmlElement(name = "RecipAcc")
    public String getRecipAcc() {
        return recipAcc;
    }

    public void setRecipAcc(String recipAcc) {
        this.recipAcc = recipAcc;
    }

    @XmlElement(name = "PurpPay")
    public String getPurpPay() {
        return purpPay;
    }

    public void setPurpPay(String purpPay) {
        this.purpPay = purpPay;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "lineNum=" + lineNum +
                ", docNum=" + docNum +
                ", docDate=" + docDate +
                ", docGUID='" + docGUID + '\'' +
                ", operType='" + operType + '\'' +
                ", bicCorr=" + bicCorr +
                ", amountIn=" + amountIn +
                ", amountOut=" + amountOut +
                ", sendAcc='" + sendAcc + '\'' +
                ", recipAcc='" + recipAcc + '\'' +
                ", purpPay='" + purpPay + '\'' +
                '}';
    }
}
