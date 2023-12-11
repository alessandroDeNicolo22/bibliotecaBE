package com.europCarBE.data.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationRequest {

    //****Sezione Attributi ****

    @JsonProperty
    private String email;

    @JsonProperty
    String password;
}
