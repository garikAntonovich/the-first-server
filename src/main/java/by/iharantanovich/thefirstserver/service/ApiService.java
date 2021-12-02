package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ExtractedData;

import java.util.List;

public interface ApiService {

    void postData(List<ExtractedData> extractedDataList);

}
