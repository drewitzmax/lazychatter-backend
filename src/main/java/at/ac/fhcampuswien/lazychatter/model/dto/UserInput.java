package at.ac.fhcampuswien.lazychatter.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserInput (@JsonProperty String username, @JsonProperty String password, @JsonProperty String type){ }
