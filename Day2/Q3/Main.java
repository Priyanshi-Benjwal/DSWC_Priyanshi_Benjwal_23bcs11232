package Day2.Q3;

enum DoorState {
    OPEN,
    CLOSED,
    LOCKED
}

class IllegalStateTransitionException extends RuntimeException {

    public IllegalStateTransitionException(String message) {
        super(message);
    }
}
s
class VaultDoor {

    private DoorState state;

    public VaultDoor() {
        state = DoorState.OPEN; 
    }

    public void closeDoor() {
        if (state == DoorState.OPEN) {
            state = DoorState.CLOSED;
            System.out.println("Door closed successfully.");
        } else {
            System.out.println("Door is already closed or locked.");
        }
    }

    public void lockDoor() {

        if (state == DoorState.OPEN) {
            throw new IllegalStateTransitionException(
                "Cannot lock an OPEN door. Close the door first."
            );
        }

        if (state == DoorState.CLOSED) {
            state = DoorState.LOCKED;
            System.out.println("Door locked successfully.");
        } else {
            System.out.println("Door is already locked.");
        }
    }

    public void unlockDoor() {
        if (state == DoorState.LOCKED) {
            state = DoorState.CLOSED;
            System.out.println("Door unlocked successfully.");
        } else {
            System.out.println("Door is not locked.");
        }
    }

    public DoorState getState() {
        return state;
    }
}

public class VaultGuardSystem {

    public static void main(String[] args) {

        VaultDoor vault = new VaultDoor();

        System.out.println("Initial State: " + vault.getState());

        try {

            vault.lockDoor();

        } catch (IllegalStateTransitionException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        vault.closeDoor();
        System.out.println("Current State: " + vault.getState());

        vault.lockDoor();
        System.out.println("Current State: " + vault.getState());

        vault.unlockDoor();
        System.out.println("Current State: " + vault.getState());
    }
}
