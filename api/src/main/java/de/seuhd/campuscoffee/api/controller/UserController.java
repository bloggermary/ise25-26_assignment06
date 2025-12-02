package de.seuhd.campuscoffee.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "Operations related to user management.")
@Controller
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return service.findAll().stream().map(UserMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable long id) {
        return service.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/filter")
    public UserDto findByLoginName(@RequestParam String loginName) {
        return service.findByLoginName(loginName)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto dto) {
        return UserMapper.toDto(service.create(UserMapper.fromDto(dto)));
    }

    @PutMapping("/{id}")
    public UserDto update(
            @PathVariable long id,
            @Valid @RequestBody UserDto dto
    ) {
        return UserMapper.toDto(service.update(id, UserMapper.fromDto(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
