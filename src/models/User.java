package models;

/**
 * Member: Abdulaziz (Leader)
 * Task: Implement the base User class with common attributes (id, name, email,
 * role).
 */
public class User {
    private final String username;
    private final String password;
    private final String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
