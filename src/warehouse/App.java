package warehouse;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Car car = new Car.Builder("Ferrari", Vehicle.Modality.LAND, 350000, Car.Variation.COUPE).
                engineSize(3.5).sunRoof(false).horsepower(450).topSpeed(200).build();

        Car car2 = new Car.Builder("Lamborghini Aventador", Vehicle.Modality.LAND, 165000, Car.Variation.COUPE).
                engineSize(4.6).horsepower(545).build();

        Boat boat = new Boat.Builder("Steve", Vehicle.Modality.AIR, 60000, Boat.Variation.YACHT, Boat.UseFor.OCEAN).
                numEngines(4).topSpeed(76).horsepower(3000).build();

        Warehouse warehouse = Warehouse.create();
        warehouse.add(car);
        warehouse.addAll(List.of(car, car2, boat));
        VehicleSet v = warehouse.getWarehouse();


        System.out.println(v.search(4).toString());
    }
}
