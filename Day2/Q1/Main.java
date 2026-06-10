package Day2.Q1;


interface BatteryOperated {
    int getBatteryLevel();
    void triggerRechargeAlert();
}


abstract class SmartDevice {
    protected String deviceId;
    protected String deviceName;

    public SmartDevice(String deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    public abstract void runDiagnostic();
}


class SmartLight extends SmartDevice {

    public SmartLight(String deviceId, String deviceName) {
        super(deviceId, deviceName);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("Light [" + deviceName + "] diagnostic: Turning ON/OFF successfully.");
    }
}

class SmartCamera extends SmartDevice implements BatteryOperated {
    private int batteryLevel;

    public SmartCamera(String deviceId, String deviceName, int batteryLevel) {
        super(deviceId, deviceName);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void runDiagnostic() {
        System.out.println("Camera [" + deviceName + "] diagnostic: Recording test successful.");
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public void triggerRechargeAlert() {
        System.out.println("ALERT: Camera [" + deviceName + "] battery low! Please recharge.");
    }
}

class SmartLock extends SmartDevice implements BatteryOperated {
    private int batteryLevel;

    public SmartLock(String deviceId, String deviceName, int batteryLevel) {
        super(deviceId, deviceName);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void runDiagnostic() {
        System.out.println("Lock [" + deviceName + "] diagnostic: Lock/Unlock cycle successful.");
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public void triggerRechargeAlert() {
        System.out.println("ALERT: Lock [" + deviceName + "] battery low! Please recharge.");
    }
}

class HomeHub {

    public void executeNightlyRoutine(SmartDevice[] devices) {

        System.out.println("\n===== NIGHTLY DIAGNOSTIC ROUTINE =====\n");

        for (SmartDevice device : devices) {

            // Polymorphic call
            device.runDiagnostic();

            // Safe type checking and downcasting
            if (device instanceof BatteryOperated) {
                BatteryOperated batteryDevice = (BatteryOperated) device;

                System.out.println("Battery Level: "
                        + batteryDevice.getBatteryLevel() + "%");

                if (batteryDevice.getBatteryLevel() < 20) {
                    batteryDevice.triggerRechargeAlert();
                }
            }

            System.out.println("--------------------------------");
        }
    }
}

public class EcoSmartSystem {

    public static void main(String[] args) {

        SmartDevice[] devices = {
                new SmartLight("L101", "Living Room Light"),
                new SmartCamera("C201", "Front Door Camera", 15),
                new SmartLock("SL301", "Main Gate Lock", 45),
                new SmartCamera("C202", "Garage Camera", 10)
        };

        HomeHub hub = new HomeHub();
        hub.executeNightlyRoutine(devices);
    }
}
