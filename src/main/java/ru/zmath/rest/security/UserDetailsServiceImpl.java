package ru.zmath.rest.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.zmath.rest.model.User;
import ru.zmath.rest.service.UserService;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        List<User> users = userService.findByName(name);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException(name);
        }
        User user = users.get(0);
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }
}