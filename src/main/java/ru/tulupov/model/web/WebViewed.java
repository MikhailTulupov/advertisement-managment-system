package ru.tulupov.model.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * The class {@link WebViewed} presents canal model class for deserialization and serialization
 * {@link ru.tulupov.model.Viewed} for next use. i.e. we get canal model and mapping json object to POJO.
 */
@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
@Schema
public class WebViewed implements Serializable {
    @JsonAlias(value = "userGuid")
    @JsonProperty(value = "userGuid")
    @Schema(name = "userGuid",
            title = "User identifier",
            type = "String",
            example = "81a474b8-c135-4a73-a6de-a9ea51651e28")
    private String user;

    @JsonAlias(value = "contentGuid")
    @JsonProperty(value = "contentGuid")
    @Schema(name = "contentGuid",
            title = "Content identifier",
            type = "String",
            example = "32a524b2-c127-2a45-a6de-a9ea40601e23")
    private String content;
}