package ru.tulupov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;
import ru.tulupov.model.web.WebViewed;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The {@link ViewedMapper} interfaces provides methods for mapping to data classes.
 */
@Mapper
public interface ViewedMapper {

    /**
     * Method mapping {@link WebViewed} web model to {@link Viewed} data model.
     *
     * @param webViewed web model viewed
     * @return data model viewed
     */
    @Named("webViewedToViewed")
    static Viewed webViewedToViewed(WebViewed webViewed) {

        return Viewed.builder()
                .user(User.builder().id(UUID.fromString(webViewed.getUser())).build())
                .content(Content.builder().id(UUID.fromString(webViewed.getContent())).build()).build();
    }

    /**
     * Method mapping {@link Viewed} data model to {@link WebViewed} web model.
     *
     * @param viewed data model viewed
     * @return web viewed model
     */
    @Named("viewedToWebViewed")
    static WebViewed viewedToWebViewed(Viewed viewed) {
        return WebViewed.builder()
                .user(viewed.getUser().getId().toString())
                .content(viewed.getContent().getId().toString()).build();
    }

    /**
     * Method mapping list of {@link Viewed} data models to list of {@link WebViewed} web models.
     *
     * @param viewed list of data models
     * @return list of web models
     */
    @Named("viewedListToWebViewedList")
    static List<WebViewed> viewedListToWebViewedList(List<Viewed> viewed) {
        List<WebViewed> webViewed = new ArrayList<>();
        for (Viewed view : viewed) {
            WebViewed v = ViewedMapper.viewedToWebViewed(view);
            webViewed.add(v);
        }
        return webViewed;
    }
}