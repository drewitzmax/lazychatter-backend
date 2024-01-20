package at.ac.fhcampuswien.campattend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRegistrationRequest(@JsonProperty String username, @JsonProperty String password, @JsonProperty String type){ }
