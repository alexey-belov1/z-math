package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.rest.model.User;
import ru.zmath.rest.repository.UserRepository;
import ru.zmath.rest.security.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return this.userRepository.findById(id);
    }

    public Optional<User> findByName(String name) {
        return this.userRepository.findDistinctByLogin(name);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findDistinctByEmail(email);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public User save(User user) {
        return this.userRepository.findByLogin(user.getLogin()).isEmpty()
                ? this.userRepository.save(user) : null;
    }

    public boolean update(User user) {
        Optional<User> optional = this.userRepository.findById(user.getId());
        if (optional.isPresent()) {
            this.userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean delete(User user) {
        return this.userRepository.deleteUserById(user.getId()) != 0;
    }

    @Transactional(readOnly = true)
    public Optional<User> getCurrentUser() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findDistinctByLogin);
    }
}
