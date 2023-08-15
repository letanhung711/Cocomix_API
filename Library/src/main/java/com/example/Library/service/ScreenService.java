package com.example.Library.service;

import com.example.Library.dto.ScreenDto;
import com.example.Library.model.RoleScreenPermission;
import com.example.Library.model.Screen;

import java.util.List;
import java.util.Optional;

public interface ScreenService {
    Screen newScreen(ScreenDto screenDto);
    List<Screen> getListScreen();
    Optional<Screen> getScreen(Long id);
    String updateScreen(Long id, ScreenDto screenDto);
    String deleteScreen(Long id);
    String assignsScreensToUsers(Long idscreen, Long iduser);
    List<RoleScreenPermission> GetListOfUserScreens(Long userId);
}
