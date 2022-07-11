package ru.tulupov.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The {@link WebTarget} class presents canal model provides offers for the user.
 */
@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
@Schema
public class WebTarget implements Serializable {
    @Schema(name = "userGuid",
            title = "user identifier",
            type = "String",
            example = "53efc4e4-7049-49b6-a81f-0fa974fe3a60")
    private String userGuid;

    @Schema(name = "offers",
            title = "WebOffer",
            type = "Array",
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