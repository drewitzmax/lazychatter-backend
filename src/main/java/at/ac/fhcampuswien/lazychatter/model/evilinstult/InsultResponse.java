package at.ac.fhcampuswien.lazychatter.model.evilinstult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsultResponse {
    @JsonProperty
    private String number;
    @JsonProperty
    private String language;
    @JsonProperty
    private String insult;
    @JsonProperty
    private String created;
    @JsonProperty
    private String shown;
    @JsonProperty
    private String createdby;
    @JsonProperty
    private String active;
    @JsonProperty
    private String comment;

    public String getNumber() {
        return number;
    }

    public String getLanguage() {
        return language;
    }

    public String getInsult() {
        return insult;
    }

    public String getCreated() {
        return created;
    }

    public String getShown() {
        return shown;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getActive() {
        return active;
    }

    public String getComment() {
        return comment;
    }
}
