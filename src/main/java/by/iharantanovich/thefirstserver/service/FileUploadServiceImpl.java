package by.iharantanovich.thefirstserver.service;

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
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final String MAIN_XML = "</SKP_REPORT_KS>";
    private static final String SUPPLEMENTARY_XML = "</Inf_Pay_Doc>";
    protected List<ZippedFile> zippedFiles;

    @Autowired
    public FileUploadServiceImpl(List<ZippedFile> zippedFiles) {
        this.zippedFiles = zippedFiles;
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
                        System.out.println(mainXml);
                    }
                } else if (zippedFile.getData().endsWith(SUPPLEMENTARY_XML)) {
                    RootSupplementary suppXml = (RootSupplementary) JAXBContext.newInstance(RootSupplementary.class).createUnmarshaller().
                            unmarshal(new StringReader(zippedFile.getData()));
                    System.out.println(suppXml);
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}