package com.email.email_writer_sb.service;


import java.util.Map;

// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.JSONObject;
import org.json.JSONArray;

import com.email.email_writer_sb.entity.EmailRequest;


@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    @Value("${url}")
    private String url;
    
    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();  // Correctly initializing WebClient
    }

    public String generateEmailReply(EmailRequest emailRequest){

        String prompt = buildPrompt(emailRequest);

        // Request Format
        // {
        //     "contents": [{
        //       "parts":[{"text": prompt}]
        //       }]
        // }
        Map<String, Object> requestBody = Map.of(
            "contents", new Object[]{
                Map.of("parts", new Object[]{
                    Map.of("text", prompt)
                })
            }
        );

        String response = webClient.post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(String.class)
            .block(); // If you want to get the response synchronously


        JSONObject jsonObject = new JSONObject(response);
        JSONArray candidatesArray = jsonObject.getJSONArray("candidates");
        
        JSONObject firstCandidate = candidatesArray.getJSONObject(0);
        JSONObject content = firstCandidate.getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");
            
        JSONObject firstPart = parts.getJSONObject(0);
        String extractedText = firstPart.getString("text");
        
        return extractedText;

    }

    public String buildPrompt(EmailRequest emailRequest){
        
        String content = emailRequest.getContent();
        String tone = emailRequest.getTone();
        
        StringBuilder prompt = new StringBuilder();

        prompt.append(content);
        prompt.append("Above is the email which I have Got. I have to reply above email in a");
        prompt.append(tone);
        prompt.append("tone");
        prompt.append("Do not give me options only give one answer and do not add any extra word. I will copy the content and reply it");

        return prompt.toString();

    }
    
}
