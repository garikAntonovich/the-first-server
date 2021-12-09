package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ExtractedData;
import by.iharantanovich.thefirstserver.model.ZippedFile;
import by.iharantanovich.thefirstserver.parser.jaxb.mainXmlFile.RootMain;
import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.RootSupplementary;
import org.springframework.beans.factory.annotation.Autowired;
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
    protected List<ZippedFile> zippedFiles;
    protected RootMain rootMain;
    protected RootSupplementary rootSupplementary;
    protected ExtractedData extractedData;

    @Autowired
    public FileUploadServiceImpl(List<ZippedFile> zippedFiles, RootMain rootMain, RootSupplementary rootSupplementary,
                                 ExtractedData extractedData) {
        this.zippedFiles = zippedFiles;
        this.rootMain = rootMain;
        this.rootSupplementary = rootSupplementary;
        this.extractedData = extractedData;
    }

    @Override
    public List<ZippedFile> saveZippedFiles(MultipartFile file) {

        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {

            ZipEntry zipEntry;
            zippedFiles.remove(0);

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

    public List<ExtractedData> extractData() {

        List<ExtractedData> extractedDataList = new ArrayList<>();

        for (int index = 0; index < rootMain.getDoc().size(); index++) {
            if (rootMain.getDoc().get(index).getDocGUID().equals(rootSupplementary.getDoc().get(index).getGuid())) {
                extractedData = new ExtractedData();
                extractedData.setDocNum(rootMain.getDoc().get(index).getDocNum());
                extractedData.setDocDate(rootMain.getDoc().get(index).getDocDate());
                extractedData.setDocGUID(rootMain.getDoc().get(index).getDocGUID());
                extractedData.setOperType(rootMain.getDoc().get(index).getOperType());
                extractedData.setAmountOut(rootMain.getDoc().get(index).getAmountOut());
                extractedData.setInfPay(rootSupplementary.getDoc().get(index).getInfPay());
                extractedData.setBankPay(rootSupplementary.getDoc().get(index).getBankPay());
                extractedData.setInfRcp(rootSupplementary.getDoc().get(index).getInfRcp());
                extractedData.setBankRcp(rootSupplementary.getDoc().get(index).getBankRcp());
                extractedData.setPurpose(rootSupplementary.getDoc().get(index).getPurpose());
                extractedDataList.add(extractedData);
            }
        }

        return extractedDataList;
    }
}