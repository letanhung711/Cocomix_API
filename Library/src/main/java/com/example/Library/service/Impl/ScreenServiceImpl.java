package com.example.Library.service.Impl;

import com.example.Library.dto.ScreenDto;
import com.example.Library.model.Role;
import com.example.Library.model.RoleScreenPermission;
import com.example.Library.model.Screen;
import com.example.Library.model.User;
import com.example.Library.repository.RSPRepository;
import com.example.Library.repository.ScreenRepository;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RSPRepository rspRepository;
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

    @Override
    public String deleteScreen(Long id) {
        Optional<Screen> screen = screenRepository.findById(id);
        if(screen.isEmpty()) {
            return "Not found screen!";
        }
        screenRepository.deleteById(screen.get().getId());
        return "Delete screen successful!";
    }

    @Override
    public String assignsScreensToUsers(Long idscreen, Long iduser) {
        Optional<User> user = userRepository.findById(iduser);
        Optional<Screen> screen = screenRepository.findById(idscreen);
        if(user.isEmpty()) {
            return "Not found user!";
        }
        if(screen.isEmpty()) {
            return "Not found screen!";
        }

        Role role = user.get().getRoles().iterator().next(); //truy cập lần lượt từng phần tử danh sách
        if(role == null){
            return "Not found roles!";
        }

        RoleScreenPermission rsp = new RoleScreenPermission();
        rsp.setScreen(screen.get());
        rsp.setRole(role);
        //rsp.setPermission();
        rspRepository.save(rsp);
        return "Assign screens to users successful!";
    }

    @Override
    public List<RoleScreenPermission> GetListOfUserScreens(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            return null;
        }
        return rspRepository.findAllById(user.get().getId());
    }
}
