package by.iharantanovich.thefirstserver.parser.dom;

public class Docs {

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

    public Docs() {
    }

    public Docs(Integer lineNum, String docNum, String docDate, String docGUID, String operType, Integer bicCorr, Double amountIn,
                Double amountOut, String sendAcc, String recipAcc, String purpPay) {
        this.lineNum = lineNum;
        this.docNum = docNum;
        this.docDate = docDate;
        this.docGUID = docGUID;
        this.operType = operType;
        this.bicCorr = bicCorr;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
        this.sendAcc = sendAcc;
        this.recipAcc = recipAcc;
        this.purpPay = purpPay;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public String getDocNum() {
        return docNum;
    }

    public String getDocDate() {
        return docDate;
    }

    public String getDocGUID() {
        return docGUID;
    }

    public String getOperType() {
        return operType;
    }

    public Integer getBicCorr() {
        return bicCorr;
    }

    public Double getAmountIn() {
        return amountIn;
    }

    public Double getAmountOut() {
        return amountOut;
    }

    public String getSendAcc() {
        return sendAcc;
    }

    public String getRecipAcc() {
        return recipAcc;
    }

    public String getPurpPay() {
        return purpPay;
    }
}
