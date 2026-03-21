package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Member: Rahmatulloh
 * Task: Implement the TrainingSession entity for group classes or individual
 * training slots.
 */
public class TrainingSession {
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final String sessionId;
    private final String memberId;
    private final String memberName;
    private final String trainerId;
    private final String trainerName;
    private final String sessionType;
    private final LocalDateTime startTime;
    private final int durationMinutes;
    private final boolean groupSession;

    public TrainingSession(String sessionId, String memberId, String memberName, String trainerId,
            String trainerName, String sessionType, LocalDateTime startTime,
            int durationMinutes, boolean groupSession) {
        this.sessionId = sessionId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.sessionType = sessionType;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.groupSession = groupSession;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public String getSessionType() {
        return sessionType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return startTime.plusMinutes(durationMinutes);
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public boolean isGroupSession() {
        return groupSession;
    }

    public String getDisplaySlot() {
        return startTime.format(DISPLAY_FORMAT);
    }

    @Override
    public String toString() {
        String mode = groupSession ? "Group" : "Personal";
        return sessionId + " | " + memberName + " | Trainer: " + trainerName
                + " | " + sessionType + " | " + getDisplaySlot()
                + " | " + durationMinutes + " min | " + mode;
    }
}
