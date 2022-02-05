package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ZippedFile;
import by.iharantanovich.thefirstserver.parser.dom.mainXml.Doc;
import by.iharantanovich.thefirstserver.parser.dom.supplementaryXml.BankPayRcp;
import by.iharantanovich.thefirstserver.parser.dom.supplementaryXml.DocSuppl;
import by.iharantanovich.thefirstserver.parser.dom.supplementaryXml.InfPayRcp;
import by.iharantanovich.thefirstserver.parser.jaxb.mainXmlFile.RootMain;
import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.RootSupplementary;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlParsingServiceImpl implements XmlParsingService {

    @Override
    public void parseXmlFilesByJAXB(List<ZippedFile> xmlFilesList) {

        try {
            for (ZippedFile zippedFile : xmlFilesList) {
                if (zippedFile.getData().endsWith("</SKP_REPORT_KS>")) {
                    RootMain mainXml = (RootMain) JAXBContext.newInstance(RootMain.class).createUnmarshaller().
                            unmarshal(new StringReader(zippedFile.getData()));
                    if (!(mainXml.getReportTypeFlag().equals("Итоговая"))) {
                        break;
                    }
                } else if (zippedFile.getData().endsWith("</Inf_Pay_Doc>")) {
                    RootSupplementary rootSupplementary = (RootSupplementary) JAXBContext.newInstance(RootSupplementary.class).createUnmarshaller().
                            unmarshal(new StringReader(zippedFile.getData()));
                }
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseXmlFilesByDOM(List<ZippedFile> xmlFilesList) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        Doc doc;
        DocSuppl docSuppl;
        List<Doc> docList = new ArrayList<>();
        List<DocSuppl> docSupplList = new ArrayList<>();

        for (ZippedFile zippedFile : xmlFilesList) {

            try {
                builder = factory.newDocumentBuilder();
                document = builder.parse(new InputSource(new StringReader(zippedFile.getData())));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (zippedFile.getData().endsWith("</SKP_REPORT_KS>")) {

                NodeList docNumElements = document.getDocumentElement().getElementsByTagName("DocNum");
                NodeList docDateElements = document.getDocumentElement().getElementsByTagName("DocDate");
                NodeList docGUIDElements = document.getDocumentElement().getElementsByTagName("DocGUID");
                NodeList operTypeElements = document.getDocumentElement().getElementsByTagName("OperType");
                NodeList amountOutElements = document.getDocumentElement().getElementsByTagName("AmountOut");

                for (int index = 0; index < operTypeElements.getLength(); index++) {
                    doc = new Doc();
                    doc.setDocNum(docNumElements.item(index + 1).getTextContent());
                    doc.setDocDate(docDateElements.item(index).getTextContent());
                    doc.setDocGUID(docGUIDElements.item(index).getTextContent());
                    doc.setOperType(operTypeElements.item(index).getTextContent());
                    doc.setAmountOut(Double.valueOf(amountOutElements.item(index).getTextContent()));

                    docList.add(doc);
                }
            }

            if (zippedFile.getData().endsWith("</Inf_Pay_Doc>")) {

                NodeList innPayElements = document.getDocumentElement().getElementsByTagName("INN_PAY");
                NodeList kppPayElements = document.getDocumentElement().getElementsByTagName("KPP_PAY");
                NodeList cNamePayElements = document.getDocumentElement().getElementsByTagName("CName_PAY");
                NodeList bsPayElements = document.getDocumentElement().getElementsByTagName("BS_PAY");
                NodeList bicPayElements = document.getDocumentElement().getElementsByTagName("BIC_PAY");
                NodeList bsKsPayElements = document.getDocumentElement().getElementsByTagName("BS_KS_PAY");
                NodeList purposeElements = document.getDocumentElement().getElementsByTagName("Purpose");
                NodeList giudElements = document.getDocumentElement().getElementsByTagName("GUID");

                for (int index = 0; index < innPayElements.getLength(); index += 2) {

                    docSuppl = new DocSuppl();
                    docSuppl.setInfPay(new InfPayRcp(innPayElements.item(index).getTextContent(),
                            kppPayElements.item(index).getTextContent(), cNamePayElements.item(index).getTextContent()));
                    docSuppl.setInfRcp(new InfPayRcp(innPayElements.item(index + 1).getTextContent(),
                            kppPayElements.item(index + 1).getTextContent(), cNamePayElements.item(index + 1).getTextContent()));
                    docSuppl.setBankPay(new BankPayRcp(bsPayElements.item(index).getTextContent(),
                            bicPayElements.item(index).getTextContent(), bsKsPayElements.item(index).getTextContent()));
                    docSuppl.setBankRcp(new BankPayRcp(bsPayElements.item(index + 1).getTextContent(),
                            bicPayElements.item(index + 1).getTextContent(), bsKsPayElements.item(index + 1).getTextContent()));

                    if (index == 0) {
                        docSuppl.setPurpose(purposeElements.item(index).getTextContent());
                        docSuppl.setGuid(giudElements.item(index).getTextContent());
                    } else {
                        docSuppl.setPurpose(purposeElements.item(index / 2).getTextContent());
                        docSuppl.setGuid(giudElements.item(index / 2).getTextContent());
                    }

                    docSupplList.add(docSuppl);
                }
            }
        }
    }
}
