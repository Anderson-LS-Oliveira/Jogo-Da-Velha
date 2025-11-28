package com.univille.projeto.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {

    private final Client client;

    public String getAiMove(String tabuleiro) {

        String prompt =
                "Contexto: estamos jogando jogo da velha." +
                        "Jogue para vencer, mas as vezes, tente não fazer a jogada matematicamente perfeita," +
                        " adicionando pequenos erros numa porcentagem de 30% aproximadamente " +
                        "O tabuleiro é uma string de 9 caracteres usando X, O e _. Exemplo: X_O____X_" +
                        "responda APENAS com os números entre 0 e 8 representando a jogada ecolhida." +
                        "Não explique, não adicione texto, não responda nada que não seja o número." +
                        "Tabuleiro: " + tabuleiro;

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        prompt,
                        null
                );

        String text = response.text().trim();

        for (char c : text.toCharArray()) {
            if (c >= '0' && c <= '8') {
                return String.valueOf(c);
            }
        }

        return "0";
    }
}
