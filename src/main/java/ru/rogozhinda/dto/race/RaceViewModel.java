package ru.rogozhinda.dto.race;

import java.util.Date;

public record RaceViewModel(String name, String location, Integer duration, Date date) {
}
