package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.RootTag;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Map;

@Service
public class XmlService {

    public void storageXml(Map<String, String> stringMap) {

        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(by.iharantanovich.thefirstserver.parser.jaxb.mainXmlFile.RootTag.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            by.iharantanovich.thefirstserver.parser.jaxb.mainXmlFile.RootTag mainXmlRootTag = (by.iharantanovich.thefirstserver.parser.jaxb.mainXmlFile.RootTag) jaxbUnmarshaller.unmarshal(new StringReader(stringMap.get("Report.xml")));

            System.out.println(mainXmlRootTag);


            jaxbContext = JAXBContext.newInstance(RootTag.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            RootTag supplementaryXmlRootTag = (RootTag) jaxbUnmarshaller.unmarshal(new StringReader(stringMap.get("PayDocs.xml")));

            System.out.println(supplementaryXmlRootTag);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
