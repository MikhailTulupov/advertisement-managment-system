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
 * The class {@link WebPage} presents canal model class for deserialization and serialization
 * {@link ru.tulupov.model.Page} for next use. i.e. we get canal model and mapping json object to POJO.
 */
@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
@Schema
public class WebPage implements Serializable {
    @JsonAlias(value = "pageName")
    @JsonProperty(value = "pageName")
    @Schema(name = "pageName", title = "Page name", type = "String", example = "MAIN_PAGE")
    private String name;
}