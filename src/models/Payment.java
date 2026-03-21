package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Member: Asomiddin
 * Task: Implement the Payment entity for tracking transactions, receipts, and
 * billing history.
 */
public class Payment {
    private final String transactionId;
    private final String memberId;
    private final String memberName;
    private final double amount;
    private final String membershipType; // Gold, Silver, Bronze
    private final LocalDateTime transactionDate;
    private String status; // PAID, PENDING, FAILED
    private final String description;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Payment(String transactionId, String memberId, String memberName, double amount,
            String membershipType, LocalDateTime transactionDate, String status, String description) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.amount = amount;
        this.membershipType = membershipType;
        this.transactionDate = transactionDate;
        this.status = status;
        this.description = description;
    }

    // Getters
    public String getTransactionId() {
        return transactionId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public double getAmount() {
        return amount;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Receipt - ID: %s | Member: %s (%s) | Amount: $%.2f | Type: %s | Date: %s | Status: %s",
                transactionId, memberName, memberId, amount, membershipType,
                transactionDate.format(DATE_FORMAT), status);
    }

    public String getFormattedReceipt() {
        return """
                ===== PAYMENT RECEIPT =====
                Transaction ID: %s
                Member: %s (%s)
                Membership Type: %s
                Amount: $%.2f
                Date: %s
                Status: %s
                Description: %s
                ============================="""
                .formatted(transactionId, memberName, memberId, membershipType,
                        amount, transactionDate.format(DATE_FORMAT), status, description);
    }
}
