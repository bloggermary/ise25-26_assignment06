package de.seuhd.campuscoffee.domain.ports;

import de.seuhd.campuscoffee.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);
}