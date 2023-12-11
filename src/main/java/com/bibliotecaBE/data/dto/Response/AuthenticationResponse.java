package com.bibliotecaBE.data.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationResponse {

    //**** Sezione Attributi ****

    @JsonProperty
    private String token;
}
