package ru.zmath.rest.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.rest.errors.EmailAlreadyUsedException;
import ru.zmath.rest.errors.LoginAlreadyUsedException;
import ru.zmath.rest.model.Role;
import ru.zmath.rest.model.User;
import ru.zmath.rest.repository.UserRepository;
import ru.zmath.rest.security.SecurityUtils;
import ru.zmath.rest.service.dto.UserDTO;
import ru.zmath.rest.service.mapper.UserMapper;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    private final UserMapper userMapper;

    public UserService(final UserRepository userRepository, BCryptPasswordEncoder encoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
            .map(userMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> findById(int id) {
        return this.userRepository.findById(id)
            .map(this.userMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByName(String name) {
        return this.userRepository.findDistinctByLogin(name);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public UserDTO save(UserDTO userDTO) {

        User user = this.userMapper.toEntity(userDTO);

        if (this.userRepository.findDistinctByLogin(user.getLogin()).isPresent()) {
            throw new LoginAlreadyUsedException();
        }

        if (this.userRepository.findDistinctByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        Role role = new Role();
        role.setId(1);
        user.setRole(role);
        user.setCreated(GregorianCalendar.getInstance());
        user.setPassword(encoder.encode(user.getPassword()));

        return this.userMapper.toDto(this.userRepository.save(user));
    }

    public boolean update(UserDTO userDTO) {
        User user = this.userMapper.toEntity(userDTO);
        Optional<User> optional = this.userRepository.findById(user.getId());
        if (optional.isPresent()) {
            this.userRepository.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean delete(int id) {
        return this.userRepository.deleteUserById(id) != 0;
    }

    @Transactional(readOnly = true)
    public Optional<User> getCurrentUser() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findDistinctByLogin);
    }
}
