package by.iharantanovich.thefirstserver.parser.dom;

import java.util.List;

public class Root {

    private Integer docNum;
    private String docDate;
    private String docDateOld;
    private String accNum;
    private String reportTypeFlag;
    private Integer codeOkeu;
    private List<Docs> docs;
    private String executorSFP;
    private String executorPost;
    private StmInfrmtnTf stmInfrmtnTf;

    public Root() {
    }

    public Root(Integer docNum, String docDate, String docDateOld, String accNum, String reportTypeFlag, Integer codeOkeu, List<Docs> docs,
                String executorSFP, String executorPost, StmInfrmtnTf stmInfrmtnTf) {
        this.docNum = docNum;
        this.docDate = docDate;
        this.docDateOld = docDateOld;
        this.accNum = accNum;
        this.reportTypeFlag = reportTypeFlag;
        this.codeOkeu = codeOkeu;
        this.docs = docs;
        this.executorSFP = executorSFP;
        this.executorPost = executorPost;
        this.stmInfrmtnTf = stmInfrmtnTf;
    }

    public Integer getDocNum() {
        return docNum;
    }

    public String getDocDate() {
        return docDate;
    }

    public String getDocDateOld() {
        return docDateOld;
    }

    public String getAccNum() {
        return accNum;
    }

    public String getReportTypeFlag() {
        return reportTypeFlag;
    }

    public Integer getCodeOkeu() {
        return codeOkeu;
    }

    public List<Docs> getDocs() {
        return docs;
    }

    public String getExecutorSFP() {
        return executorSFP;
    }

    public String getExecutorPost() {
        return executorPost;
    }

    public StmInfrmtnTf getStmInfrmtnTf() {
        return stmInfrmtnTf;
    }
}
