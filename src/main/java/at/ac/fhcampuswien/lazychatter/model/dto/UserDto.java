package at.ac.fhcampuswien.lazychatter.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserDto {
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;
    @JsonProperty
    private List<String> chatIds;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getChatIds() {
        return chatIds;
    }
}
