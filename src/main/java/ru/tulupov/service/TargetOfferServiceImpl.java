package ru.tulupov.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;
import ru.tulupov.model.web.WebOffer;
import ru.tulupov.model.web.WebTarget;
import ru.tulupov.model.web.WebTargetOffer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The {@link TargetOfferServiceImpl} class implements {@link TargetOfferService} interface methods.
 * This class contains scheduled method sendTargeting()
 */
@Service
public class TargetOfferServiceImpl implements TargetOfferService {
    private static final String PAGE_NAME = "MAIN_PAGE";
    private static final String DATE_TIME_PATTERN = "ddMMyyyy";
    private static final int MAX_PRIORITY = 99;

    private final Random random = new Random();

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserService userService;
    @Autowired
    PageService pageService;

    @Override
    public void sendTargeting() {
        WebTargetOffer.WebTargetOfferBuilder webTargetingOffer = WebTargetOffer.builder();
        webTargetingOffer.page(PAGE_NAME);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        LocalDate localDate = LocalDate.now();

        String startDate = localDate.format(dateTimeFormatter);
        String endDate = localDate.plusDays(1L).format(dateTimeFormatter);

        webTargetingOffer.startDate(startDate).endDate(endDate);

        List<WebTarget> webTargets = new ArrayList<>();

        Set<Content> contents = Set.copyOf(pageService.getAllContentByPageName(PAGE_NAME));

        List<User> users = userService.getAll();

        for (User user : users) {
            Set<Content> userContents = user.getViewedSet()
                    .stream().map(Viewed::getContent).collect(Collectors.toSet());

            Sets.SetView<Content> difference = Sets.difference(contents, userContents);

            WebTarget.WebTargetBuilder webTarget = WebTarget.builder();

            List<WebOffer> webOffers = new ArrayList<>();

            for (Content content : difference) {
                webOffers.add(WebOffer.builder().contentGuid(content.getId().toString())
                        .priority(String.valueOf(random.nextInt(MAX_PRIORITY))).build());
            }

            webTarget.userGuid(user.getId().toString()).offers(webOffers);
            webTargets.add(webTarget.build());
        }

        webTargetingOffer.target(webTargets);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WebTargetOffer> entity = new HttpEntity<>(webTargetingOffer.build(), headers);

        restTemplate.exchange(
                "http://localhost:8080/", HttpMethod.POST,
                entity, WebTargetOffer.class);
    }
}