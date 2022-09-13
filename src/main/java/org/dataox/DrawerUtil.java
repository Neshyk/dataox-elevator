package org.dataox;

public class DrawerUtil {
    public static <T extends Building> void draw(T building){
        Elevator elevator = building.getElevator();
        for (Floor floor : building.getFloors()) {
            System.out.print(floor.getFloorNumber() + "|");
            for(int i = 0; i<Main.MAX_PASSENGERS; i++){
                System.out.print("| ");
                try{
                    System.out.print(floor.getPassengers().get(i).getDestinationFloor().getFloorNumber());
                }catch (IndexOutOfBoundsException e){
                    System.out.print("_");
                }
                System.out.print(" |");
            }
            if(floor == elevator.getCurrentFloor()){
                System.out.print("|||-->|");
                System.out.print(elevator.getDirection()==UpDown.DOWN ? "^" : "v");
                for(int i = 0; i< elevator.getMaxPassengers(); i++){
                    System.out.print("| ");
                    try{
                        System.out.print(elevator.getPassengers().get(i).getDestinationFloor().getFloorNumber());
                    }catch (IndexOutOfBoundsException e){
                        System.out.print("_");
                    }
                    System.out.print(" |");
                }

            }
            System.out.println();

        }
        System.out.println("-------------------------------------------------------");
        System.out.println("-------------------------------------------------------");
    }
}
