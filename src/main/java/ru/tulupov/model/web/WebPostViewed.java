package ru.tulupov.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The {@link WebPostViewed} class presents canal model, witch get from CMS service.
 */
@AllArgsConstructor
@Data
@RequiredArgsConstructor
@Schema
public class WebPostViewed implements Serializable {
    @Schema(name = "viewed",
            title = "WebViewed",
            type = "Array",
            example = """
                    [
                        {
                            "userGuid": "23a474b8-c123-4a73-a6de-a9eb40603d32",
                            "contentGuid": "81a474b8-c127-4a70-a6de-a9ea40601e58"
                        },
                        {
                            "userGuid": "23a474b8-c153-4a13-a6de-a9eb40603d14",
                            "contentGuid": "81a474b8-c147-1a70-a6de-a9ea40601e83"
                        }
                    ]
                    """)
    private List<WebViewed> viewed;
}