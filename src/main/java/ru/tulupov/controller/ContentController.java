package ru.tulupov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tulupov.model.web.WebContent;
import ru.tulupov.service.web.WebContentService;

/**
 * The {@link ContentController} class presents' controller.
 */
@RestController
@RequiredArgsConstructor
public class ContentController {
    final WebContentService webContentService;

    @Operation(summary = "create Content")
    @PostMapping("/create-user")
    @Parameters(value = {@Parameter(name = "webContent",
            description = "Web content",
            content = @Content(schema = @Schema(type = "WebContent")))})
    public void createContent(@RequestBody WebContent webContent) {
        webContentService.save(webContent);
    }


    @GetMapping("/get-content/{id}")
    public WebContent getContent(@PathVariable(value = "id") String contentGuid) {
        return webContentService.getById(contentGuid);
    }
}
