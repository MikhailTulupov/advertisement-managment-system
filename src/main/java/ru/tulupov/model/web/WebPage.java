package ru.tulupov.model.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * The class {@link WebPage} presents canal model class.
 */
@Data
@Schema
public class WebPage implements Serializable {
    @JsonAlias(value = "pageName")
    @Schema(name = "pageName",
            title = "Page name",
            type = "String",
            example = "MAIN_PAGE")
    private String name;
}
