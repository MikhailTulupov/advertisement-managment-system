package ru.tulupov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;
import ru.tulupov.repository.ViewedRepository;

import java.util.List;
import java.util.UUID;

/**
 * The {@link ViewedServiceImpl} class implements {@link ViewedService} methods.
 */
@Service
public class ViewedServiceImpl implements ViewedService {
    @Autowired
    ViewedRepository viewedRepository;
    @Autowired
    ContentService contentService;
    @Autowired
    UserService userService;

    @Override
    public Viewed save(Viewed viewed) {
        return viewedRepository.save(viewed);
    }

    @Override
    public List<Viewed> saveAll(List<Viewed> viewedList) {
        for (Viewed viewed : viewedList) {
            contentService.save(viewed.getContent());
            userService.save(viewed.getUser());
        }
        return viewedRepository.saveAll(viewedList);
    }

    @Override
    public Viewed getById(UUID id) {
        return viewedRepository.findById(id).orElseGet(Viewed::new);
    }

    @Override
    public List<Viewed> getAll() {
        return viewedRepository.findAll();
    }

    @Override
    public List<User> getAllUsersByContentId(UUID id) {
        return viewedRepository.findAllUsersByContentId(id);
    }

    @Override
    public List<Content> getAllContentByUserId(UUID id) {
        return viewedRepository.findAllContentByUserId(id);
    }
}