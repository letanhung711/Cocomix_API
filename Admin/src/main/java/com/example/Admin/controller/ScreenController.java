package com.example.Admin.controller;

import com.example.Library.dto.ScreenDto;
import com.example.Library.model.Screen;
import com.example.Library.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/screen")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @PostMapping("")
    public ResponseEntity<String> newScreen(@RequestBody ScreenDto screenDto) {
        Screen screen = screenService.newScreen(screenDto);
        if(screen == null) {
            return ResponseEntity.badRequest().body("Create a new screen fail!");
        }
        return ResponseEntity.ok("Create a new screen successful!");
    }

    @GetMapping("")
    public ResponseEntity<List<Screen>> getListScreen() {
        return ResponseEntity.ok(screenService.getListScreen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScreen(@PathVariable("id") Long id) {
        Optional<Screen> screen = screenService.getScreen(id);
        if(screen.isEmpty()) {
            return ResponseEntity.badRequest().body("Not found screen!");
        }
        return ResponseEntity.ok(screen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateScreen(@PathVariable("id") Long id,
                                               @RequestBody ScreenDto screenDto) {
        return ResponseEntity.ok(screenService.updateScreen(id, screenDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScreen(@PathVariable("id") Long id) {
        return ResponseEntity.ok(screenService.deleteScreen(id));
    }
}
