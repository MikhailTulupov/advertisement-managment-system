package ru.tulupov.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * The {@link WebOffer} class presents canal model for providing priority for displaying content.
 */
@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
@Schema
public class WebOffer implements Serializable {
    @Schema(name = "contentGuid",
            title = "content identifier",
            type = "String",
            example = "c797e7a2-61df-4a01-b28d-6ac272481307")
    private String contentGuid;

    @Schema(name = "priority",
            title = "priority of content",
            type = "String",
            example = "99")
    private String priority;
}