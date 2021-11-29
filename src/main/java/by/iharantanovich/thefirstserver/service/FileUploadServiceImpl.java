package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ZippedFile;
import by.iharantanovich.thefirstserver.parser.jaxb.mainXmlFile.RootTag;
import by.iharantanovich.thefirstserver.parser.jaxb.supplementaryXmlFile.RootTagS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Map<String, String> getZippedFiles(List<ZippedFile> zippedFileList) {

        Map<String, String> zippedFileMap = new HashMap<>();

        for (ZippedFile zippedFile : zippedFileList) {
            zippedFileMap.put(zippedFile.getFileName(), zippedFile.getData());
        }

        return zippedFileMap;
    }

    @Override
    public void parseZippedFiles(Map<String, String> zippedFilesMap) {

        try {

            for (Map.Entry<String, String> item : zippedFilesMap.entrySet()) {
                if (item.getValue().endsWith(MAIN_XML)) {
                    RootTag mainXml = (RootTag) JAXBContext.newInstance(RootTag.class).createUnmarshaller().
                            unmarshal(new StringReader(zippedFilesMap.get(item.getKey())));
                    System.out.println(mainXml);
                }
                if (item.getValue().endsWith(SUPPLEMENTARY_XML)) {
                    RootTagS supplementaryXml = (RootTagS) JAXBContext.newInstance(RootTagS.class).createUnmarshaller().
                            unmarshal(new StringReader(zippedFilesMap.get(item.getKey())));
                    System.out.println(supplementaryXml);
                }
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}