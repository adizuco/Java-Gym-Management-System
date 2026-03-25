package models;

/**
 * Member: Boburjon
 * Task: Implement the Member entity with personal details, weight/height
 * history, and goal tracking.
 */
public class Member {
    private final String id;
    private final String name;
    private double weight;
    private double height;
    private String goal;
    private Membership membership;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(String id, String name, double weight, double height, String goal, Membership membership) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.membership = membership;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Weight: " + weight + " | Height: " + height + " | Goal: " + goal + " | Membership: " + (membership != null ? membership : "None");
    }
}
