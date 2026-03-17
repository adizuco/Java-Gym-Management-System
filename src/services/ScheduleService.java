package services;

import models.Member;
import models.Trainer;
import models.TrainingSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Member: Rahmatulloh
 * Task: Implement scheduling logic, booking sessions, and preventing time conflicts.
 */
public class ScheduleService {
    private static final DateTimeFormatter SLOT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private List<Trainer> trainers = new ArrayList<>();
    private List<TrainingSession> sessions = new ArrayList<>();
    private int sessionCounter = 1;

    public ScheduleService() {
        trainers.add(new Trainer(
                "T001",
                "Akmal",
                "Strength Training",
                Arrays.asList("2026-03-19 09:00", "2026-03-19 11:00", "2026-03-20 15:00")
        ));
        trainers.add(new Trainer(
                "T002",
                "Dilshod",
                "Weight Loss",
                Arrays.asList("2026-03-19 10:00", "2026-03-19 14:00", "2026-03-20 16:00")
        ));
        trainers.add(new Trainer(
                "T003",
                "Madina",
                "Yoga and Mobility",
                Arrays.asList("2026-03-19 08:00", "2026-03-20 09:00", "2026-03-20 17:00")
        ));
    }

    public List<Trainer> getAllTrainers() {
        return new ArrayList<>(trainers);
    }

    public List<TrainingSession> getAllSessions() {
        return new ArrayList<>(sessions);
    }

    public String bookSession(Member member, String trainerId, String requestedSlot,
                              int durationMinutes, boolean groupSession) {
        Trainer trainer = findTrainerById(trainerId);
        if (trainer == null) {
            return "Trainer not found.";
        }

        if (!trainer.isAvailableAt(requestedSlot)) {
            return "Trainer is not available at that time.";
        }

        LocalDateTime startTime;
        try {
            startTime = LocalDateTime.parse(requestedSlot, SLOT_FORMAT);
        } catch (DateTimeParseException ex) {
            return "Invalid date format. Use yyyy-MM-dd HH:mm";
        }

        if (hasTrainerConflict(trainerId, startTime, durationMinutes)) {
            return "Booking rejected. Trainer is already assigned during that time.";
        }

        String sessionType = trainer.getSpecialty();
        TrainingSession session = new TrainingSession(
                generateSessionId(),
                member.getId(),
                member.getName(),
                trainer.getId(),
                trainer.getName(),
                sessionType,
                startTime,
                durationMinutes,
                groupSession
        );

        sessions.add(session);
        return "Session booked successfully: " + session.getSessionId();
    }

    public Trainer findTrainerById(String trainerId) {
        for (Trainer trainer : trainers) {
            if (trainer.getId().equalsIgnoreCase(trainerId)) {
                return trainer;
            }
        }
        return null;
    }

    public boolean hasTrainerConflict(String trainerId, LocalDateTime requestedStart, int durationMinutes) {
        LocalDateTime requestedEnd = requestedStart.plusMinutes(durationMinutes);

        for (TrainingSession session : sessions) {
            if (!session.getTrainerId().equalsIgnoreCase(trainerId)) {
                continue;
            }

            boolean overlaps = requestedStart.isBefore(session.getEndTime())
                    && requestedEnd.isAfter(session.getStartTime());
            if (overlaps) {
                return true;
            }
        }

        return false;
    }

    private String generateSessionId() {
        return String.format("S%03d", sessionCounter++);
    }
}
