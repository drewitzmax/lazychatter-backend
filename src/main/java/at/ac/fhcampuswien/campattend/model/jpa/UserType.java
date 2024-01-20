package at.ac.fhcampuswien.campattend.model.jpa;

import java.util.Arrays;

public enum UserType {
    LECTURER,
    STUDENT;
    public static UserType get(String type){
        return Arrays.stream(UserType.values()).filter(e -> e.name().equalsIgnoreCase(type)).findAny().orElse(null);
    }
}
