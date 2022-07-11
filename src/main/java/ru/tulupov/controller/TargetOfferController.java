package ru.tulupov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tulupov.model.web.WebTargetOffer;
import ru.tulupov.service.PageService;
import ru.tulupov.service.TargetOfferService;

/**
 * The {@link TargetOfferController} class present's controller.
 */
@RestController
public class TargetOfferController {
    @Autowired
    TargetOfferService targetingOfferService;
    @Autowired
    PageService pageService;

    /**
     * Method ship target offers data to request post to CDS system.
     * @param webTargetOffer target offers
     * @return response body {@link WebTargetOffer}
     */
    @Operation(summary = "Post targeting offers")
    @PostMapping("/")
    @ResponseBody
    @Parameters(value = @Parameter(name = "TargetOffer",
            description = "Presents targeting offer. Object is contains list of offers. " +
                    "Offers contains content guid witch user dont view and user guid.",
            content = @Content(schema = @Schema(type = "WebPublishedContent"))))
    public WebTargetOffer getOffer(@RequestBody WebTargetOffer webTargetOffer) {
        return webTargetOffer;
    }
}