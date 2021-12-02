package by.iharantanovich.thefirstserver.service;

import by.iharantanovich.thefirstserver.model.ExtractedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private static final String URL = "http://localhost:8090/post";

    protected RestTemplate restTemplate;

    @Autowired
    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void postData(List<ExtractedData> extractedDataList) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(extractedDataList, headers);
        restTemplate.exchange(URL, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<ExtractedData>>() {});
    }
}
