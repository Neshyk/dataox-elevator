package org.dataox;

import java.util.ArrayList;
import java.util.List;

class SimpleFloor implements Floor{
    List<Passenger> passengers;
    private final int floorNumber;

    public SimpleFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.passengers = new ArrayList<>();
    }

    public List<Passenger>  getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger>  passengers) {
        this.passengers = passengers;
    }
    public int getFloorNumber() {
        return floorNumber;
    }

    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
    }
    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
    }
   public void removePassengers(List<Passenger> passengers){
        this.passengers.removeAll(passengers);
    }

}
