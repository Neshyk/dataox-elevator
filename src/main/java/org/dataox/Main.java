package org.dataox;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Building building;

    public static final int MAX_PASSENGERS = 6;
    public static final int MIN_PASSENGERS = 3;

    public static final int MAX_FLOORS = 10;
    public static final int MIN_FLOORS = 6;

    public static void main(String[] args) {

        List<Floor> floors = new ArrayList<>();
        for(int i=1; i<getRandomNumber(MIN_FLOORS,MAX_FLOORS); i++){
            floors.add(new SimpleFloor(i));
        }
        floors.forEach(floor -> {
            for(int i=1; i<getRandomNumber(MIN_PASSENGERS,MAX_PASSENGERS); i++){
                Passenger passenger = new SimplePassenger(floor, floors.get(getRandomNumber(floor.getFloorNumber()-1,floors.size()-1)));
                floor.addPassenger(passenger);
                }
            });

        Elevator elevator = new Elevator( 5);
        Building building = new Building(floors);
        building.setElevator(elevator);

        elevator.move();

    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}






