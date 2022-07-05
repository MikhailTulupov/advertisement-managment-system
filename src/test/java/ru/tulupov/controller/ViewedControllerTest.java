package ru.tulupov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.tulupov.model.web.WebPostViewed;
import ru.tulupov.model.web.WebViewed;
import ru.tulupov.service.web.WebViewedService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ViewedController.class)
public class ViewedControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    WebViewedService webViewedService;

    @Test
    public void postViewedTest() throws Exception {
        WebPostViewed webPostViewed = new WebPostViewed(List.of(
                WebViewed.builder()
                        .content("2090a014-b713-4ecc-8d10-4f9fb5c40c20")
                        .user("0af6cc01-ae5d-43d1-ac3f-1f1060232779").build(),
                WebViewed.builder()
                        .content("2090a014-b713-4ecc-8d10-4f9fb5c40c31")
                        .user("0af6cc01-ae5d-43d1-ac3f-1f1060225819").build()));

        when(webViewedService.saveAll(webPostViewed.getViewed())).thenReturn(webPostViewed.getViewed());

        mockMvc.perform(
                post("/post-list-viewed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(webPostViewed))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("contentGuid")));
    }
}
