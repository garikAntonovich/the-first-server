package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.parser.jaxb.MainXmlRoot;
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
            MainXmlRoot firstRoot = (MainXmlRoot) jaxbUnmarshaller.unmarshal(new StringReader(stringMap.get("Report.xml")));

            System.out.println(firstRoot);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
