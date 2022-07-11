package ru.tulupov.model.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Schema
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WebUser implements Serializable {
    @JsonAlias(value = "userGuid")
    @JsonProperty(value = "userGuid")
    @Schema(name = "userGuid",
            title = "User identifier",
            type = "String",
            example = "81a474b8-c135-4a73-a6de-a9ea51651e28")
    private String id;
}