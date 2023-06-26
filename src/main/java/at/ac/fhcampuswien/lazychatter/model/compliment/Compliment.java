package at.ac.fhcampuswien.lazychatter.model.compliment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Compliment {
    @JsonProperty
    private String compliment;

    public String getCompliment() {
        return compliment;
    }
}
