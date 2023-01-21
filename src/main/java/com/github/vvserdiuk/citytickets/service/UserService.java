package com.github.vvserdiuk.citytickets.service;

import com.github.vvserdiuk.citytickets.data.UserRepository;
import com.github.vvserdiuk.citytickets.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
