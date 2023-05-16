package com.player.player;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerApplicationTests {
    private static final String CSV_FILE_PATH = "src/main/resources/player.csv";
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void returnsAllPlayersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize((int) number0fPlayersInTheExelFile())));
    }

    private long number0fPlayersInTheExelFile() {
        long rowCount = 0;
        try {
            rowCount = Files.lines(Path.of(CSV_FILE_PATH)).skip(1).count();
            System.out.println("Number of rows: " + rowCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    @Test
    public void returnsSinglePlayerByIDReturnStatusOkTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/players/aardsda01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.playerID").value("aardsda01"))
                .andExpect(jsonPath("$.birthYear").value(1981))
                .andExpect(jsonPath("$.birthMonth").value(12))
                .andExpect(jsonPath("$.birthDay").value(27))
                .andExpect(jsonPath("$.birthCountry").value("USA"))
                .andExpect(jsonPath("$.birthState").value("CO"))
                .andExpect(jsonPath("$.birthCity").value("Denver"))
                .andExpect(jsonPath("$.deathYear").value(Matchers.nullValue()))
                .andExpect(jsonPath("$.deathMonth").value(Matchers.nullValue()))
                .andExpect(jsonPath("$.deathDay").value(Matchers.nullValue()))
                .andExpect(jsonPath("$.deathCountry").value(""))
                .andExpect(jsonPath("$.deathState").value(""))
                .andExpect(jsonPath("$.deathState").value(""))
                .andExpect(jsonPath("$.nameFirst").value("David"))
                .andExpect(jsonPath("$.nameLast").value("Aardsma"))
                .andExpect(jsonPath("$.nameGiven").value("David Allan"))
                .andExpect(jsonPath("$.weight").value(215))
                .andExpect(jsonPath("$.height").value(75))
                .andExpect(jsonPath("$.bats").value("R"))
                .andExpect(jsonPath("$.playerThrows").value("R"))
                .andExpect(jsonPath("$.debut").value("2004-04-06"))
                .andExpect(jsonPath("$.finalGame").value("2015-08-23"))
                .andExpect(jsonPath("$.retroID").value("aardd001"))
                .andExpect(jsonPath("$.bbrefID").value("aardsda01"));
    }

    @Test
    public void returnsSinglePlayerByIDReturnStatusNotFoundTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/players/a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

