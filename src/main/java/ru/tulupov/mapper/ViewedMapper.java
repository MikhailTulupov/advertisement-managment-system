package ru.tulupov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;
import ru.tulupov.model.web.WebViewed;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper
public interface ViewedMapper {

    @Named("webViewedToViewed")
    static Viewed webViewedToViewed(WebViewed webViewed) {

        return Viewed.builder()
                .user(User.builder().id(UUID.fromString(webViewed.getUser())).build())
                .content(Content.builder().id(UUID.fromString(webViewed.getContent())).build()).build();
    }

    @Named("viewedToWebViewed")
    static WebViewed viewedToWebViewed(Viewed viewed) {
        return WebViewed.builder()
                .user(viewed.getUser().getId().toString())
                .content(viewed.getContent().getId().toString()).build();
    }

    @Named("viewedListToWebViewedList")
    static List<WebViewed> viewedListToWebViewedList(List<Viewed> viewed) {
        List<WebViewed> webViewed = new ArrayList<>();
        for (Viewed view: viewed) {
            WebViewed v = ViewedMapper.viewedToWebViewed(view);
            webViewed.add(v);
        }
        return webViewed;
    }
}