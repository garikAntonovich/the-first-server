package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.parser.jaxb.mainXmlFile.RootTag;
import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.RootTagS;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Map;

@Service
public class ParseXmlService {

    public void storageXml(Map<String, String> stringMap) {

        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(RootTag.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            RootTag mainXmlRootTag = (RootTag) jaxbUnmarshaller.unmarshal(new StringReader(stringMap.get("Report.xml")));

            jaxbContext = JAXBContext.newInstance(RootTagS.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            RootTagS supplementaryXmlRootTag = (RootTagS) jaxbUnmarshaller.unmarshal(new StringReader(stringMap.get("PayDocs.xml")));

            System.out.println(mainXmlRootTag);
            System.out.println(supplementaryXmlRootTag);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
