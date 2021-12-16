package by.iharantanovich.thefirstserver.parser.dom.mainXml;

public class Doc {

    private String docNum;
    private String docDate;
    private String docGUID;
    private String operType;
    private Double amountOut;

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

    @Override
    public String toString() {
        return "Doc{" +
                "docNum='" + docNum + '\'' +
                ", docDate='" + docDate + '\'' +
                ", docGUID='" + docGUID + '\'' +
                ", operType='" + operType + '\'' +
                ", amountOut=" + amountOut +
                '}';
    }
}
