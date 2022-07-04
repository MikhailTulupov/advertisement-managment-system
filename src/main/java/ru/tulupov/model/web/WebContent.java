package ru.tulupov.model.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The class {@link WebContent} presents canal model class.
 */

@Data
@Schema
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WebContent implements Serializable {
    @JsonAlias(value = "contentGuid")
    @JsonProperty(value = "contentGuid")
    @Schema(name = "contentGuid",
            title = "Content guid",
            type = "String",
            example = "81a474b8-c127-4a70-a6de-a9ea40601e58")
    private String id;

    @JsonAlias(value = "pages")
    @JsonProperty(value = "pages")
    @ArraySchema(arraySchema = @Schema(
            description = "Set of pages",
            example = "[{\"pageName\": \"MAIN_PAGE\"}," +
                    "{\"pageName\": \"SHOP_PAGE\"}]"))
    @Builder.Default
    private Set<WebPage> pages = new HashSet<>();

    public WebContent(String id) {
        this.id = id;
    }
}
