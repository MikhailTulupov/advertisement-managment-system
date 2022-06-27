package ru.tulupov.service;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ViewedServiceImpl implements ViewedService {
    final ViewedRepository viewedRepository;

    @Override
    public void save(Viewed viewed) {
        viewedRepository.save(viewed);
    }

    @Override
    public void saveAll(List<Viewed> viewedList) {
        viewedRepository.saveAll(viewedList);
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
    public List<User> findAllUsersByContentId(UUID id) {
        return viewedRepository.findAllUsersByContentId(id);
    }

    @Override
    public List<Content> findAllContentByUserId(UUID id) {
        return viewedRepository.findAllContentByUserId(id);
    }
}
