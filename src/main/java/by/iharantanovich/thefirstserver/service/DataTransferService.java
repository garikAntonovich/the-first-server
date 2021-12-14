package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.DataToTransfer;

import java.util.List;

public interface DataTransferService {

    void transferData(List<DataToTransfer> extractedDataList);
}
