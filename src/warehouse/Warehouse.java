package warehouse;


import java.util.*;
import java.util.stream.Collectors;

public final class Warehouse {
    private final Set<Vehicle> vehicles = new HashSet<>();



    private Warehouse() {};

    public static Warehouse create() {

        return new Warehouse();
    }

    // GET DATA

    /**
     * returns ArrayList of all vehicles currently in the warehouse
     * @return ArrayList of Vehicles
     */
    public final VehicleSet getAllVehicles() {
        return new VehicleSet(vehicles);
    }

    public final VehicleSet getWarehouse() {
        return new VehicleSet(vehicles.stream().filter(v -> !v.isCheckedOut()).collect(Collectors.toSet()));
    }

    public final VehicleSet getCheckedOut() {
        return new VehicleSet(vehicles.stream().filter(Vehicle::isCheckedOut).collect(Collectors.toSet()));
    }

    /**
     * Takes an unknown class as long as it is a subclass of Vehicle<br>
     * <b>Usage:</b> getAllByType(Car.class);
     * @param vType Vehicle subclass .class
     * @return Set of Vehicles of the subclass type.
     */
    public VehicleSet getAllByType(Class<? extends Vehicle> vType) {
       return new VehicleSet(vehicles.stream().filter(vType::isInstance).collect(Collectors.toSet()));
    }


    /**
     * Takes an unknown class as long as it is a subclass of Vehicle<br>
     * @param vType
     * @return Set of Vehicles of the subclass type which are checked out (not in the warehouse)
     */
    public VehicleSet getAllCheckedOutByType(Class<? extends Vehicle> vType) {
        return new VehicleSet(vehicles.stream().filter(Vehicle::isCheckedOut)
                .filter(vType::isInstance)
                .collect(Collectors.toSet()));
    }

    /**
     * Takes an unknown class as long as it is a subclass of Vehicle<br>
     * @param vType
     * @return Set of vehicles of the subclass type which are not checked out (are in the warehouse)
     */
    public VehicleSet getWarehouseByType(Class<? extends Vehicle> vType) {
        return new VehicleSet(vehicles.stream().filter(v -> !v.isCheckedOut())
                .filter(vType::isInstance)
                .collect(Collectors.toSet()));
    }

    /**
     *
     * @return A string which prints the toString() method of every element in the either the warehouse or that is
     * currently checked out (in checkedOut)
     */
    public String getAllToString() {
        StringBuilder res = new StringBuilder();
        for (Vehicle v : vehicles) {
            res.append(v.toString())
                    .append("\n");

        }
        return res.toString();
    }




    // FUNCTIONALITY



    public final boolean add(Vehicle v) {
        Objects.requireNonNull(v);
        return vehicles.add(v);
    }

    public final boolean addAll(List<Vehicle> list) {
        Objects.requireNonNull(list);
        return vehicles.addAll(list);
    }

    public final boolean remove(Vehicle v) {
        Objects.requireNonNull(v);
        if (!vehicles.contains(v)) { System.out.println("Vehicle not found."); return false; }

        return vehicles.remove(v);
    }





}
