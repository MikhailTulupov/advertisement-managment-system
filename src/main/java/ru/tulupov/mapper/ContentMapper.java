package ru.tulupov.mapper;

import org.mapstruct.Mapper;
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

    Content webContentToContent(WebContent webContent);

    WebContent contentToWebContent(Content content);

    List<Content> webContentsToContents(List<WebContent> webContents);

    List<WebContent> contentsToWebContents(List<Content> contents);
}
