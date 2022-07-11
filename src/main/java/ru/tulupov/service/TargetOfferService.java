package ru.tulupov.service;


import org.springframework.scheduling.annotation.Scheduled;

/**
 * The {@link TargetOfferService} interface provides a set of methods for manipulating
 * {@link ru.tulupov.model.web.WebTargetOffer} data.
 */
public interface TargetOfferService {
    /**
     * Scheduled method, run once a day. Method using {@link org.springframework.web.client.RestTemplate}
     * to send a post request to CDS system.
     */
    @Scheduled(cron = "0 0 * * * *")
    void sendTargeting();
}
