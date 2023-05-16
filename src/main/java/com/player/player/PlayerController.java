package com.player.player;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.player.player.entities.Player;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private static final String CSV_FILE_PATH = "player.csv";

    @GetMapping
    public List<Player> getAllPlayers() throws IOException {
        List<Player> players = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource(CSV_FILE_PATH);
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(resource.getInputStream()))
                .withSkipLines(1) // Skip the first line - the titles
                .build()) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                Player player = new Player(line);
                players.add(player);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    @GetMapping("/{playerID}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("playerID") String id) throws IOException {
        ClassPathResource resource = new ClassPathResource(CSV_FILE_PATH);
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String playerId = line[0];
                if (playerId.equals(id)) {
                    return ResponseEntity.ok(new Player(line));
                }
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.notFound().build();
    }

}
