package by.iharantanovich.thefirstserver.parser.dom;

public class Docs {

    private String docNum;
    private String docDate;
    private String docGUID;
    private String operType;
    private Double amountOut;

    private InfPayRcp infPay;
    private InfPayRcp infRcp;
    private BankPayRcp bankPay;
    private BankPayRcp bankRcp;

    public Docs() {
    }

    public Docs(String docNum, String docDate, String docGUID, String operType, Double amountOut) {
        this.docNum = docNum;
        this.docDate = docDate;
        this.docGUID = docGUID;
        this.operType = operType;
        this.amountOut = amountOut;
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

    public InfPayRcp getInfPay() {
        return infPay;
    }

    public void setInfPay(InfPayRcp infPay) {
        this.infPay = infPay;
    }

    public InfPayRcp getInfRcp() {
        return infRcp;
    }

    public void setInfRcp(InfPayRcp infRcp) {
        this.infRcp = infRcp;
    }

    public BankPayRcp getBankPay() {
        return bankPay;
    }

    public void setBankPay(BankPayRcp bankPay) {
        this.bankPay = bankPay;
    }

    public BankPayRcp getBankRcp() {
        return bankRcp;
    }

    public void setBankRcp(BankPayRcp bankRcp) {
        this.bankRcp = bankRcp;
    }
}
