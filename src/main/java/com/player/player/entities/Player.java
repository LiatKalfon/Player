package com.player.player.entities;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class Player {
    private String playerID;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private Integer deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private Integer weight;
    private Integer height;
    private Bats bats;
    private Throws playerThrows;
    private LocalDate debut;
    private LocalDate finalGame;
    private String retroID;
    private String bbrefID;

    public Player(String[] line) {
        this.playerID = line[0];
        this.birthYear = parseInteger(line[1]);
        this.birthMonth = parseInteger(line[2]);
        this.birthDay = parseInteger(line[3]);
        this.birthCountry = line[4];
        this.birthState = line[5];
        this.birthCity = line[6];
        this.deathYear = parseInteger(line[7]);
        this.deathMonth = parseInteger(line[8]);
        this.deathDay = parseInteger(line[9]);
        this.deathCountry = line[10];
        this.deathState = line[11];
        this.deathCity = line[12];
        this.nameFirst = line[13];
        this.nameLast = line[14];
        this.nameGiven = line[15];
        this.weight = parseInteger(line[16]);
        this.height = parseInteger(line[17]);
        this.bats = parseEnum(line[18], Bats.class);
        this.playerThrows = parseEnum(line[19], Throws.class);
        this.debut = parseDate(line[20]);
        this.finalGame = parseDate(line[21]);
        this.retroID = line[22];
        this.bbrefID = line[23];
    }

    public Player() {
    }

//    public Player(String[] line) {
//        new Player(line[0],parseInteger(line[1]),parseInteger(line[2]),parseInteger(line[3]),line[4],line[5],line[6],parseInteger(line[7]),
//        parseInteger(line[8]),parseInteger(line[9]),line[10],line[11],line[12],line[13],line[14],
//        line[15],
//                parseInteger(line[16]),
//                parseInteger(line[17]),
//                parseEnum(line[18], Bats.class),
//                parseEnum(line[19], Throws.class),
//        parseDate(line[20]),parseDate(line[21]),line[22],line[23]);
//    }
    private Player mapToPlayer(String[] line) {
        Player player = new Player();

        try {
            Field[] fields = Player.class.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                String value = line[i];

                if (field.getName().equals("bats")) {
                    field.set(player, parseEnum(value, Player.Bats.class));
                } else if (field.getName().equals("playerThrows")) {
                    field.set(player, parseEnum(value, Player.Throws.class));
                } else if (field.getType() == Integer.class) {
                    if (!value.isEmpty()) {
                        field.set(player, Integer.parseInt(value));
                    }
                } else if (field.getType() == LocalDate.class) {
                    if (!value.isEmpty()) {
                        field.set(player, LocalDate.parse(value));
                    }
                } else {
                    field.set(player, value);
                }
            }
        } catch (Exception e) {
            // Handle any potential exceptions
        }

        return player;
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDate parseDate(String value) {
        if (value.isEmpty()) {
            return null;
        }
        return LocalDate.parse(value);
    }

    private <E extends Enum<E>> E parseEnum(String value, Class<E> enumClass) {
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            // Handle invalid value, return a default value or throw an exception
            return null; // Or you can set a default value, e.g., enumClass.getEnumConstants()[0]
        }
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getBirthState() {
        return birthState;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public Integer getDeathMonth() {
        return deathMonth;
    }

    public void setDeathMonth(Integer deathMonth) {
        this.deathMonth = deathMonth;
    }

    public Integer getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(Integer deathDay) {
        this.deathDay = deathDay;
    }

    public String getDeathCountry() {
        return deathCountry;
    }

    public void setDeathCountry(String deathCountry) {
        this.deathCountry = deathCountry;
    }

    public String getDeathState() {
        return deathState;
    }

    public void setDeathState(String deathState) {
        this.deathState = deathState;
    }

    public String getDeathCity() {
        return deathCity;
    }

    public void setDeathCity(String deathCity) {
        this.deathCity = deathCity;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getNameGiven() {
        return nameGiven;
    }

    public void setNameGiven(String nameGiven) {
        this.nameGiven = nameGiven;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Bats getBats() {
        return bats;
    }

    public void setBats(Bats bats) {
        this.bats = bats;
    }

    public Throws getPlayerThrows() {
        return playerThrows;
    }

    public void setPlayerThrows(Throws playerThrows) {
        this.playerThrows = playerThrows;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFinalGame() {
        return finalGame;
    }

    public void setFinalGame(LocalDate finalGame) {
        this.finalGame = finalGame;
    }

    public String getRetroID() {
        return retroID;
    }

    public void setRetroID(String retroID) {
        this.retroID = retroID;
    }

    public String getBbrefID() {
        return bbrefID;
    }

    public void setBbrefID(String bbrefID) {
        this.bbrefID = bbrefID;
    }

    public enum Bats {
        L, R
    }

    public enum Throws {
        L, R
    }
}
