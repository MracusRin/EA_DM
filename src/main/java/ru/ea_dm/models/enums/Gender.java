package ru.ea_dm.models.enums;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGenderName() {
        return this.gender;
    }
}
