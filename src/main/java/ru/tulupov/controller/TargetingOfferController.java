package ru.tulupov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tulupov.model.web.WebTargetingOffer;
import ru.tulupov.service.PageService;
import ru.tulupov.service.TargetingOfferService;

@RestController
public class TargetingOfferController {
    @Autowired
    TargetingOfferService targetingOfferService;
    @Autowired
    PageService pageService;

    @PostMapping("/")
    @ResponseBody
    public WebTargetingOffer getOffer(@RequestBody WebTargetingOffer webTargetingOffer) {
        return webTargetingOffer;
    }
}