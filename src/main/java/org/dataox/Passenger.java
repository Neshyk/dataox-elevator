package org.dataox;

public interface Passenger {
    Floor getCurrentFloor();
    Floor getDestinationFloor();
    UpDown getDirection();
}
