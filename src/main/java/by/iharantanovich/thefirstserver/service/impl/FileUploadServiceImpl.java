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

    private static final String MAIN_XML = "</SKP_REPORT_KS>";
    private static final String SUPPLEMENTARY_XML = "</Inf_Pay_Doc>";

    protected List<ZippedFile> zippedFiles = new ArrayList<>();
    private RootMain rootMain;
    private RootSupplementary rootSupplementary;

    @Override
    public List<ZippedFile> saveZippedFiles(MultipartFile file) {

        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {

            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".xml")) {
                    zippedFiles.add(new ZippedFile(zipEntry.getName(), new String(zis.readNBytes((int) zipEntry.getSize()))));
                } else {
                    continue;
                }
                zis.closeEntry();
            }

            return zippedFiles;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void parseZippedFiles(List<ZippedFile> zippedFiles) {

        try {

            for (ZippedFile zippedFile : zippedFiles) {

                if (zippedFile.getData().endsWith(MAIN_XML)) {
                    RootMain mainXml = (RootMain) JAXBContext.newInstance(RootMain.class).createUnmarshaller().
                            unmarshal(new StringReader(zippedFile.getData()));
                    if (!(mainXml.getReportTypeFlag().equals("Итоговая"))) {
                        break;
                    } else {
                        rootMain = mainXml;
                    }
                } else if (zippedFile.getData().endsWith(SUPPLEMENTARY_XML)) {
                    rootSupplementary = (RootSupplementary) JAXBContext.newInstance(RootSupplementary.class).createUnmarshaller().
                            unmarshal(new StringReader(zippedFile.getData()));
                }
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<DataToTransfer> extractData() {

        List<DataToTransfer> dataToTransferList = new ArrayList<>();

        for (int index = 0; index < rootMain.getDoc().size(); index++) {
            if (rootMain.getDoc().get(index).getDocGUID().equals(rootSupplementary.getDoc().get(index).getGuid())) {
                DataToTransfer dataToTransfer = new DataToTransfer();
                dataToTransfer.setDocNum(rootMain.getDoc().get(index).getDocNum());
                dataToTransfer.setDocDate(rootMain.getDoc().get(index).getDocDate());
                dataToTransfer.setDocGUID(rootMain.getDoc().get(index).getDocGUID());
                dataToTransfer.setOperType(rootMain.getDoc().get(index).getOperType());
                dataToTransfer.setAmountOut(rootMain.getDoc().get(index).getAmountOut());
                dataToTransfer.setInfPay(rootSupplementary.getDoc().get(index).getInfPay());
                dataToTransfer.setBankPay(rootSupplementary.getDoc().get(index).getBankPay());
                dataToTransfer.setInfRcp(rootSupplementary.getDoc().get(index).getInfRcp());
                dataToTransfer.setBankRcp(rootSupplementary.getDoc().get(index).getBankRcp());
                dataToTransfer.setPurpose(rootSupplementary.getDoc().get(index).getPurpose());
                dataToTransferList.add(dataToTransfer);
            }
        }

        return dataToTransferList;
    }
}