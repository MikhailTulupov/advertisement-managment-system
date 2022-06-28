package ru.tulupov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.tulupov.model.Content;
import ru.tulupov.model.web.WebContent;

/**
 * The {@link ContentMapper} interfaces provides methods for mapping to data classes.
 */
@Mapper
public interface ContentMapper {
    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    Content webContentToContent(WebContent webContent);
    WebContent contentToWebContent(Content content);
}
