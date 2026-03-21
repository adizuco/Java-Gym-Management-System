import java.util.Scanner;
import models.Member;
import models.Trainer;
import models.TrainingSession;
import models.User;
import services.AuthService;
import services.MemberService;
import services.ScheduleService;

/**
 * Member: Abdulaziz (Leader)
 * Task: Implement the main menu, application entry point, and coordination
 * logic.
 */
public class Main {
    private static final AuthService authService = new AuthService();
    private static final MemberService memberService = new MemberService();
    private static final ScheduleService scheduleService = new ScheduleService();
    private static final Scanner scanner = new Scanner(System.in);
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
            System.out.println("2. View Trainers");
            System.out.println("3. Book Training Session");
            System.out.println("4. View Booked Sessions");
            System.out.println("5. Track Attendance (Mock)");
            System.out.println("6. Logout & Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> displayMembers();
                case "2" -> displayTrainers();
                case "3" -> bookTrainingSession();
                case "4" -> displaySessions();
                case "5" -> System.out.println("Attendance tracked for today!");
                case "6" -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void displayMembers() {
        System.out.println("\n--- Member List ---");
        for (Member member : memberService.getAllMembers()) {
            System.out.println(member.getId() + " - " + member.getName());
        }
    }

    private static void displayTrainers() {
        System.out.println("\n--- Trainer List ---");
        for (Trainer trainer : scheduleService.getAllTrainers()) {
            System.out.println(trainer);
        }
    }

    private static void displaySessions() {
        System.out.println("\n--- Booked Sessions ---");
        if (scheduleService.getAllSessions().isEmpty()) {
            System.out.println("No sessions booked yet.");
            return;
        }

        for (TrainingSession session : scheduleService.getAllSessions()) {
            System.out.println(session);
        }
    }

    private static void bookTrainingSession() {
        System.out.println("\n--- Book Training Session ---");
        displayMembers();
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine().trim();

        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        displayTrainers();
        System.out.print("Enter trainer ID: ");
        String trainerId = scanner.nextLine().trim();

        System.out.print("Enter date and time (yyyy-MM-dd HH:mm): ");
        String slot = scanner.nextLine().trim();

        System.out.print("Enter duration in minutes: ");
        int durationMinutes;
        try {
            durationMinutes = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Duration must be a number.");
            return;
        }

        System.out.print("Is this a group session? (yes/no): ");
        boolean groupSession = scanner.nextLine().trim().equalsIgnoreCase("yes");

        String result = scheduleService.bookSession(member, trainerId, slot, durationMinutes, groupSession);
        System.out.println(result);
    }

    private static Member findMemberById(String memberId) {
        for (Member member : memberService.getAllMembers()) {
            if (member.getId().equalsIgnoreCase(memberId)) {
                return member;
            }
        }
        return null;
    }
}
