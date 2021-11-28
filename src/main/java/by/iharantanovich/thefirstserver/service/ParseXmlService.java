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

    public void parseXmlFiles(Map<String, String> stringMap) {

        try {
            for (Map.Entry<String, String> item : stringMap.entrySet()) {
                if (item.getValue().endsWith("</SKP_REPORT_KS>")) {
                    Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(RootTag.class).createUnmarshaller();
                    RootTag mainXmlRootTag = (RootTag) jaxbUnmarshaller.unmarshal(new StringReader(stringMap.get(item.getKey())));
                    System.out.println(mainXmlRootTag);
                }
                if (item.getValue().endsWith("</Inf_Pay_Doc>")) {
                    Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(RootTagS.class).createUnmarshaller();
                    RootTagS supplementaryXmlRootTag = (RootTagS) jaxbUnmarshaller.unmarshal(new StringReader(stringMap.get(item.getKey())));
                    System.out.println(supplementaryXmlRootTag);
                }
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
