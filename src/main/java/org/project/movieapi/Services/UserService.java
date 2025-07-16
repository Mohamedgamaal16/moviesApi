package org.project.movieapi.Services;

import org.project.movieapi.Entites.User;

public interface UserService {
    User findByEmail(String email);
    User findByUsername(String username);
    boolean isEmailExists(String email);
    boolean isUsernameExists(String username);
    User saveUser(User user);
}
