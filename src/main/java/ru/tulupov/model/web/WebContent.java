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
 * The class {@link WebContent} presents canal model class for deserialization and serialization
 * {@link ru.tulupov.model.Content} for next use. i.e. we get canal model and mapping json object to POJO.
 */
@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
@Schema
public class WebContent implements Serializable {
    @JsonAlias(value = "contentGuid")
    @JsonProperty(value = "contentGuid")
    @Schema(name = "contentGuid",
            title = "Content identifier",
            type = "String",
            example = "81a474b8-c127-4a70-a6de-a9ea40601e58")
    private String id;

    @ArraySchema(arraySchema = @Schema(
            name = "pages",
            title = "WebPage",
            type = "Array",
            example = """
                    [
                        {
                            "pageName": "MAIN_PAGE",
                            "pageName": "SHOP_PAGE"
                        },
                    ]
                    """))
    @Builder.Default
    @JsonAlias(value = "pages")
    @JsonProperty(value = "pages")
    private Set<WebPage> pages = new HashSet<>();
}