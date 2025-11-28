package com.univille.projeto.controller;

import com.univille.projeto.entity.Score;
import com.univille.projeto.repository.ScoreRepository;
import com.univille.projeto.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final AiService ai;
    private final ScoreRepository scoreRepository;

    private Score getScore() {
        return scoreRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> scoreRepository.save(new Score()));
    }

    @PostMapping(value = "/ai-move", consumes = "text/plain", produces = "text/plain")
    public String aiMove(@RequestBody String tabuleiro) {
        return ai.getAiMove(tabuleiro);
    }

    @PostMapping("/update-score")
    public void updateScore(@RequestParam String result) {
        Score score = getScore();

        if (result.equals("X")) {
            score.setVitorias(score.getVitorias() + 1);
        } else if (result.equals("O")) {
            score.setDerrotas(score.getDerrotas() + 1);
        } else if (result.equals("E")) {
            score.setEmpates(score.getEmpates() + 1);
        }

        scoreRepository.save(score);
    }

    @GetMapping("/score")
    public Score getScoreData() {
        return getScore();
    }
}
