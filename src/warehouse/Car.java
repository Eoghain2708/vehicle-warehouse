package warehouse;

import java.util.Objects;

public class Car extends Vehicle {

    public enum Variation { COUPE, SALOON, ESTATE, CABRIOLET }

    private final double engineSize;
    private final boolean sunRoof;
    private final Variation bodyType;

    public static class Builder extends Vehicle.Builder<Builder> {

        private double engineSize;
        private boolean sunRoof;
        private final Variation bodyType;


        /**
         * Must take in name, modality, price, bodyType.
         * Can then take engineSize(double), horsepower(int),
         * topSpeed(int), sunRoof(bool)
         * @param name - string
         * @param modality - Modality.LAND/AIR/WATER
         * @param price - int
         * @param bodyType - (Variation.COUPLE, SALOON, ESTATE, CABRIOLET)
         */
        public Builder(String name, Modality modality, int price, Variation bodyType) {
            super(name, modality, price);
            this.bodyType = bodyType;

        }

        public Builder engineSize(double engineSize) {
            this.engineSize = engineSize;
            return self();
        }


        public Builder sunRoof(boolean sunRoof) {
            this.sunRoof = sunRoof;
            return self();
        }


        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Car build() {
            return new Car(this);
        }
    }


    private Car(Builder builder) {
        super(builder);
        this.bodyType = builder.bodyType;
        this.engineSize = builder.engineSize;
        this.sunRoof = builder.sunRoof;

    }

    @Override
    public String toString() {
        String res = "";
        res += "ID: " + getID();
        res += "\n";
        res += "Name: " + getName();
        res += "\n";
        res += "Colour: " + getColour();
        res += "\n";
        res += "Travels on: " + getModality().getDesc();
        res += "\n";
        res += "Price: " + getPrice();
        res += "\n";
        res += "Body type: " + bodyType;
        res += "\n";
        res += "Engine size: " + (engineSize != 0.0 ? engineSize + "L" : "engine size not set");
        res += "\n";
        res += "Horsepower: " + (getHorsepower() != 0 ? getHorsepower() + "bhp": "horsepower not set");
        res += "\n";
        res += "Top speed: " + (getTopSpeed() != 0 ? getTopSpeed() + "mph" : "top speed not set");
        res += "\n";
        res += sunRoof ? "The " + getName() + "has a sunroof." : "This car does not have a sunroof.";

        return res;
    }


    // validations
    private void validateVariation(Variation variation) throws IllegalArgumentException {
        Objects.requireNonNull(variation);
    }

}
