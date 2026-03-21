package services;

import models.Payment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Member: Asomiddin
 * Task: Implement payment processing, billing history, and transaction
 * management.
 */
public class PaymentService {
    private final List<Payment> transactionHistory;
    private int transactionCounter;

    public PaymentService() {
        this.transactionHistory = new ArrayList<>();
        this.transactionCounter = 1;
    }

    /**
     * Process a payment for a member
     */
    public Payment processPayment(String memberId, String memberName, double amount,
            String membershipType, String description) {
        String transactionId = "TXN-" + String.format("%05d", transactionCounter++);
        Payment payment = new Payment(transactionId, memberId, memberName, amount,
                membershipType, LocalDateTime.now(), "PAID", description);
        transactionHistory.add(payment);
        return payment;
    }

    /**
     * Create a pending payment record
     */
    public Payment createPendingPayment(String memberId, String memberName, double amount,
            String membershipType, String description) {
        String transactionId = "TXN-" + String.format("%05d", transactionCounter++);
        Payment payment = new Payment(transactionId, memberId, memberName, amount,
                membershipType, LocalDateTime.now(), "PENDING", description);
        transactionHistory.add(payment);
        return payment;
    }

    /**
     * Get all payments for a specific member
     */
    public List<Payment> getMemberPayments(String memberId) {
        return transactionHistory.stream()
                .filter(p -> p.getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    /**
     * Get all payments from the billing history
     */
    public List<Payment> getAllPayments() {
        return new ArrayList<>(transactionHistory);
    }

    /**
     * Get total revenue (all paid transactions)
     */
    public double getTotalRevenue() {
        return transactionHistory.stream()
                .filter(p -> p.getStatus().equals("PAID"))
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    /**
     * Get pending payments amount
     */
    public double getPendingAmount() {
        return transactionHistory.stream()
                .filter(p -> p.getStatus().equals("PENDING"))
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    /**
     * Update payment status
     */
    public boolean updatePaymentStatus(String transactionId, String newStatus) {
        for (Payment payment : transactionHistory) {
            if (payment.getTransactionId().equals(transactionId)) {
                payment.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }

    /**
     * Get payment by transaction ID
     */
    public Payment getPaymentByTransactionId(String transactionId) {
        for (Payment payment : transactionHistory) {
            if (payment.getTransactionId().equals(transactionId)) {
                return payment;
            }
        }
        return null;
    }
}
