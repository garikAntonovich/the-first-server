package by.iharantanovich.thefirstserver.parser.jaxb;

import javax.xml.bind.annotation.XmlElement;

public class SupplementaryXmlBankPayAndRcp {

    private String bsPay;
    private String bicPay;
    private String bsKsPay;

    @XmlElement(name = "BS_PAY")
    public String getBsPay() {
        return bsPay;
    }

    public void setBsPay(String bsPay) {
        this.bsPay = bsPay;
    }

    @XmlElement(name = "BIC_PAY")
    public String getBicPay() {
        return bicPay;
    }

    public void setBicPay(String bicPay) {
        this.bicPay = bicPay;
    }

    @XmlElement(name = "BS_KS_PAY")
    public String getBsKsPay() {
        return bsKsPay;
    }

    public void setBsKsPay(String bsKsPay) {
        this.bsKsPay = bsKsPay;
    }

    @Override
    public String toString() {
        return "SupplementaryXmlBankPayAndRcp{" +
                "bsPay='" + bsPay + '\'' +
                ", bicPay='" + bicPay + '\'' +
                ", bsKsPay='" + bsKsPay + '\'' +
                '}';
    }
}
