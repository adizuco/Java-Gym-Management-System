package models;

/**
 * Member: Boburjon
 * Task: Implement the Membership entity defining plan types, duration, and pricing.
 */
public class Membership {
    public enum PlanType {
        BASIC("Basic", 30.0, 1),
        PREMIUM("Premium", 75.0, 3),
        VIP("VIP", 250.0, 12);

        private final String displayName;
        private final double price;
        private final int durationMonths;

        PlanType(String displayName, double price, int durationMonths) {
            this.displayName = displayName;
            this.price = price;
            this.durationMonths = durationMonths;
        }

        public String getDisplayName() { return displayName; }
        public double getPrice() { return price; }
        public int getDurationMonths() { return durationMonths; }

        @Override
        public String toString() {
            return displayName + " ($" + price + " for " + durationMonths + " month(s))";
        }
    }

    private final PlanType type;
    private final String startDate;

    public Membership(PlanType type, String startDate) {
        this.type = type;
        this.startDate = startDate;
    }

    public PlanType getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return type.getDisplayName() + " (Started: " + startDate + ")";
    }
}
