package ru.tulupov.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The class {@link WebTargetOffer} presents canal model class for serialization.
 * i.e. we get all required data for CDS system and POST this data to CDS.
 */
@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
@Schema
public class WebTargetOffer implements Serializable {

    @Schema(name = "page",
            title = "Page name",
            type = "String",
            example = "MAIN_PAGE")
    private String page;

    @Schema(name = "startDate",
            title = "Start date",
            type = "String",
            example = "22022022")
    private String startDate;

    @Schema(name = "endDate",
            title = "End date",
            type = "String",
            example = "23022022")
    private String endDate;

    @Schema(name = "target",
            title = "WebTarget",
            type = "Array",
            example = """
                    [
                        {
                            "userGuid": "d65dc211-630f-4164-979c-b71c768ec7c1",
                            "offers": [
                                {
                                    "contentGuid": "81a474b8-c127-4a70-a6de-a9ea40601e58",
                                    "priority": "99"
                                },
                                {
                                    "contentGuid": "81a474b8-c147-1a70-a6de-a9ea40601e83",
                                    "priority": "1"
                                }
                            ]
                        },
                        {
                            "userGuid": "d65dc211-630f-4164-979c-b71c768ec7c2",
                            "offers": [
                                {
                                    "contentGuid": "81a474b8-c127-4a70-a6de-a9ea40601e59",
                                    "priority": "99"
                                },
                                {
                                    "contentGuid": "81a474b8-c147-1a70-a6de-a9ea40601e23",
                                    "priority": "1"
                                }
                            ]
                        }
                    ]
                    """)
    private List<WebTarget> target;
}