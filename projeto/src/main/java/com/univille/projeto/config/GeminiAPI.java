package com.univille.projeto.config;

import com.google.genai.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiAPI {

    @Bean
    public Client geminiClient(){
        return new Client();
    }
}
