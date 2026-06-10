package Day3.Q1;

import java.util.*;

// Passenger Class
class Passenger {
    private String passportNumber;
    private String fullName;
    private String nationality;

    public Passenger(String passportNumber, String fullName, String nationality) {
        this.passportNumber = passportNumber;
        this.fullName = fullName;
        this.nationality = nationality;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNationality() {
        return nationality;
    }

    // Two passengers are identical if passportNumber and nationality are same
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Passenger other = (Passenger) obj;

        return Objects.equals(passportNumber, other.passportNumber)
                && Objects.equals(nationality, other.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportNumber, nationality);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passportNumber='" + passportNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}

// Manifest Manager Class
class ManifestManager {

    // No-Fly List (O(1) average lookup)
    private Set<Passenger> globalNoFlyList;

    // Flight Roster (maintains check-in order)
    private Map<String, List<Passenger>> flightRosters;

    // Passenger -> Flight Number lookup (O(1) average)
    private Map<Passenger, String> globalPassengerDirectory;

    public ManifestManager() {
        globalNoFlyList = new HashSet<>();
        flightRosters = new HashMap<>();
        globalPassengerDirectory = new HashMap<>();
    }

    // Add passenger to no-fly list
    public void addToNoFlyList(Passenger p) {
        globalNoFlyList.add(p);
    }

    // Check-in passenger
    public boolean processCheckIn(String flightNumber, Passenger p) {

        // Reject if on no-fly list
        if (globalNoFlyList.contains(p)) {
            return false;
        }

        // Add passenger to flight roster
        flightRosters
                .computeIfAbsent(flightNumber, k -> new ArrayList<>())
                .add(p);

        // Update global directory
        globalPassengerDirectory.put(p, flightNumber);

        return true;
    }

    // Locate passenger's flight
    public String locatePassengerFlight(Passenger p) {
        return globalPassengerDirectory.get(p);
    }

    // Display roster for a flight
    public void displayFlightRoster(String flightNumber) {
        List<Passenger> passengers = flightRosters.get(flightNumber);

        if (passengers == null || passengers.isEmpty()) {
            System.out.println("No passengers checked in for flight " + flightNumber);
            return;
        }

        System.out.println("\nFlight " + flightNumber + " Roster:");
        for (Passenger p : passengers) {
            System.out.println(p);
        }
    }
}

// Driver Class
public class Main {
    public static void main(String[] args) {

        ManifestManager manager = new ManifestManager();

        Passenger p1 = new Passenger("P12345", "Alice Johnson", "USA");
        Passenger p2 = new Passenger("P67890", "Bob Smith", "UK");
        Passenger p3 = new Passenger("P11111", "Charlie Brown", "Canada");

        // Add Charlie to No-Fly List
        manager.addToNoFlyList(p3);

        System.out.println("Alice Check-In: "
                + manager.processCheckIn("AI101", p1));

        System.out.println("Bob Check-In: "
                + manager.processCheckIn("AI101", p2));

        System.out.println("Charlie Check-In: "
                + manager.processCheckIn("AI101", p3));

        // Display flight roster
        manager.displayFlightRoster("AI101");

        // Search using a different object with same passport and nationality
        Passenger searchAlice =
                new Passenger("P12345", "Alice Williams", "USA");

        System.out.println("\nLocated Flight for Alice: "
                + manager.locatePassengerFlight(searchAlice));
    }
}
