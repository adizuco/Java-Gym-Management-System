package services;

import models.User;

/**
 * Member: Abdulaziz (Leader)
 * Task: Implement authentication logic, login/logout, and session management.
 */
public class AuthService {
    private final User mockAdmin = new User("admin", "admin123", "ADMIN");

    public User login(String username, String password) {
        if (mockAdmin.getUsername().equals(username) && mockAdmin.checkPassword(password)) {
            return mockAdmin;
        }
        return null;
    }
}
