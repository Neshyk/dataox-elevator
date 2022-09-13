package org.dataox;

import java.util.List;

class Building{
    private final List<Floor> floors;
    private Elevator elevator;
    public Building(List<Floor> floors) {
        this.floors = floors;
        this.elevator = elevator;
    }
    public List<Floor> getFloors() {
        return floors;
    }

    public Elevator getElevator() {
        return elevator;
    }
    public void setElevator(Elevator elevator) {
        elevator.setBuilding(this);
        this.elevator = elevator;
    }

}