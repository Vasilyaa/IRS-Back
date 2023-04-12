package net.redciscso.javapmproject.domain.enums;

public enum RoleEnum {
    STUDENT("Студент"),
    TEACHER("Преподаватель"),
    ADMIN("Администратор");

    RoleEnum(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
