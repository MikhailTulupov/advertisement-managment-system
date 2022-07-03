package ru.tulupov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.tulupov.model.web.WebContent;
import ru.tulupov.model.web.WebPage;
import ru.tulupov.model.web.WebPublishedContent;
import ru.tulupov.service.web.WebContentService;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ContentController.class)
public class ContentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private WebContentService webContentService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new WebContent()).build();
    }

    @Test
    void postPublishedContentTest() throws Exception {

        WebPublishedContent webPublishedContent = new WebPublishedContent(List.of(
                WebContent.builder().id("11a474b8-c127-4a70-a6de-a9ea40601e58")
                        .pages(Set.of(WebPage.builder().name("MAIN_PAGE").build(),
                                WebPage.builder().name("SHOP_PAGE").build())).build(),
                WebContent.builder().id("31a474b8-c127-4a70-a6de-a9ea40601e18")
                        .pages(Set.of(WebPage.builder().name("MAIN_PAGE").build(),
                                WebPage.builder().name("SHOP_PAGE").build())).build()
        ));

        when(webContentService.saveAll(webPublishedContent.getContents())).thenReturn(webPublishedContent.getContents());

        mockMvc.perform(
                        post("/post-list-content")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(webPublishedContent))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("contentGuid")));

    }
}