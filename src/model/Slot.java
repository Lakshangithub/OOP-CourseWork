package model;

public class Slot {
    String vehicleType;
    String slot;
    String status;

    public Slot() {
    }

    public Slot(String vehicleType, String slot, String status) {
        this.vehicleType = vehicleType;
        this.slot = slot;
        this.status = status;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "vehicleType='" + vehicleType + '\'' +
                ", slot='" + slot + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
