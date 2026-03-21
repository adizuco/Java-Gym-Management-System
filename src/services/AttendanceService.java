package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Member: Asomiddin
 * Task: Implement attendance tracking logic for check-ins and member activity
 * logs.
 */
public class AttendanceService {
    private final Map<String, List<AttendanceRecord>> attendanceLog; // memberId -> list of records
    private final Map<String, LocalDateTime> currentCheckIns; // memberId -> check-in time
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AttendanceService() {
        this.attendanceLog = new HashMap<>();
        this.currentCheckIns = new HashMap<>();
    }

    /**
     * Record a member check-in
     */
    public boolean checkIn(String memberId, String memberName) {
        if (currentCheckIns.containsKey(memberId)) {
            return false; // Already checked in
        }

        LocalDateTime checkInTime = LocalDateTime.now();
        currentCheckIns.put(memberId, checkInTime);

        // Initialize attendance log for member if not exists
        attendanceLog.putIfAbsent(memberId, new ArrayList<>());

        return true;
    }

    /**
     * Record a member check-out and create attendance record
     */
    public AttendanceRecord checkOut(String memberId, String memberName) {
        if (!currentCheckIns.containsKey(memberId)) {
            return null; // Not checked in
        }

        LocalDateTime checkInTime = currentCheckIns.remove(memberId);
        LocalDateTime checkOutTime = LocalDateTime.now();
        int durationMinutes = (int) ((checkOutTime.toLocalTime().toNanoOfDay() -
                checkInTime.toLocalTime().toNanoOfDay()) / 60_000_000_000L);

        AttendanceRecord record = new AttendanceRecord(memberId, memberName, checkInTime, checkOutTime,
                durationMinutes);
        attendanceLog.get(memberId).add(record);

        return record;
    }

    /**
     * Get all attendance records for a specific member
     */
    public List<AttendanceRecord> getMemberAttendance(String memberId) {
        return new ArrayList<>(attendanceLog.getOrDefault(memberId, new ArrayList<>()));
    }

    /**
     * Get attendance statistics for a member
     */
    public AttendanceStatistics getAttendanceStats(String memberId) {
        List<AttendanceRecord> records = attendanceLog.getOrDefault(memberId, new ArrayList<>());
        if (records.isEmpty()) {
            return null;
        }

        int totalVisits = records.size();
        long totalMinutes = records.stream().mapToLong(r -> r.durationMinutes).sum();
        double averageMinutes = totalMinutes / (double) totalVisits;

        return new AttendanceStatistics(memberId, totalVisits, totalMinutes, averageMinutes);
    }

    /**
     * Check if a member is currently checked in
     */
    public boolean isCheckedIn(String memberId) {
        return currentCheckIns.containsKey(memberId);
    }

    /**
     * Get all members currently checked in
     */
    public List<String> getCurrentlyCheckedIn() {
        return new ArrayList<>(currentCheckIns.keySet());
    }

    /**
     * Inner class for attendance records
     */
    public static class AttendanceRecord {
        public String memberId;
        public String memberName;
        public LocalDateTime checkInTime;
        public LocalDateTime checkOutTime;
        public int durationMinutes;

        public AttendanceRecord(String memberId, String memberName, LocalDateTime checkInTime,
                LocalDateTime checkOutTime, int durationMinutes) {
            this.memberId = memberId;
            this.memberName = memberName;
            this.checkInTime = checkInTime;
            this.checkOutTime = checkOutTime;
            this.durationMinutes = durationMinutes;
        }

        @Override
        public String toString() {
            return String.format("%s (%s) | In: %s | Out: %s | Duration: %d min",
                    memberName, memberId,
                    checkInTime.format(TIME_FORMAT),
                    checkOutTime.format(TIME_FORMAT),
                    durationMinutes);
        }
    }

    /**
     * Inner class for attendance statistics
     */
    public static class AttendanceStatistics {
        public String memberId;
        public int totalVisits;
        public long totalMinutes;
        public double averageMinutes;

        public AttendanceStatistics(String memberId, int totalVisits, long totalMinutes, double averageMinutes) {
            this.memberId = memberId;
            this.totalVisits = totalVisits;
            this.totalMinutes = totalMinutes;
            this.averageMinutes = averageMinutes;
        }

        @Override
        public String toString() {
            return String.format("Member %s - Total Visits: %d | Total Time: %d min | Avg Session: %.1f min",
                    memberId, totalVisits, totalMinutes, averageMinutes);
        }
    }
}
