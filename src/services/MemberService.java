package services;

import java.util.ArrayList;
import java.util.List;
import models.Member;
import models.Membership;
import util.FileManager;

/**
 * Member: Boburjon
 * Task: Implement CRUD operations for members, including registration and
 * profile updates.
 */
public class MemberService {
    private static final String FILENAME = "members.txt";
    private final List<Member> members = new ArrayList<>();

    public MemberService() {
        loadMembers();
        if (members.isEmpty()) {
            members.add(new Member("M001", "Abdulaziz", 75.0, 180.0, "Bulk", new Membership(Membership.PlanType.VIP, "2026-01-01")));
            members.add(new Member("M002", "Boburjon", 70.0, 175.0, "Maintain", new Membership(Membership.PlanType.PREMIUM, "2026-02-01")));
            members.add(new Member("M003", "Rahmatulloh", 80.0, 185.0, "Strength", new Membership(Membership.PlanType.BASIC, "2026-03-01")));
            members.add(new Member("M004", "Asomiddin", 65.0, 170.0, "Weight Loss", new Membership(Membership.PlanType.BASIC, "2026-03-10")));
            saveMembers();
        }
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    public void addMember(Member member) {
        members.add(member);
        saveMembers();
    }

    public boolean deleteMember(String id) {
        boolean removed = members.removeIf(m -> m.getId().equalsIgnoreCase(id));
        if (removed) {
            saveMembers();
        }
        return removed;
    }

    public Member findMemberById(String id) {
        return members.stream()
                .filter(m -> m.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public void updateMember(Member updatedMember) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equalsIgnoreCase(updatedMember.getId())) {
                members.set(i, updatedMember);
                saveMembers();
                return;
            }
        }
    }

    private void saveMembers() {
        List<String> data = new ArrayList<>();
        for (Member m : members) {
            String membershipType = m.getMembership() != null ? m.getMembership().getType().name() : "NONE";
            String startDate = m.getMembership() != null ? m.getMembership().getStartDate() : "N/A";
            data.add(String.join("|",
                    m.getId(),
                    m.getName(),
                    String.valueOf(m.getWeight()),
                    String.valueOf(m.getHeight()),
                    m.getGoal(),
                    membershipType,
                    startDate));
        }
        FileManager.saveToFile(FILENAME, data);
    }

    private void loadMembers() {
        List<String> data = FileManager.readFromFile(FILENAME);
        members.clear();
        for (String line : data) {
            String[] parts = line.split("\\|");
            if (parts.length >= 7) {
                String id = parts[0];
                String name = parts[1];
                double weight = Double.parseDouble(parts[2]);
                double height = Double.parseDouble(parts[3]);
                String goal = parts[4];
                String mTypeStr = parts[5];
                String startDate = parts[6];

                Membership membership = null;
                if (!mTypeStr.equals("NONE")) {
                    membership = new Membership(Membership.PlanType.valueOf(mTypeStr), startDate);
                }
                members.add(new Member(id, name, weight, height, goal, membership));
            }
        }
    }
}
