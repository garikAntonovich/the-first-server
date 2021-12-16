package by.iharantanovich.thefirstserver.parser.dom;

public class InfPayRcp {

    private String innPay;
    private String kppPay;
    private String cNamePay;

    public InfPayRcp() {
    }

    public InfPayRcp(String innPay, String kppPay, String cNamePay) {
        this.innPay = innPay;
        this.kppPay = kppPay;
        this.cNamePay = cNamePay;
    }

    public String getInnPay() {
        return innPay;
    }

    public void setInnPay(String innPay) {
        this.innPay = innPay;
    }

    public String getKppPay() {
        return kppPay;
    }

    public void setKppPay(String kppPay) {
        this.kppPay = kppPay;
    }

    public String getcNamePay() {
        return cNamePay;
    }

    public void setcNamePay(String cNamePay) {
        this.cNamePay = cNamePay;
    }

    @Override
    public String toString() {
        return "InfPayRcp{" +
                "innPay='" + innPay + '\'' +
                ", kppPay='" + kppPay + '\'' +
                ", cNamePay='" + cNamePay + '\'' +
                '}';
    }
}
