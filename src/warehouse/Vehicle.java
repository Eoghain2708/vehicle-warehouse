package warehouse;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Vehicle {

    public enum Modality {
        AIR("Air"), LAND("Land"), WATER("Water");

        private final String desc;

        private Modality(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    private static final AtomicInteger nextID = new AtomicInteger();
    // necessary
    private final String name;
    private final int id;
    private final Modality modality;
    private final int price;


    // optional
    private final int year;
    private final String model;
    private final String colour;
    private final int horsepower;
    private final int topSpeed;


    abstract static class Builder<T extends Builder<T>> {

        private final String name;
        private final Modality modality;
        private final int price;

        private int year = 0;
        private String model = "unknown";
        private String colour = "not set";
        private int horsepower = 0;
        private int topSpeed = 0;


        protected Builder (String name, Modality modality, int price) {
            this.name = name;
            this.modality = modality;
            this.price = price;
        }

        public T model(String model) {
            this.model = model;
            return self();
        }

        public T year(int year) {
            this.year = year;
            return self();
        }

        public T colour(String colour) {
            this.colour = colour;
            return self();
        }

        public T topSpeed(int topSpeed) {
            this.topSpeed = topSpeed;
            return self();
        }

        public T horsepower(int horsepower) {
            this.horsepower = horsepower;
            return self();
        }

        protected abstract T self();

        public abstract Vehicle build();
    }

    protected Vehicle(Builder<?> builder) {
        this.name = builder.name;
        this.modality = builder.modality;
        this.price = builder.price;
        this.year = builder.year;
        this.model = builder.model;
        this.colour = builder.colour;
        this.topSpeed = builder.topSpeed;
        this.horsepower = builder.horsepower;
        this.id = nextID.getAndIncrement();
    }


    public int getID() {
        return this.id;
    }

    public Modality getModality() {
        return this.modality;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getColour() { return colour; }

    public int getYear() { return year; }

    public String getModel() { return model; }

    public int getTopSpeed() { return topSpeed; }

    public int getHorsepower() { return horsepower; }

}
