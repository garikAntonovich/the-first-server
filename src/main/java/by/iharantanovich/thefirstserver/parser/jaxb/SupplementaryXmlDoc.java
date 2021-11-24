package by.iharantanovich.thefirstserver.parser.jaxb;

import javax.xml.bind.annotation.XmlElement;

public class SupplementaryXmlDoc {

    private Integer num;
    private String date;
    private Long id;
    private Integer nomPp;
    private String datePp;
    private Double sumPp;
    private SupplementaryXmlAttribute vidPay;
    private String datePpIn;
    private String datePpOut;
    private String vidOper;
    private SupplementaryXmlInfPayAndRcp infPay;
    private SupplementaryXmlBankPayAndRcp bankPay;
    private SupplementaryXmlInfPayAndRcp infRcp;
    private SupplementaryXmlBankPayAndRcp bankRcp;
    private Integer purposeId;
    private Integer orderPay;
    private Integer uin;
    private String purpose;
    private Integer typePl;
    private String dateInTofk;
    private String guid;

    @XmlElement(name = "Num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @XmlElement(name = "Date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "Nom_PP")
    public Integer getNomPp() {
        return nomPp;
    }

    public void setNomPp(Integer nomPp) {
        this.nomPp = nomPp;
    }

    @XmlElement(name = "Date_PP")
    public String getDatePp() {
        return datePp;
    }

    public void setDatePp(String datePp) {
        this.datePp = datePp;
    }

    @XmlElement(name = "Sum_PP")
    public Double getSumPp() {
        return sumPp;
    }

    public void setSumPp(Double sumPp) {
        this.sumPp = sumPp;
    }

    @XmlElement(name = "Vid_Pay")
    public SupplementaryXmlAttribute getVidPay() {
        return vidPay;
    }

    public void setVidPay(SupplementaryXmlAttribute vidPay) {
        this.vidPay = vidPay;
    }

    @XmlElement(name = "Date_PP_IN")
    public String getDatePpIn() {
        return datePpIn;
    }

    public void setDatePpIn(String datePpIn) {
        this.datePpIn = datePpIn;
    }

    @XmlElement(name = "Date_PP_OUT")
    public String getDatePpOut() {
        return datePpOut;
    }

    public void setDatePpOut(String datePpOut) {
        this.datePpOut = datePpOut;
    }

    @XmlElement(name = "VID_Oper")
    public String getVidOper() {
        return vidOper;
    }

    public void setVidOper(String vidOper) {
        this.vidOper = vidOper;
    }

    @XmlElement(name = "Inf_PAY")
    public SupplementaryXmlInfPayAndRcp getInfPay() {
        return infPay;
    }

    public void setInfPay(SupplementaryXmlInfPayAndRcp infPay) {
        this.infPay = infPay;
    }

    @XmlElement(name = "Bank_PAY")
    public SupplementaryXmlBankPayAndRcp getBankPay() {
        return bankPay;
    }

    public void setBankPay(SupplementaryXmlBankPayAndRcp bankPay) {
        this.bankPay = bankPay;
    }

    @XmlElement(name = "Inf_RCP")
    public SupplementaryXmlInfPayAndRcp getInfRcp() {
        return infRcp;
    }

    public void setInfRcp(SupplementaryXmlInfPayAndRcp infRcp) {
        this.infRcp = infRcp;
    }

    @XmlElement(name = "Bank_RCP")
    public SupplementaryXmlBankPayAndRcp getBankRcp() {
        return bankRcp;
    }

    public void setBankRcp(SupplementaryXmlBankPayAndRcp bankRcp) {
        this.bankRcp = bankRcp;
    }

    @XmlElement(name = "Purpose_ID")
    public Integer getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(Integer purposeId) {
        this.purposeId = purposeId;
    }

    @XmlElement(name = "Order_PAY")
    public Integer getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(Integer orderPay) {
        this.orderPay = orderPay;
    }

    @XmlElement(name = "UIN")
    public Integer getUin() {
        return uin;
    }

    public void setUin(Integer uin) {
        this.uin = uin;
    }

    @XmlElement(name = "Purpose")
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @XmlElement(name = "Type_Pl")
    public Integer getTypePl() {
        return typePl;
    }

    public void setTypePl(Integer typePl) {
        this.typePl = typePl;
    }

    @XmlElement(name = "Date_IN_TOFK")
    public String getDateInTofk() {
        return dateInTofk;
    }

    public void setDateInTofk(String dateInTofk) {
        this.dateInTofk = dateInTofk;
    }

    @XmlElement(name = "GUID")
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "num=" + num +
                ", date='" + date + '\'' +
                ", id=" + id +
                ", nomPp=" + nomPp +
                ", datePp='" + datePp + '\'' +
                ", sumPp=" + sumPp +
                ", vidPay=" + vidPay +
                ", datePpIn='" + datePpIn + '\'' +
                ", datePpOut='" + datePpOut + '\'' +
                ", vidOper='" + vidOper + '\'' +
                ", infPay=" + infPay +
                ", bankPay=" + bankPay +
                ", infRcp=" + infRcp +
                ", bankRcp=" + bankRcp +
                ", purposeId=" + purposeId +
                ", orderPay=" + orderPay +
                ", uin=" + uin +
                ", purpose='" + purpose + '\'' +
                ", typePl=" + typePl +
                ", dateInTofk='" + dateInTofk + '\'' +
                ", guid='" + guid + '\'' +
                '}';
    }
}
