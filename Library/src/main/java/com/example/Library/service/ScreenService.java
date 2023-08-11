package com.example.Library.service;

import com.example.Library.dto.ScreenDto;
import com.example.Library.model.Screen;

import java.util.List;
import java.util.Optional;

public interface ScreenService {
    Screen newScreen(ScreenDto screenDto);
    List<Screen> getListScreen();
    Optional<Screen> getScreen(Long id);
    String updateScreen(Long id, ScreenDto screenDto);
}
