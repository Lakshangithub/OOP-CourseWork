package model;

public class Vehicle {
    String vehicleNo;
    String VehicleType;
    double maxWeight;
    int passengers;

    public Vehicle() {
    }

    public Vehicle(String vehicleNo, String vehicleType, double maxWeight, int passengers) {
        this.vehicleNo = vehicleNo;
        VehicleType = vehicleType;
        this.maxWeight = maxWeight;
        this.passengers = passengers;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", VehicleType='" + VehicleType + '\'' +
                ", maxWeight=" + maxWeight +
                ", passengers=" + passengers +
                '}';
    }
}
