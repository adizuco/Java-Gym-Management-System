package models;

/**
 * Member: Boburjon
 * Task: Implement the Member entity with personal details, weight/height
 * history, and goal tracking.
 */
public class Member {
    private final String id;
    private final String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
