package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.parser.jaxb.MainXmlRoot;
import by.iharantanovich.thefirstserver.parser.jaxb.SupplementaryXmlRoot;
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
            jaxbContext = JAXBContext.newInstance(MainXmlRoot.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            MainXmlRoot mainXmlRoot = (MainXmlRoot) jaxbUnmarshaller.unmarshal(new StringReader(stringMap.get("Report.xml")));

            System.out.println(mainXmlRoot);


            jaxbContext = JAXBContext.newInstance(SupplementaryXmlRoot.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SupplementaryXmlRoot supplementaryXmlRoot = (SupplementaryXmlRoot) jaxbUnmarshaller.unmarshal(new StringReader(stringMap.get("PayDocs.xml")));

            System.out.println(supplementaryXmlRoot);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
