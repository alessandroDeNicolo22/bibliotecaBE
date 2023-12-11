package com.europCarBE.data.dto.Request;

import com.europCarBE.data.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtenteRequest {

    //****Sezione Attributi****

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String nome;

    @JsonProperty
    private String cognome;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @JsonProperty
    private Role role;
}
