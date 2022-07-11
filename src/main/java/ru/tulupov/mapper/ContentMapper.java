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

    /**
     * Method mapping web {@link WebContent} model to {@link Content} model
     *
     * @param webContent web model
     * @return data model
     */
    @Mappings({
            @Mapping(target = "id", source = "webContent.id"),
            @Mapping(target = "pages", source = "webContent.pages")
    })
    Content webContentToContent(WebContent webContent);

    /**
     * Method mapping {@link Content} model to web {@link WebContent} model
     *
     * @param content data model
     * @return web model
     */
    @Mappings({
            @Mapping(target = "id", source = "content.id"),
            @Mapping(target = "pages", source = "content.pages")
    })
    WebContent contentToWebContent(Content content);

    /**
     * Method mapping list of {@link Content} model to list of web {@link WebContent} model
     *
     * @param contents list of content
     * @return list of web models
     */
    List<WebContent> contentsToWebContents(List<Content> contents);
}