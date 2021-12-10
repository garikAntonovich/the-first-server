package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ExtractedData;

import java.util.List;

public interface DataTransferService {

    void transferData(List<ExtractedData> extractedDataList);

}
