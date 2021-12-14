package by.iharantanovich.thefirstserver.service.impl;

import by.iharantanovich.thefirstserver.model.DataToTransfer;
import by.iharantanovich.thefirstserver.model.ZippedFile;
import by.iharantanovich.thefirstserver.parser.jaxb.mainXmlFile.RootMain;
import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.RootSupplementary;
import by.iharantanovich.thefirstserver.service.FileUploadService;
import org.springframework.stereotype.Service;
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

    private List<ZippedFile> zippedFileList;
    private RootMain rootMain;
    private RootSupplementary rootSupplementary;

    @Override
    public List<DataToTransfer> processingFile(MultipartFile file) {
        saveZippedFiles(file);
        parseZippedFiles(zippedFileList);
        return extractData();
    }

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
    public void parseZippedFiles(List<ZippedFile> zippedFiles) {

        try {

            for (ZippedFile zippedFile : zippedFiles) {

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
    public List<DataToTransfer> extractData() {

        List<DataToTransfer> dataToTransferList = new ArrayList<>();

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

        return dataToTransferList;
    }
}