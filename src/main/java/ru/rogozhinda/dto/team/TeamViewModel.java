package ru.rogozhinda.dto.team;

import java.util.Date;

public record TeamViewModel(String name, String country, Integer trophies, Integer points, Date founded) {
}
