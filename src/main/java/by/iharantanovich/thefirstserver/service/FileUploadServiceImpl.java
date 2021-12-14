package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.DataToTransfer;
import by.iharantanovich.thefirstserver.model.ZippedFile;
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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
    public void parseZippedFiles() {

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
    public void extractData() {

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
    public void transferData() {
        HttpEntity<Object> requestEntity = new HttpEntity<>(dataToTransferList, new HttpHeaders());
        restTemplate.exchange(URL, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<DataToTransfer>>() {});
    }

    @Override
    public void processingFile(MultipartFile file) {
        saveZippedFiles(file);
        parseZippedFiles();
        extractData();
    }
}