package com.wheretogo.backend.controller;

import com.wheretogo.backend.enums.AttractionTypes;
import com.wheretogo.backend.models.User;
import com.wheretogo.backend.responses.UserLocationResponse;
import com.wheretogo.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
        this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<User> findUser(@PathVariable long chatId) {
        return ResponseEntity.ok(this.userService.findUser(chatId));
    }

    @GetMapping("locations/{chatId}")
    public ResponseEntity<UserLocationResponse> getLocations(@PathVariable long chatId, @RequestParam(name = "type") AttractionTypes type, @RequestParam(name = "num") int numRecommendations) {

        return ResponseEntity.ok(new UserLocationResponse(this.userService.getLocations(chatId, type, numRecommendations)));
    }

    @PutMapping("/{chatId}")
    public ResponseEntity updateLocations(@PathVariable long chatId) {
        this.userService.updateLocations(chatId);
        return ResponseEntity.ok().build();
    }
}
