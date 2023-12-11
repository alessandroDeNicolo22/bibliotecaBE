package com.europCarBE.data.dto.Response;

import com.europCarBE.data.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteResponse {

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
