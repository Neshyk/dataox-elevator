package org.dataox;

import java.util.List;

public interface Floor {
    int getFloorNumber();
    void addPassenger(Passenger passenger);
    void removePassenger(Passenger passenger);
    List<Passenger> getPassengers();
    void removePassengers(List<Passenger> passengers);


}
