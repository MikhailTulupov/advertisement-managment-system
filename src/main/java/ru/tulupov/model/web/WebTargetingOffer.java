package ru.tulupov.model.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Data
@Schema
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class WebTargetingOffer implements Serializable {

    @JsonAlias(value = "page")
    @JsonProperty(value = "page")
    @Schema(name = "page",
            title = "Name page",
            type = "String",
            example = "MAIN_PAGE")
    private String page;

    @JsonAlias(value = "startDate")
    @JsonProperty(value = "startDate")
    @Schema(name = "startDate",
            title = "Start date",
            type = "String",
            example = "22022022")
    private String startDate;

    @JsonAlias(value = "endDate")
    @JsonProperty(value = "endDate")
    @Schema(name = "endDate",
            title = "End date",
            type = "String",
            example = "23022022")
    private String endDate;

    @JsonAlias(value = "target")
    @JsonProperty(value = "target")
    @Schema(name = "target",
            title = "target",
            type = "List",
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
