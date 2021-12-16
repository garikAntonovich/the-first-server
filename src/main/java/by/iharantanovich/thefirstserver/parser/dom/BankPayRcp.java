package by.iharantanovich.thefirstserver.parser.dom;

public class BankPayRcp {

    private String bsPay;
    private String bicPay;
    private String bsKsPay;

    public BankPayRcp() {
    }

    public BankPayRcp(String bsPay, String bicPay, String bsKsPay) {
        this.bsPay = bsPay;
        this.bicPay = bicPay;
        this.bsKsPay = bsKsPay;
    }

    public String getBsPay() {
        return bsPay;
    }

    public void setBsPay(String bsPay) {
        this.bsPay = bsPay;
    }

    public String getBicPay() {
        return bicPay;
    }

    public void setBicPay(String bicPay) {
        this.bicPay = bicPay;
    }

    public String getBsKsPay() {
        return bsKsPay;
    }

    public void setBsKsPay(String bsKsPay) {
        this.bsKsPay = bsKsPay;
    }

    @Override
    public String toString() {
        return "BankPayRcp{" +
                "bsPay='" + bsPay + '\'' +
                ", bicPay='" + bicPay + '\'' +
                ", bsKsPay='" + bsKsPay + '\'' +
                '}';
    }
}
