package ru.tulupov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tulupov.model.web.WebPostViewed;
import ru.tulupov.service.web.WebViewedService;

/**
 * The {@link ViewedController} present's controller.
 */
@RestController
public class ViewedController {
    @Autowired
    WebViewedService webViewedService;

    /**
     * Method get request post in the form JSON {@link WebPostViewed} mapping and save {@link ru.tulupov.model.Viewed}
     * content.
     *
     * @param webPostViewed request body
     * @return response body {@link WebPostViewed}
     */
    @Operation(summary = "Post viewed content")
    @PostMapping("/post-list-viewed")
    @Parameters(value = @Parameter(name = "viewedContent",
            description = "Presents viewed content object is contains list of viewed content. " +
                    "Viewed contains content guid and user guid.",
            content = @Content(schema = @Schema(type = "WebPostViewed"))))
    public WebPostViewed postViewed(@RequestBody WebPostViewed webPostViewed) {
        webPostViewed.setViewed(webViewedService.saveAll(webPostViewed.getViewed()));
        return webPostViewed;
    }
}
