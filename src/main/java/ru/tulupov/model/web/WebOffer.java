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
 * The {@link WebOffer} class presents canal model for
 * providing priority for displaying content.
 */
@Data
@Schema
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class WebOffer implements Serializable {
    @JsonAlias(value = "contentGuid")
    @JsonProperty(value = "contentGuid")
    @Schema(name = "contentGuid",
            title = "content guid",
            type = "String",
            example = "c797e7a2-61df-4a01-b28d-6ac272481307")
    private String contentGuid;

    @JsonAlias(value = "priority")
    @JsonProperty(value = "priority")
    @Schema(name = "priority",
            title = "priority",
            type = "String",
            example = "99")
    private String priority;
}
