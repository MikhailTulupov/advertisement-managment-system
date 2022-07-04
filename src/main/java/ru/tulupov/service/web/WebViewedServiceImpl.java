package ru.tulupov.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.mapper.ViewedMapper;
import ru.tulupov.model.Viewed;
import ru.tulupov.model.web.WebViewed;
import ru.tulupov.service.ViewedService;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebViewedServiceImpl implements WebViewedService{
    @Autowired
    private ViewedService viewedService;

    @Override
    public List<WebViewed> saveAll(List<WebViewed> webViewed) {
        List<Viewed> viewed = new ArrayList<>();
        for (WebViewed el : webViewed) {
            Viewed view = ViewedMapper.INSTANCE.webViewedToViewed(el);
            viewed.add(view);
        }
        return ViewedMapper.INSTANCE.viewedListToWebViewedList(viewedService.saveAll(viewed));
    }
}
