package com.company.bookapi.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.company.bookapi.security.UserRole.*;

@Repository("custom")
public class CustomUserDaoService implements UserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return getUsers()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<User> getUsers() {
        List<User> users = Lists.newArrayList(
                new User(
                        "raj",
                        passwordEncoder.encode("password"),
                        MEMBER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                        "rishi",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                        "ranbir",
                        passwordEncoder.encode("password"),
                        ADMIN_SUB_CON.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
        return users;
    }
}
