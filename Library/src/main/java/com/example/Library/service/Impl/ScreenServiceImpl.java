package com.example.Library.service.Impl;

import com.example.Library.dto.ScreenDto;
import com.example.Library.model.Screen;
import com.example.Library.repository.ScreenRepository;
import com.example.Library.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;
    @Override
    public Screen newScreen(ScreenDto screenDto) {
        Screen screen = new Screen();
        screen.setName(screenDto.getName());
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(screenDto.getImages()));
            screen.setImages(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        screen.setDescription(screenDto.getDescription());
        screenRepository.save(screen);
        return screen;
    }

    @Override
    public List<Screen> getListScreen() {
        return screenRepository.findAll();
    }

    @Override
    public Optional<Screen> getScreen(Long id) {
        return screenRepository.findById(id);
    }

    @Override
    public String updateScreen(Long id, ScreenDto screenDto) {
        Optional<Screen> screen = screenRepository.findById(id);
        if(screen.isEmpty()) {
            return "Not found screen!";
        }
        Screen screen1 = screen.get();
        screen1.setName(screenDto.getName());
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(screenDto.getImages()));
            screen1.setImages(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        screen1.setDescription(screenDto.getDescription());
        screenRepository.save(screen1);
        return "Update screen successful!";
    }
}
