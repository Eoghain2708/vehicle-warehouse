package warehouse;

public class Boat extends Vehicle {
    public enum Variation { YACHT, SHIP, SPEEDBOAT, JETSKI; }
    public enum UseFor { BEACH, CANAL, RIVER, LAKE, OCEAN; }


    private final int numEngines;
    private final Variation bodyType;
    private final UseFor suitable;

    public static class Builder extends Vehicle.Builder<Builder> {
        // necessary
        private final Variation bodyType;
        private final UseFor suitable;

        // optional
        private int numEngines;


        /**
         * Necessitates 5 params, with chained methods numEngines(int) available specific to boat, alongside
         * horsepower(int), topSpeed(int), color(String)
         * @param name - String
         * @param modality - Modality.AIR, LAND, WATER
         * @param price - int
         * @param bodyType - Variation.YACHT, SHIP, SPEEDBOAT, JETSKI
         * @param suitable - UseFor.BEACH, CANAL, RIVER, LAKE, OCEAN
         */
        public Builder(String name, Modality modality, int price, Variation bodyType, UseFor suitable) {
            super(name, modality, price);
            this.bodyType = bodyType;
            this.suitable = suitable;
        }

        public Builder numEngines(int val) {
            this.numEngines = val;
            return self();
        }


        protected Builder self() { return this; }
        public Boat build() { return new Boat(this); }
    }


    private Boat(Builder builder) {
        super(builder);
        this.bodyType = builder.bodyType;
        this.suitable = builder.suitable;
        this.numEngines = builder.numEngines;
    }

    public int getNumEngines() { return numEngines; }
    public Variation getBodyType() { return bodyType; }
    public UseFor getSuitable() { return suitable; }

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
        res += "Suitable for: " + getSuitable();
        res += "\n";
        res += "Price: " + getPrice();
        res += "\n";
        res += "Body type: " + bodyType;
        res += "\n";
        res += "Num. engines: " + (numEngines != 0 ? numEngines : "engine size not set");
        res += "\n";
        res += "Horsepower: " + (getHorsepower() != 0 ? getHorsepower() + "bhp": "horsepower not set");
        res += "\n";
        res += "Top speed: " + (getTopSpeed() != 0 ? getTopSpeed() + "mph" : "top speed not set");

        return res;
    }
}
