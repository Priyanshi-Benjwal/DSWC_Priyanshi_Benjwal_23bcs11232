package Day4.Q1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

abstract class Cargo {
    protected String containerId;
    protected double valueInCredits;
    protected boolean isHazardous;

    public Cargo(String containerId,
                 double valueInCredits,
                 boolean isHazardous) {
        this.containerId = containerId;
        this.valueInCredits = valueInCredits;
        this.isHazardous = isHazardous;
    }

    public String getContainerId() {
        return containerId;
    }

    public double getValueInCredits() {
        return valueInCredits;
    }

    public boolean isHazardous() {
        return isHazardous;
    }
}

class StandardCargo extends Cargo {

    public StandardCargo(String containerId,
                         double valueInCredits,
                         boolean isHazardous) {
        super(containerId, valueInCredits, isHazardous);
    }
}

class BiologicalCargo extends Cargo {

    private boolean isShielded;

    public BiologicalCargo(String containerId,
                           double valueInCredits,
                           boolean isHazardous,
                           boolean isShielded) {
        super(containerId, valueInCredits, isHazardous);
        this.isShielded = isShielded;
    }

    public boolean isShielded() {
        return isShielded;
    }
}

@FunctionalInterface
interface CargoInspector {
    boolean inspect(Cargo cargo);
}

@FunctionalInterface
interface CargoCompressor {
    String compress(Cargo cargo);
}

class ManifestProcessor {

    public List<String> processManifest(
            List<Cargo> manifest,
            CargoInspector inspector,
            CargoCompressor compressor) {

        return manifest.stream()
                .filter(inspector::inspect)
                .filter(cargo -> cargo.getValueInCredits() >= 1000.0)
                .map(compressor::compress)
                .collect(Collectors.toList());
    }
}

public class Main {

    public static void main(String[] args) {

        List<Cargo> manifest = Arrays.asList(

                new StandardCargo(
                        "ALPHA-99",
                        5000.50,
                        false),

                new BiologicalCargo(
                        "BETA-11",
                        8000.00,
                        true,
                        false),

                new BiologicalCargo(
                        "GAMMA-22",
                        7000.00,
                        true,
                        true),

                new StandardCargo(
                        "DELTA-33",
                        700.00,
                        false),

                new StandardCargo(
                        "OMEGA-44",
                        12000.00,
                        false)
        );

        CargoInspector inspector = cargo -> {

            if (cargo.isHazardous()
                    && cargo instanceof BiologicalCargo) {

                BiologicalCargo bio =
                        (BiologicalCargo) cargo;

                return bio.isShielded();
            }

            return true;
        };

        CargoCompressor compressor = cargo ->
                cargo.getContainerId().substring(0, 4)
                        + "-"
                        + (int) cargo.getValueInCredits();

        ManifestProcessor processor =
                new ManifestProcessor();

        List<String> result =
                processor.processManifest(
                        manifest,
                        inspector,
                        compressor);

        System.out.println("Compressed Telemetry Data:");

        result.forEach(System.out::println);
    }
}
