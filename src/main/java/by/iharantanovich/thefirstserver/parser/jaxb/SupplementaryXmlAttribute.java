package by.iharantanovich.thefirstserver.parser.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class SupplementaryXmlAttribute {

    @XmlAttribute
    private String value;

    @Override
    public String toString() {
        return "SupplementaryXmlAttribute{" +
                "value='" + value + '\'' +
                '}';
    }
}
