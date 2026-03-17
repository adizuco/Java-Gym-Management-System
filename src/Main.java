import models.User;
import models.Member;
import services.AuthService;
import services.MemberService;
import java.util.Scanner;

/**
 * Member: Abdulaziz (Leader)
 * Task: Implement the main menu, application entry point, and coordination
 * logic.
 */
public class Main {
    private static AuthService authService = new AuthService();
    private static MemberService memberService = new MemberService();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("=== Welcome to Java Gym Management System ===");

        while (currentUser == null) {
            System.out.println("\n--- Login ---");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            currentUser = authService.login(username, password);
            if (currentUser == null) {
                System.out.println("Invalid credentials! Hint: use admin/admin123");
            }
        }

        System.out.println("\nLogin Successful! Role: " + currentUser.getRole());
        showMenu();
    }

    private static void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View All Members");
            System.out.println("2. Track Attendance (Mock)");
            System.out.println("3. Logout & Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    displayMembers();
                    break;
                case "2":
                    System.out.println("Attendance tracked for today!");
                    break;
                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void displayMembers() {
        System.out.println("\n--- Member List ---");
        for (Member member : memberService.getAllMembers()) {
            System.out.println(member.getId() + " - " + member.getName());
        }
    }
}