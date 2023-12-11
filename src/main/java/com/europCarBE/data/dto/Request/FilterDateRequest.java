package com.europCarBE.data.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
public class FilterDateRequest {
    @JsonProperty
    Date startDate;

    @JsonProperty
    Date endDate;

    @JsonProperty
    String type;

    @JsonProperty
    Integer id;
}
