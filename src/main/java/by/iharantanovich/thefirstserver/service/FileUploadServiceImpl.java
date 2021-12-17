package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.DataToTransfer;
import by.iharantanovich.thefirstserver.model.ZippedFile;
import by.iharantanovich.thefirstserver.parser.dom.mainXml.Doc;
import by.iharantanovich.thefirstserver.parser.dom.supplementaryXml.DocSuppl;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = null;

        for (ZippedFile zippedFile : zippedFileList) {
            try {
                document = documentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(zippedFile.getData())));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Node rootNode = document.getFirstChild();
            NodeList rootChilds = rootNode.getChildNodes();
            Node docsNode = null;
            for (int index = 0; index < rootChilds.getLength(); index++) {
                if (rootChilds.item(index).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                if (rootChilds.item(index).getNodeName().equals("Docs")) {
                    docsNode = rootChilds.item(index);
                }
            }

            if (docsNode == null) {
                return;
            }

            NodeList docsChilds = docsNode.getChildNodes();

            List<Doc> docList = new ArrayList<>();
            List<DocSuppl> docSupplList = new ArrayList<>();

            for (int index = 0; index < docsChilds.getLength(); index++) {
                if (docsChilds.item(index).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                if (!docsChilds.item(index).getNodeName().equals("Doc")) {
                    continue;
                }

                Doc doc = null;
                DocSuppl docSuppl = null;
                NodeList docChilds = docsChilds.item(index).getChildNodes();

                for (int jindex = 0; jindex < docChilds.getLength(); jindex++) {
                    if (docChilds.item(index).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

                    if (zippedFile.getData().endsWith("</SKP_REPORT_KS>")) {
                        doc = new Doc();
                        switch (docChilds.item(jindex).getNodeName()) {
                            case "DocNum":
                                doc.setDocNum(docChilds.item(jindex).getTextContent());
                                break;
                            case "DocDate":
                                doc.setDocDate(docChilds.item(jindex).getTextContent());
                                break;
                            case "DocGUID":
                                doc.setDocGUID(docChilds.item(jindex).getTextContent());
                                break;
                            case "OperType":
                                doc.setOperType(docChilds.item(jindex).getTextContent());
                                break;
                            case "AmountOut":
                                doc.setAmountOut(Double.valueOf(docChilds.item(jindex).getTextContent()));
                                break;
                        }
                    }

                    if (zippedFile.getData().endsWith("</Inf_Pay_Doc>")) {
                        docSuppl = new DocSuppl();

                        if (docChilds.item(jindex).getNodeName().startsWith("Inf")) {
                            NodeList infPayRcpChilds = docChilds.item(jindex).getChildNodes();
                            for (int kindex = 0; kindex < infPayRcpChilds.getLength(); kindex++) {
                                if (infPayRcpChilds.item(index).getNodeType() != Node.ELEMENT_NODE) {
                                    continue;
                                }
                                switch (infPayRcpChilds.item(kindex).getNodeName()) {
                                    case "INN_PAY":
                                        if (infPayRcpChilds.item(kindex).getNodeName().equals("Inf_PAY"))
                                            docSuppl.getInfPay().setInnPay(infPayRcpChilds.item(kindex).getTextContent());
                                        if (infPayRcpChilds.item(kindex).getNodeName().equals("Inf_RCP"))
                                            docSuppl.getInfRcp().setInnPay(infPayRcpChilds.item(kindex).getTextContent());
                                        break;
                                    case "KPP_PAY":
                                        if (infPayRcpChilds.item(kindex).getNodeName().equals("Inf_PAY"))
                                            docSuppl.getInfPay().setKppPay(infPayRcpChilds.item(kindex).getTextContent());
                                        if (infPayRcpChilds.item(kindex).getNodeName().equals("Inf_RCP"))
                                            docSuppl.getInfRcp().setKppPay(infPayRcpChilds.item(kindex).getTextContent());
                                        break;
                                    case "CName_PAY":
                                        if (infPayRcpChilds.item(kindex).getNodeName().equals("Inf_PAY"))
                                            docSuppl.getInfPay().setcNamePay(infPayRcpChilds.item(kindex).getTextContent());
                                        if (infPayRcpChilds.item(kindex).getNodeName().equals("Inf_RCP"))
                                            docSuppl.getInfRcp().setcNamePay(infPayRcpChilds.item(kindex).getTextContent());
                                        break;
                                }
                            }
                        } else if (docChilds.item(jindex).getNodeName().startsWith("Bank")) {
                            NodeList bankPayRcpChilds = docChilds.item(jindex).getChildNodes();
                            for (int kindex = 0; kindex < bankPayRcpChilds.getLength(); kindex++) {
                                if (bankPayRcpChilds.item(index).getNodeType() != Node.ELEMENT_NODE) {
                                    continue;
                                }
                                switch (bankPayRcpChilds.item(kindex).getNodeName()) {
                                    case "BS_PAY":
                                        if (bankPayRcpChilds.item(kindex).getNodeName().equals("Bank_PAY"))
                                            docSuppl.getBankPay().setBsPay(bankPayRcpChilds.item(kindex).getTextContent());
                                        if (bankPayRcpChilds.item(kindex).getNodeName().equals("Bank_RCP"))
                                            docSuppl.getBankRcp().setBsPay(bankPayRcpChilds.item(kindex).getTextContent());
                                        break;
                                    case "BIC_PAY":
                                        if (bankPayRcpChilds.item(kindex).getNodeName().equals("Bank_PAY"))
                                            docSuppl.getBankPay().setBicPay(bankPayRcpChilds.item(kindex).getTextContent());
                                        if (bankPayRcpChilds.item(kindex).getNodeName().equals("Bank_RCP"))
                                            docSuppl.getBankRcp().setBicPay(bankPayRcpChilds.item(kindex).getTextContent());
                                        break;
                                    case "BS_KS_PAY":
                                        if (bankPayRcpChilds.item(kindex).getNodeName().equals("Bank_PAY"))
                                            docSuppl.getBankPay().setBsKsPay(bankPayRcpChilds.item(kindex).getTextContent());
                                        if (bankPayRcpChilds.item(kindex).getNodeName().equals("Bank_RCP"))
                                            docSuppl.getBankRcp().setBsKsPay(bankPayRcpChilds.item(kindex).getTextContent());
                                        break;
                                }
                            }
                        }
                    }
                }
                docList.add(doc);
            }
            System.out.println(docList);
        }
    }

    @Override
    public void saveDataToTransfeFromJAXB() {

        dataToTransferList = new ArrayList<>();

        for (int index = 0; index < rootMain.getDoc().size(); index++) {
            if (rootMain.getDoc().get(index).getDocGUID().equals(rootSupplementary.getDoc().get(index).getGuid())) {
                DataToTransfer dataToTransfer = new DataToTransfer(
                        rootMain.getDoc().get(index).getDocNum(), rootMain.getDoc().get(index).getDocDate(),
                        rootMain.getDoc().get(index).getDocGUID(), rootMain.getDoc().get(index).getOperType(),
                        rootMain.getDoc().get(index).getAmountOut(),
                        rootSupplementary.getDoc().get(index).getInfPay(),
                        rootSupplementary.getDoc().get(index).getBankPay(),
                        rootSupplementary.getDoc().get(index).getInfRcp(),
                        rootSupplementary.getDoc().get(index).getBankRcp(),
                        rootSupplementary.getDoc().get(index).getPurpose());
                dataToTransferList.add(dataToTransfer);
            }
        }
    }

    @Override
    public void saveDataToTransfeFromDom() {

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
        parseZippedFilesJAXB();
        parseZippedFilesDom();
//        saveDataToTransfeFromJAXB();
    }
}