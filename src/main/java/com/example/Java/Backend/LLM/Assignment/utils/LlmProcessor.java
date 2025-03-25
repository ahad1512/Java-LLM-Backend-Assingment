package com.example.Java.Backend.LLM.Assignment.utils;


import com.example.Java.Backend.LLM.Assignment.model.ExtractedData;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LlmProcessor {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    public ExtractedData processTextWithLLM(String extractedText) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        JSONObject request = new JSONObject();
        request.put("model", "gpt-4");
        request.put("messages", new JSONObject[]{
                new JSONObject().put("role", "system").put("content", "Extract Name, Email, Opening Balance, and Closing Balance from the text."),
                new JSONObject().put("role", "user").put("content", extractedText)
        });

        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(OPENAI_URL, HttpMethod.POST, entity, String.class);
        JSONObject jsonResponse = new JSONObject(response.getBody());

        String name = jsonResponse.getJSONObject("choices").getJSONArray("text").getJSONObject(0).getString("name");
        String email = jsonResponse.getJSONObject("choices").getJSONArray("text").getJSONObject(0).getString("email");
        String openingBalance = jsonResponse.getJSONObject("choices").getJSONArray("text").getJSONObject(0).getString("openingBalance");
        String closingBalance = jsonResponse.getJSONObject("choices").getJSONArray("text").getJSONObject(0).getString("closingBalance");

        return new ExtractedData(name, email, openingBalance, closingBalance, null);
    }
}

