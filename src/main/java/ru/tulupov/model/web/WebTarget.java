package ru.tulupov.model.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The {@link WebTarget} class presents canal model
 * provides offers for the user.
 */
@Data
@Schema
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class WebTarget implements Serializable {
    @JsonAlias(value = "userGuid")
    @JsonProperty(value = "userGuid")
    @Schema(name = "userGuid",
    title = "user guid",
    type = "String",
    example = "53efc4e4-7049-49b6-a81f-0fa974fe3a60")
    private String userGuid;

    @JsonAlias(value = "offers")
    @JsonProperty(value = "offers")
    @Schema(name = "offers",
    title = "offers",
    type = "String",
    example = """
            [
                {
                    "contentGuid": "81a474b8-c127-4a70-a6de-a9ea40601e58",
                    "priority": "99"
                },
                {
                    "contentGuid": "81a474b8-c147-1a70-a6de-a9ea40601e83",
                    "priority": "1"
                }
            ]
            """)
    private List<WebOffer> offers;
}
