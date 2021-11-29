package by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile;

import javax.xml.bind.annotation.XmlElement;

public class InfPayAndRcp {

    private String innPay;
    private String kppPay;
    private String cNamePay;

    @XmlElement(name = "INN_PAY")
    public String getInnPay() {
        return innPay;
    }

    public void setInnPay(String innPay) {
        this.innPay = innPay;
    }

    @XmlElement(name = "KPP_PAY")
    public String getKppPay() {
        return kppPay;
    }

    public void setKppPay(String kppPay) {
        this.kppPay = kppPay;
    }

    @XmlElement(name = "CName_PAY")
    public String getcNamePay() {
        return cNamePay;
    }

    public void setcNamePay(String cNamePay) {
        this.cNamePay = cNamePay;
    }

    @Override
    public String toString() {
        return "InfPayAndRcp{" +
                "innPay='" + innPay + '\'' +
                ", kppPay='" + kppPay + '\'' +
                ", cNamePay='" + cNamePay + '\'' +
                '}';
    }
}
