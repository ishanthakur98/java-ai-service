package com.ai.service.service;

import com.ai.service.model.GeminiRequest;
import com.ai.service.model.GeminiResponse;
import io.netty.util.internal.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiService {

    @Value("${ai.api-key}")
    private String API_KEY;

    @Value("${ai.model}")
    private String AI_MODEL;

    private final String CONTENT_TYPE = "application/json";

    private final WebClient webClient;

    public String getResponse(String text){
        GeminiRequest geminiRequest = new GeminiRequest();
        GeminiRequest.Content content = new GeminiRequest.Content();
        GeminiRequest.Parts parts = new GeminiRequest.Parts();
        parts.setText(text);
        content.setParts(List.of(parts));
        geminiRequest.setContents(List.of(content));
        log.info("API_KEY: {}",API_KEY);

        GeminiResponse response = webClient.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/"+ AI_MODEL + ":generateContent")
                .bodyValue(geminiRequest)
                .headers(httpHeaders -> httpHeaders.addAll(getHeaders())).retrieve()
                .bodyToMono(GeminiResponse.class).block();
        log.info("response: "+response);
        if(response != null && !response.getCandidates().isEmpty() &&
        response.getCandidates().get(0).getContent() != null &&
        !response.getCandidates().get(0).getContent().getParts().isEmpty()){
            return response.getCandidates().get(0).
                    getContent().getParts().get(0).
                    getText();
        }
        return "Unable to get response. Please try again!!";

    }

    public HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-goog-api-key",API_KEY);
        headers.add("Content-Type",CONTENT_TYPE);
        return headers;
    }
}
