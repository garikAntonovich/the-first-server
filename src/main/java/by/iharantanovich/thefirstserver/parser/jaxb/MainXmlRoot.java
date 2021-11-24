package by.iharantanovich.thefirstserver.parser.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

//@XmlType(propOrder = {"docNum", "docDate", "docDateOld", "accNum", "reportTypeFlag", "codeOkeu", "doc", "executorSFP", "executorPost"})
@XmlRootElement(name = "SKP_REPORT_KS")
public class MainXmlRoot {

    private Integer docNum;
    private String docDate;
    private String docDateOld;
    private String accNum;
    private String reportTypeFlag;
    private Integer codeOkeu;
    private List<MainXmlDoc> doc = new ArrayList<>();
    private String executorSFP;
    private String executorPost;

    @XmlElement(name = "DocNum")
    public Integer getDocNum() {
        return docNum;
    }

    public void setDocNum(Integer docNum) {
        this.docNum = docNum;
    }

    @XmlElement(name = "DocDate")
    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    @XmlElement(name = "DocDateOld")
    public String getDocDateOld() {
        return docDateOld;
    }

    public void setDocDateOld(String docDateOld) {
        this.docDateOld = docDateOld;
    }

    @XmlElement(name = "AccNum")
    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    @XmlElement(name = "Report_type_flag")
    public String getReportTypeFlag() {
        return reportTypeFlag;
    }

    public void setReportTypeFlag(String reportTypeFlag) {
        this.reportTypeFlag = reportTypeFlag;
    }

    @XmlElement(name = "Code_OKEU")
    public Integer getCodeOkeu() {
        return codeOkeu;
    }

    public void setCodeOkeu(Integer codeOkeu) {
        this.codeOkeu = codeOkeu;
    }

    @XmlElementWrapper(name = "Docs")
    @XmlElement(name = "MainXmlDoc")
    public List<MainXmlDoc> getDoc() {
        return doc;
    }

    public void setDoc(ArrayList<MainXmlDoc> doc) {
        this.doc = doc;
    }

    @XmlElement(name = "Executor_SFP")
    public String getExecutorSFP() {
        return executorSFP;
    }

    public void setExecutorSFP(String executorSFP) {
        this.executorSFP = executorSFP;
    }

    @XmlElement(name = "Executor_Post")
    public String getExecutorPost() {
        return executorPost;
    }

    public void setExecutorPost(String executorPost) {
        this.executorPost = executorPost;
    }

    @Override
    public String toString() {
        return "MainXmlRoot{" +
                "docNum=" + docNum +
                ", docDate='" + docDate + '\'' +
                ", docDateOld='" + docDateOld + '\'' +
                ", accNum='" + accNum + '\'' +
                ", reportTypeFlag='" + reportTypeFlag + '\'' +
                ", codeOkeu=" + codeOkeu +
                ", doc=" + doc +
                ", executorSFP='" + executorSFP + '\'' +
                ", executorPost='" + executorPost + '\'' +
                '}';
    }
}