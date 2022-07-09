package ru.tulupov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;
import ru.tulupov.model.web.WebOffer;
import ru.tulupov.model.web.WebTarget;
import ru.tulupov.model.web.WebTargetingOffer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TargetingOfferServiceImpl implements TargetingOfferService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserService userService;
    @Autowired
    ContentService contentService;
    @Autowired
    PageService pageService;
    @Autowired
    ViewedService viewedService;
    @Autowired
    ObjectMapper mapper;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "*/10 * * * * *")
    public void sendTargeting() {
        WebTargetingOffer.WebTargetingOfferBuilder webTargetingOffer = WebTargetingOffer.builder();
        webTargetingOffer.page("MAIN_PAGE");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String startDate = LocalDate.now().format(dateTimeFormatter);
        String endDate = LocalDate.now().plusDays(1L).format(dateTimeFormatter);

        webTargetingOffer.startDate(startDate).endDate(endDate);

        List<WebTarget> webTargets = new ArrayList<>();

        Set<Content> contents = Set.copyOf(pageService.findAllContentByPageName("MAIN_PAGE"));
        List<User> users = userService.findAll();

        for (User user : users) {
            Set<Content> userContents = user.getViewedSet().stream().map(Viewed::getContent).collect(Collectors.toSet());
            Sets.SetView<Content> difference = Sets.difference(contents, userContents);
            WebTarget.WebTargetBuilder webTarget = WebTarget.builder();
            List<WebOffer> webOffers = new ArrayList<>();
            for (Content content : difference) {
                Random random = new Random();
                webOffers.add(WebOffer.builder().contentGuid(content.getId().toString())
                        .priority(String.valueOf(random.nextInt(99))).build());
            }
            webTarget.userGuid(user.getId().toString()).offers(webOffers);
            webTargets.add(webTarget.build());
        }
        webTargetingOffer.target(webTargets);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WebTargetingOffer> entity = new HttpEntity<>(webTargetingOffer.build(), headers);

        restTemplate.exchange(
                "http://localhost:8080/", HttpMethod.POST,
                entity, WebTargetingOffer.class);
    }
}