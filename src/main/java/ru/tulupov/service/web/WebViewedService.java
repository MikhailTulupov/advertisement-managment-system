package ru.tulupov.service.web;

import ru.tulupov.model.web.WebViewed;

import java.util.List;

/**
 * The {@link WebViewedService} interface provides a set of methods for manipulating {@link WebViewed} data.
 */
public interface WebViewedService {
    List<WebViewed> saveAll(List<WebViewed> webViewed);
}
