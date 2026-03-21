package services;

import java.util.ArrayList;
import java.util.List;
import models.Member;

/**
 * Member: Boburjon
 * Task: Implement CRUD operations for members, including registration and
 * profile updates.
 */
public class MemberService {
    private final List<Member> members = new ArrayList<>();

    public MemberService() {
        members.add(new Member("M001", "Abdulaziz"));
        members.add(new Member("M002", "Boburjon"));
        members.add(new Member("M003", "Rahmatulloh"));
        members.add(new Member("M004", "Asomiddin"));
    }

    public List<Member> getAllMembers() {
        return members;
    }
}
