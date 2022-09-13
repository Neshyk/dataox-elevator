package org.dataox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Elevator{
    private final int maxPassengers;
    private Building building;
    private List<Passenger> passengers;
    private Floor currentFloor;
    private UpDown direction;

    public Elevator(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    private void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    void setBuilding(Building building) {
        this.building = building;
        this.currentFloor = building.getFloors().get(0);
        this.direction = UpDown.UP;
        this.passengers = new ArrayList<>();
    }
    public void move(){
        if (building==null){
            throw new RuntimeException("Elevator is not assigned to any building");
        }
        if(currentFloor== null){
            throw new RuntimeException("Elevator is not assigned to any floor");
        }
        if(direction==null){
            throw new RuntimeException("Elevator is not assigned to any direction");
        }
        DrawerUtil.draw(building);
        addPassengersByFloor();
        if(passengers.size() != 0){
            Floor nextFloor = getNextFloor();
            if(nextFloor != null){
                moveTo(nextFloor);
            }
        }
    }

    private Floor getNextFloor() {
        direction = getDirectionForMostPassengers(passengers);
        Floor nextFloor = passengers.stream().filter(p -> p.getDirection() == direction)
                .map(Passenger::getDestinationFloor)
                .max((f1, f2) -> direction == UpDown.DOWN ? f1.getFloorNumber() - f2.getFloorNumber() : f2.getFloorNumber() - f1.getFloorNumber())
                .orElse(null);
        return nextFloor;
    }

    private void moveTo(Floor floor){
        this.currentFloor = floor;
        removePassengers();
        if(passengers.size() == 0){
            addPassengersByFloor();
            move();
        }else{
            addPassengersByDirection();
        }
        Floor nextFloor = getNextFloor();
        DrawerUtil.draw(building);
        if((nextFloor!=null) && (nextFloor.getFloorNumber() < building.getFloors().size()-1) && (passengers.size()>0)){
            this.direction = currentFloor.getFloorNumber() < nextFloor.getFloorNumber() ? UpDown.UP : UpDown.DOWN;
            moveTo(nextFloor);
        }
    }

    private UpDown getDirectionForMostPassengers(List<Passenger> currentPassengers){
        return currentPassengers.stream()
                .collect(Collectors.groupingBy(Passenger::getDirection, Collectors.counting()))
                .entrySet().stream()
                .max((e1, e2) -> e1.getValue() > e2.getValue() ? 1 : -1)
                .map(e -> e.getKey())
                .orElse(null);
    }
    private void addPassengersByDirection(){
        int count = maxPassengers-passengers.size();
        List<Passenger> passengerList = addPassengers(count, this.direction);
    }
    private void addPassengersByFloor(){
        int count = maxPassengers;
        List<Passenger> passengerList = addPassengers(count, getDirectionForMostPassengers(currentFloor.getPassengers()));
    }
    private List<Passenger> addPassengers(int count, UpDown currentDirection){
        List<Passenger> passengersToAdd = currentFloor.getPassengers().stream()
                .filter(
                        passenger -> passenger.getDirection() == currentDirection)
                .toList();
        if (passengersToAdd.size() > count){
            passengersToAdd = passengersToAdd.subList(0, count-1);
        }
        passengers.addAll(passengersToAdd);
        currentFloor.removePassengers(passengersToAdd);

        return passengersToAdd;

    }
    private List<Passenger> removePassengers(){
        List<Passenger> passengersToRemove = passengers.stream()
                .filter(
                        passenger -> passenger.getDestinationFloor().getFloorNumber() == currentFloor.getFloorNumber())
                .toList();
        passengers.removeAll(passengersToRemove);
        return passengersToRemove;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public UpDown getDirection() {
        return direction;
    }
}
