package Day3.Q2;

import java.util.PriorityQueue;

// Enum for triage levels
enum TriageLevel {
    CRITICAL,
    URGENT,
    STABLE
}

// Patient class implementing Comparable
class Patient implements Comparable<Patient> {
    private String name;
    private TriageLevel severity;
    private long arrivalTime;

    public Patient(String name, TriageLevel severity, long arrivalTime) {
        this.name = name;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public TriageLevel getSeverity() {
        return severity;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public int compareTo(Patient other) {

        // Priority: CRITICAL > URGENT > STABLE
        int thisPriority = getSeverityPriority(this.severity);
        int otherPriority = getSeverityPriority(other.severity);

        if (thisPriority != otherPriority) {
            return Integer.compare(otherPriority, thisPriority);
        }

        // If same severity, earlier arrival gets priority
        return Long.compare(this.arrivalTime, other.arrivalTime);
    }

    private int getSeverityPriority(TriageLevel level) {
        switch (level) {
            case CRITICAL:
                return 3;
            case URGENT:
                return 2;
            case STABLE:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", severity=" + severity +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}

// Triage Manager
class TriageManager {

    private PriorityQueue<Patient> waitingRoom;

    public TriageManager() {
        waitingRoom = new PriorityQueue<>();
    }

    // Add patient
    public void admitPatient(Patient p) {
        waitingRoom.offer(p);
    }

    // Get highest-priority patient
    public Patient getNextPatient() {
        return waitingRoom.poll();
    }

    public boolean isEmpty() {
        return waitingRoom.isEmpty();
    }
}

// Driver Class
public class Main {

    public static void main(String[] args) {

        TriageManager manager = new TriageManager();

        manager.admitPatient(
                new Patient("John", TriageLevel.STABLE, 1003));

        manager.admitPatient(
                new Patient("Alice", TriageLevel.CRITICAL, 1001));

        manager.admitPatient(
                new Patient("Bob", TriageLevel.URGENT, 1002));

        manager.admitPatient(
                new Patient("Charlie", TriageLevel.CRITICAL, 1000));

        System.out.println("Treatment Order:");

        while (!manager.isEmpty()) {
            System.out.println(manager.getNextPatient());
        }
    }
}
