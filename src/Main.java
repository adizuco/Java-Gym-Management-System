import java.util.Scanner;
import models.Member;
import models.Membership;
import models.Trainer;
import models.TrainingSession;
import models.User;
import services.AuthService;
import services.MemberService;
import services.ScheduleService;
import java.time.LocalDate;

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
            System.out.println("2. Add New Member");
            System.out.println("3. Remove Member");
            System.out.println("4. View Trainers");
            System.out.println("5. Book Training Session");
            System.out.println("6. View Booked Sessions");
            System.out.println("7. Track Attendance (Mock)");
            System.out.println("8. Logout & Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> displayMembers();
                case "2" -> addMember();
                case "3" -> removeMember();
                case "4" -> displayTrainers();
                case "5" -> bookTrainingSession();
                case "6" -> displaySessions();
                case "7" -> System.out.println("Attendance tracked for today!");
                case "8" -> {
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
            System.out.println(member);
        }
    }

    private static void addMember() {
        System.out.println("\n--- Add New Member ---");
        System.out.print("Enter ID (e.g., M005): ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Weight (kg): ");
        double weight = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter Height (cm): ");
        double height = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter Goal: ");
        String goal = scanner.nextLine().trim();

        System.out.println("Select Membership Plan:");
        System.out.println("1. Basic");
        System.out.println("2. Premium");
        System.out.println("3. VIP");
        int planChoice = Integer.parseInt(scanner.nextLine().trim());
        Membership.PlanType planType = switch (planChoice) {
            case 2 -> Membership.PlanType.PREMIUM;
            case 3 -> Membership.PlanType.VIP;
            default -> Membership.PlanType.BASIC;
        };

        Membership membership = new Membership(planType, LocalDate.now().toString());
        Member newMember = new Member(id, name, weight, height, goal, membership);
        memberService.addMember(newMember);
        System.out.println("Member added successfully!");
    }

    private static void removeMember() {
        System.out.print("\nEnter Member ID to remove: ");
        String id = scanner.nextLine().trim();
        if (memberService.deleteMember(id)) {
            System.out.println("Member removed successfully.");
        } else {
            System.out.println("Member not found.");
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

        Member member = memberService.findMemberById(memberId);
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
}
