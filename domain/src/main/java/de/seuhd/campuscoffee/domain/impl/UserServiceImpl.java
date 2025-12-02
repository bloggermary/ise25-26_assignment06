package de.seuhd.campuscoffee.domain.impl;

import de.seuhd.campuscoffee.domain.ports.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // outbound port

        @Override
        public User createUser(User user) {
            return userRepository.save(user);
        }

        @Override
        public Optional<User> findById(Long id) {
            return userRepository.findById(id);
        }

        @Override
        public List<User> findAll() {
            return userRepository.findAll();
        }

        @Override
        public User updateUser(Long id, User updatedUser) {
            return userRepository.findById(id)
                    .map(existing -> {
                        User merged = existing.toBuilder()
                                .username(updatedUser.username())
                                .email(updatedUser.email())
                                .role(updatedUser.role())
                                .build();
                        return userRepository.save(merged);
                    })
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        @Override
        public void deleteUser(Long id) {
            userRepository.delete(id);
        }
    }
}
