package by.iharantanovich.thefirstserver.parser.jaxb.model.supplementaryXmlFile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class TagAttribute {

    @XmlAttribute
    private String value;

    @Override
    public String toString() {
        return "TagAttribute{" +
                "value='" + value + '\'' +
                '}';
    }
}
