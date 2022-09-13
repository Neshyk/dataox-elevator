package org.dataox;

class SimplePassenger implements Passenger {
    private Floor currentFloor;
    private Floor destinationFloor;
    private UpDown direction;

    public SimplePassenger(Floor currentFloor, Floor destinationFloor) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.direction = currentFloor.getFloorNumber() < destinationFloor.getFloorNumber() ? UpDown.UP : UpDown.DOWN;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Floor getDestinationFloor() {
        return destinationFloor;
    }

    public UpDown getDirection() {
        return direction;
    }
}