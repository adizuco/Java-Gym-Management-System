package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Member: Rahmatulloh
 * Task: Implement the Trainer entity with specialization, availability, and client list.
 */
public class Trainer {
    private String id;
    private String name;
    private String specialty;
    private List<String> availableSlots;

    public Trainer(String id, String name, String specialty, List<String> availableSlots) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.availableSlots = new ArrayList<>(availableSlots);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public List<String> getAvailableSlots() {
        return new ArrayList<>(availableSlots);
    }

    public boolean isAvailableAt(String slot) {
        return availableSlots.contains(slot);
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + specialty + ") Available: " + String.join(", ", availableSlots);
    }
}
