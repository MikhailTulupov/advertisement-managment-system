package ru.tulupov.service.web;

import ru.tulupov.model.web.WebContent;
import ru.tulupov.model.web.WebViewed;

import java.util.List;

/**
 * The {@link WebViewedService} interface provides a set of methods for manipulating {@link WebViewed} data.
 */
public interface WebViewedService {
    /**
     * Method save {@link List}<{@link WebContent}> in database.
     *
     * @param webViewed list of web viewed
     * @return list save entities.
     */
    List<WebViewed> saveAll(List<WebViewed> webViewed);
}
