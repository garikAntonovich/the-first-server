package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ZippedFile;

import java.util.List;

public interface XmlParsingService extends FileUploadService {

    void parseXmlFilesByJAXB(List<ZippedFile> xmlFilesList);

    void parseXmlFilesByDOM(List<ZippedFile> xmlFilesList);
}
