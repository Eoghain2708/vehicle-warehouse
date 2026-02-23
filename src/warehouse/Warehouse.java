package warehouse;

import errors.ErrorAddingItemException;
import errors.ErrorRemovingItemException;

import java.util.*;

public final class Warehouse {
    private Set<Vehicle> vehicles;
    private Set<Vehicle> checkedOut;


    private Warehouse() {};

    public static Warehouse create() {
        return new Warehouse();
    }

    // GET DATA

    /**
     * returns ArrayList of all vehicles currently in the warehouse
     * @return ArrayList of Vehicles
     */
    public final Set<Vehicle> warehouse() {
        return new HashSet<Vehicle>(vehicles);
    }


    /**
     * Get all vehicles which are currently checked out of the warehouse
     * @return Vehicle ArrayList
     */
    public final Set<Vehicle> checkedOut() {
        return new HashSet<Vehicle>(checkedOut);
    }

    /**
     * Takes an unknown class as long as it is a subclass of Vehicle
     * <b>Usage:</b> getAllByType(Car.class);
     * @param vType - Vehicle subclass .class
     * @return ArrayList of Vehicles of the subclass type.
     */
    public Set<Vehicle> getAllByType(Class<? extends Vehicle> vType) {
        Set<Vehicle> res = new HashSet<>();
        for (Vehicle v : vehicles) {
            if (vType.isInstance(v)) {
                res.add(v);
            }
        }
        return res;
    }

    /**
     *
     * @return A string which prints the toString() method of every element in the either the warehouse or that is
     * currently checked out (in checkedOut)
     */
    public String getAllToString(Set<Vehicle> set) {
        StringBuilder res = new StringBuilder();
        for (Vehicle v : set) {
            res.append(v.toString())
                    .append("\n");

        }
        return res.toString();
    }


    // SEARCH DATA

    /**
     * Takes a String and searches for the object in attribute ArrayList <b>vehicles</b>
     * with that name, then returns it if present. Returns null otherwise.
     * @param name - Name of vehicle
     * @return a Vehicle or null
     */
    public final Optional<Vehicle> search(String name, Set<Vehicle> set) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(set);
        for (Vehicle v: set) {
            if (v.getName().equals(name)) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    public final Optional<Vehicle> search(int id, Set<Vehicle> set) {
        Objects.requireNonNull(set);
        for (Vehicle v : set) {
            if (v.getID() == id) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    public final Optional<Vehicle> search(Vehicle v, Set<Vehicle> set) {
        Objects.requireNonNull(v);
        Objects.requireNonNull(set);

        return set.stream().filter(vehicle -> vehicle.equals(v)).findFirst();
    }


    // FUNCTIONALITY


    /**
     * Exists as a helper method for swap, which exists as a helper for checkIn and checkOut() and a wrapped method for
     * addToWarehouse()
     * @param v - Vehicle
     * @param dst - Vehicle Set
     * @return
     */
    private final boolean add(Vehicle v, Set<Vehicle> dst) {
        Objects.requireNonNull(v);
        Objects.requireNonNull(dst);
        try {
            dst.add(v);
            return true;
        } catch (ErrorAddingItemException e) {
            System.out.println("Error adding item to " + dst);
            System.out.println("Possible causes: Item is already in " + dst + " or item is invalid type for " + dst);
        }
        return false;
    }


    /**
     * Exists as a helper for swap, which exists as a helper for checkIn and checkOut(), and a wrapped method for
     * removeFromWarehouse();
     * @param v - Vehicle
     * @param dst - Vehicle Set
     * @return
     */
    private final boolean remove(Vehicle v, Set<Vehicle> dst) {
        Objects.requireNonNull(v);
        Objects.requireNonNull(dst);
        if (!dst.contains(v)) { System.out.println("Vehicle not found."); return false; }
        try {
            dst.remove(v);
            return true;
        } catch (ErrorRemovingItemException e) {
            System.out.println("Error removing item from " + dst);
        }
        return false;
    }

    private final boolean swap(Vehicle v, Set<Vehicle> start, Set<Vehicle> dst) {
        try {
            remove(v, start);
            add(v, dst);
            return true;
        } catch (ErrorRemovingItemException e) {
            System.out.println("Swap failed when removing item from " + start);
        } catch(ErrorAddingItemException e) {
            System.out.println("Swap failed when adding item to " + dst);
        }
        return false;
    }

    public boolean checkOut(Vehicle v) {
        return swap(v, vehicles, checkedOut);
    }

    public boolean checkIn(Vehicle v) {
        return swap(v, checkedOut, vehicles);
    }

    public boolean addToWarehouse(Vehicle v) {
        return add(v, vehicles);
    }

    public boolean removeFromWarehouse(Vehicle v) {
        return remove(v, vehicles);
    }



}
