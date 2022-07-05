package ru.tulupov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.tulupov.model.Content;
import ru.tulupov.model.web.WebContent;

import java.util.List;

/**
 * The {@link ContentMapper} interfaces provides methods for mapping to data classes.
 */
@Mapper
public interface ContentMapper {
    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "webContent.id"),
            @Mapping(target = "pages", source = "webContent.pages")
    })
    Content webContentToContent(WebContent webContent);

    @Mappings({
            @Mapping(target = "id", source = "content.id"),
            @Mapping(target = "pages", source = "content.pages")
    })
    WebContent contentToWebContent(Content content);

    List<WebContent> contentsToWebContents(List<Content> contents);
}