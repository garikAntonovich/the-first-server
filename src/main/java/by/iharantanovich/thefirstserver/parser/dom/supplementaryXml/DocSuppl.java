package by.iharantanovich.thefirstserver.parser.dom.supplementaryXml;

public class DocSuppl {

    private InfPayRcp infPay;
    private InfPayRcp infRcp;
    private BankPayRcp bankPay;
    private BankPayRcp bankRcp;
    private String purpose;
    private String guid;

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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "DocSuppl{" +
                "infPay=" + infPay +
                ", infRcp=" + infRcp +
                ", bankPay=" + bankPay +
                ", bankRcp=" + bankRcp +
                ", purpose='" + purpose + '\'' +
                ", guid='" + guid + '\'' +
                '}';
    }
}
