package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.DataToTransfer;
import by.iharantanovich.thefirstserver.model.ZippedFile;
import by.iharantanovich.thefirstserver.parser.dom.mainXml.Doc;
import by.iharantanovich.thefirstserver.parser.dom.supplementaryXml.BankPayRcp;
import by.iharantanovich.thefirstserver.parser.dom.supplementaryXml.DocSuppl;
import by.iharantanovich.thefirstserver.parser.dom.supplementaryXml.InfPayRcp;
import by.iharantanovich.thefirstserver.parser.jaxb.mainXmlFile.RootMain;
import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.RootSupplementary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private RestTemplate restTemplate;

    private List<ZippedFile> zippedFileList;
    private List<DataToTransfer> dataToTransferList;
    private RootMain rootMain;
    private RootSupplementary rootSupplementary;
    private List<Doc> docList;
    private List<DocSuppl> docSupplList;

    private static final String URL = "http://localhost:8090/transfer";

    @Override
    public void saveZippedFiles(MultipartFile file) {

        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {

            zippedFileList = new ArrayList<>();
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".xml")) {
                    zippedFileList.add(new ZippedFile(zipEntry.getName(), new String(zis.readNBytes((int) zipEntry.getSize()))));
                } else {
                    continue;
                }
                zis.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseZippedFilesJAXB() {

        try {
            for (ZippedFile zippedFile : zippedFileList) {
                if (zippedFile.getData().endsWith("</SKP_REPORT_KS>")) {
                    RootMain mainXml = (RootMain) JAXBContext.newInstance(RootMain.class).createUnmarshaller().
                            unmarshal(new StringReader(zippedFile.getData()));
                    if (!(mainXml.getReportTypeFlag().equals("Итоговая"))) {
                        break;
                    } else {
                        rootMain = mainXml;
                    }
                } else if (zippedFile.getData().endsWith("</Inf_Pay_Doc>")) {
                    rootSupplementary = (RootSupplementary) JAXBContext.newInstance(RootSupplementary.class).createUnmarshaller().
                            unmarshal(new StringReader(zippedFile.getData()));
                }
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseZippedFilesDom() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        Doc doc;
        DocSuppl docSuppl;
        docList = new ArrayList<>();
        docSupplList = new ArrayList<>();

        for (ZippedFile zippedFile : zippedFileList) {

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

    @Override
    public void saveDataToTransfeFromJAXB() {

        dataToTransferList = new ArrayList<>();

        for (int index = 0; index < rootMain.getDoc().size(); index++) {
            if (rootMain.getDoc().get(index).getDocGUID().equals(rootSupplementary.getDoc().get(index).getGuid())) {
                DataToTransfer dataToTransfer = new DataToTransfer(
                        rootMain.getDoc().get(index).getDocNum(),
                        rootMain.getDoc().get(index).getDocDate(),
                        rootMain.getDoc().get(index).getDocGUID(),
                        rootMain.getDoc().get(index).getOperType(),
                        rootMain.getDoc().get(index).getAmountOut(),
                        rootSupplementary.getDoc().get(index).getInfPay(),
                        rootSupplementary.getDoc().get(index).getBankPay(),
                        rootSupplementary.getDoc().get(index).getInfRcp(),
                        rootSupplementary.getDoc().get(index).getBankRcp(),
                        rootSupplementary.getDoc().get(index).getPurpose()
                );
                dataToTransferList.add(dataToTransfer);
            }
        }
    }

    @Override
    public void saveDataToTransfeFromDom() {
        dataToTransferList = new ArrayList<>();

        for (int index = 0; index < docList.size(); index++) {
            if (docList.get(index).getDocGUID().equalsIgnoreCase(docSupplList.get(index).getGuid())) {
                DataToTransfer dataToTransfer = new DataToTransfer(
                        docList.get(index).getDocNum(),
                        docList.get(index).getDocDate(),
                        docList.get(index).getDocGUID(),
                        docList.get(index).getOperType(),
                        docList.get(index).getAmountOut(),
                        docSupplList.get(index).getInfPay(),
                        docSupplList.get(index).getBankPay(),
                        docSupplList.get(index).getInfRcp(),
                        docSupplList.get(index).getBankRcp(),
                        docSupplList.get(index).getPurpose()
                );
                dataToTransferList.add(dataToTransfer);
            }
        }
    }

    @Override
    public void transferData() {
        HttpEntity<Object> requestEntity = new HttpEntity<>(dataToTransferList, new HttpHeaders());
        restTemplate.exchange(URL, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<DataToTransfer>>() {
        });
    }

    @Override
    public void processingFile(MultipartFile file) {
        saveZippedFiles(file);
//        parseZippedFilesJAXB();
        parseZippedFilesDom();
//        saveDataToTransfeFromJAXB();
        saveDataToTransfeFromDom();
    }
}