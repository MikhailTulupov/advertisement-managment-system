package ru.tulupov.model.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The {@link WebPublishedContent} class presents canal model, witch get from CMS service.
 */
@Data
@Schema
@RequiredArgsConstructor
@AllArgsConstructor
public class WebPublishedContent implements Serializable {
    @JsonAlias(value = "content")
    @JsonProperty(value = "content")
    @Schema(title = "WebContent",
            example = """
                    [
                        {
                            "contentGuid": "81a474b8-c127-4a70-a6de-a9ea40601e58",
                            "pages": [
                                {
                                    "pageName": "MAIN_PAGE"
                                },
                                {
                                    "pageName": "SHOP_PAGE"
                                }
                            ]
                        },
                        {
                            "contentGuid": "23a474b8-c123-4a73-a6de-a9eb40603d32",
                            "pages": [
                                {
                                    "pageName": "MAIN_PAGE"
                                },
                                {
                                    "pageName": "SHOP_PAGE"
                                }
                            ]
                        }
                    ]
                    """)
    private List<WebContent> contents;
}