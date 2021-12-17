package by.iharantanovich.thefirstserver.parser.jaxb.model.mainXmlFile;

import javax.xml.bind.annotation.XmlElement;

public class StmInfrmtnTf {

    private String guid;

    @XmlElement(name = "GUID")
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "StmInfrmtnTf{" +
                "guid='" + guid + '\'' +
                '}';
    }
}
