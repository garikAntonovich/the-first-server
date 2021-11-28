package by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Inf_Pay_Doc")
public class RootTagS {

    private String guidDoc;
    private String date;
    private TagAttribute scrs;
    private TagAttribute vidOtch;
    private Integer kolDoc;
    private List<DocTag> doc = new ArrayList<>();

    @XmlElement(name = "GUID_Doc")
    public String getGuidDoc() {
        return guidDoc;
    }

    public void setGuidDoc(String guidDoc) {
        this.guidDoc = guidDoc;
    }

    @XmlElement(name = "Date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "Scrc")
    public TagAttribute getScrs() {
        return scrs;
    }

    public void setScrs(TagAttribute scrs) {
        this.scrs = scrs;
    }

    @XmlElement(name = "Vid_Otch")
    public TagAttribute getVidOtch() {
        return vidOtch;
    }

    public void setVidOtch(TagAttribute vidOtch) {
        this.vidOtch = vidOtch;
    }

    @XmlElement(name = "Kol_Doc")
    public Integer getKolDoc() {
        return kolDoc;
    }

    public void setKolDoc(Integer kolDoc) {
        this.kolDoc = kolDoc;
    }

    @XmlElementWrapper(name = "Docs")
    @XmlElement(name = "Doc")
    public List<DocTag> getDoc() {
        return doc;
    }

    public void setDoc(List<DocTag> doc) {
        this.doc = doc;
    }

    @Override
    public String toString() {
        return "RootTag{" +
                "guidDoc='" + guidDoc + '\'' +
                ", date='" + date + '\'' +
                ", scrs=" + scrs +
                ", vidOtch=" + vidOtch +
                ", kolDoc=" + kolDoc +
                ", doc=" + doc +
                '}';
    }
}