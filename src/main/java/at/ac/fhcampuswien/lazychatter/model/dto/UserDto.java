package at.ac.fhcampuswien.lazychatter.model.dto;

import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDto {
    @JsonProperty
    private String id;
    @JsonProperty
    private String username;
    @JsonProperty
    private String type;

    public UserDto(){}
    public UserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.type = user.getType().name();
    }
}
