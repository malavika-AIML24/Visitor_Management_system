package vms;

public class Visitor {
    private String name;
    private String phone;
    private String purpose;
    private String checkInTime;
    private String checkOutTime;

    public Visitor(String name, String phone, String purpose, String checkInTime, String checkOutTime) {
        this.name = name;
        this.phone = phone;
        this.purpose = purpose;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getPurpose() { return purpose; }
    public String getCheckInTime() { return checkInTime; }
    public String getCheckOutTime() { return checkOutTime; }
}
