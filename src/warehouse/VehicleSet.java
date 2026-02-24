package warehouse;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class VehicleSet {
    private final Set<Vehicle> vehicles;

    public VehicleSet(Set<Vehicle> vehicles) {
        this.vehicles = Set.copyOf(vehicles);
    }

    // SEARCH DATA

    /**
     * Takes a String and searches for the object in attribute ArrayList <b>vehicles</b>
     * with that name, then returns it if present. Returns null otherwise.
     * @param name - Name of vehicle
     * @return a Vehicle or null
     */


    public final Optional<Vehicle> search(String name) {
        Objects.requireNonNull(name);
        return vehicles.stream().filter(v -> v.getName().equals(name)).findFirst();
    }


    public final Optional<Vehicle> search(int id) {
        Objects.requireNonNull(vehicles);
        return vehicles.stream().filter(v -> v.getID() == id).findFirst();
    }

    public final Optional<Vehicle> search(Vehicle v) {
        Objects.requireNonNull(v);
        Objects.requireNonNull(vehicles);

        return vehicles.stream().filter(vehicle -> vehicle.equals(v)).findFirst();
    }





    public Set<Vehicle> asSet() {
        return vehicles;
    }


}
