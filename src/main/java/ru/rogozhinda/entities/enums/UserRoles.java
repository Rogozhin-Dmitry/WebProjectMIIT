package ru.rogozhinda.entities.enums;

public enum UserRoles {
    USER(0), MODERATOR(1), ADMIN(2);

    private final int value;

    UserRoles(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
