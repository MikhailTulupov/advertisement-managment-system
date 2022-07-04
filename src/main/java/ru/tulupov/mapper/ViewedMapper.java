package ru.tulupov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.tulupov.model.Viewed;
import ru.tulupov.model.web.WebViewed;

import java.util.List;

@Mapper
public interface ViewedMapper {
    ViewedMapper INSTANCE = Mappers.getMapper(ViewedMapper.class);

    Viewed webViewedToViewed(WebViewed webViewed);

    WebViewed viewedToWebViewed(Viewed viewed);

    List<WebViewed> viewedListToWebViewedList(List<Viewed> viewed);
}