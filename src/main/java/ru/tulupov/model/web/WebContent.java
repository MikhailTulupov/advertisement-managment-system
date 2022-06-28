package ru.tulupov.model.web;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * The class {@link WebContent} presents canal model class.
 */
@Schema
@Data
public class WebContent implements Serializable {
    @Schema(name = "contentGuid",
            title = "Content guid",
            type = "String",
            example = "81a474b8-c127-4a70-a6de-a9ea40601e58")
    private String id;

    @ArraySchema( arraySchema =  @Schema(
            description = "My description",
            example ="[{\"pageName\": \"MAIN_PAGE\"}," +
                    "{\"pageName\": \"SHOP_PAGE\"}]"))
    private Set<WebPage> pages;
}
