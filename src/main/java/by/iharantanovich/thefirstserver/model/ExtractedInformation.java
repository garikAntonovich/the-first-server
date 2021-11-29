package by.iharantanovich.thefirstserver.model;

import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.BankPayAndRcp;
import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.InfPayAndRcp;
import org.springframework.stereotype.Component;

@Component
public class ExtractedInformation {

    private Integer docNumEx;
    private String docDateEx;
    private String docGUIDEx;
    private String operTypeEx;
    private Double amountOutEx;
    private InfPayAndRcp infPayEx;
    private BankPayAndRcp bankPayEx;
    private InfPayAndRcp infRcpEx;
    private BankPayAndRcp bankRcpEx;
    private String purposeEx;

    public Integer getDocNumEx() {
        return docNumEx;
    }

    public void setDocNumEx(Integer docNumEx) {
        this.docNumEx = docNumEx;
    }

    public String getDocDateEx() {
        return docDateEx;
    }

    public void setDocDateEx(String docDateEx) {
        this.docDateEx = docDateEx;
    }

    public String getDocGUIDEx() {
        return docGUIDEx;
    }

    public void setDocGUIDEx(String docGUIDEx) {
        this.docGUIDEx = docGUIDEx;
    }

    public String getOperTypeEx() {
        return operTypeEx;
    }

    public void setOperTypeEx(String operTypeEx) {
        this.operTypeEx = operTypeEx;
    }

    public Double getAmountOutEx() {
        return amountOutEx;
    }

    public void setAmountOutEx(Double amountOutEx) {
        this.amountOutEx = amountOutEx;
    }

    public InfPayAndRcp getInfPayEx() {
        return infPayEx;
    }

    public void setInfPayEx(InfPayAndRcp infPayEx) {
        this.infPayEx = infPayEx;
    }

    public BankPayAndRcp getBankPayEx() {
        return bankPayEx;
    }

    public void setBankPayEx(BankPayAndRcp bankPayEx) {
        this.bankPayEx = bankPayEx;
    }

    public InfPayAndRcp getInfRcpEx() {
        return infRcpEx;
    }

    public void setInfRcpEx(InfPayAndRcp infRcpEx) {
        this.infRcpEx = infRcpEx;
    }

    public BankPayAndRcp getBankRcpEx() {
        return bankRcpEx;
    }

    public void setBankRcpEx(BankPayAndRcp bankRcpEx) {
        this.bankRcpEx = bankRcpEx;
    }

    public String getPurposeEx() {
        return purposeEx;
    }

    public void setPurposeEx(String purposeEx) {
        this.purposeEx = purposeEx;
    }

    @Override
    public String toString() {
        return "ExtractedInformation{" +
                "docNumEx=" + docNumEx +
                ", docDateEx='" + docDateEx + '\'' +
                ", docGUIDEx='" + docGUIDEx + '\'' +
                ", operTypeEx='" + operTypeEx + '\'' +
                ", amountOutEx=" + amountOutEx +
                ", infPayEx=" + infPayEx +
                ", bankPayEx=" + bankPayEx +
                ", infRcpEx=" + infRcpEx +
                ", bankRcpEx=" + bankRcpEx +
                ", purposeEx='" + purposeEx + '\'' +
                '}';
    }
}
