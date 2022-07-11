package ru.tulupov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tulupov.model.web.WebContent;
import ru.tulupov.model.web.WebPublishedContent;
import ru.tulupov.service.web.WebContentService;

/**
 * The {@link ContentController} class present's controller.
 */
@RestController
public class ContentController {
    @Autowired
    WebContentService webContentService;

    /**
     * Method get request post list of {@link WebContent} and mapping and save to list {@link ru.tulupov.model.Content}
     *
     * @param webPublishedContent list of web contents.
     * @return request body of list of contents.
     */
    @Operation(summary = "Post published content")
    @PostMapping("/post-list-content")
    @Parameters(value = @Parameter(name = "publishedContent",
            description = "Presents published content object is contains list of contents. " +
                    "Content contains content guid and set of pages witch refer to content guid.",
            content = @Content(schema = @Schema(type = "WebPublishedContent"))))
    public WebPublishedContent postListContent(@RequestBody WebPublishedContent webPublishedContent) {
        webPublishedContent.setContents(webContentService.saveAll(webPublishedContent.getContents()));
        return webPublishedContent;
    }
}