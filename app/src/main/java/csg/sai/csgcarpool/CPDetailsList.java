package csg.sai.csgcarpool;

public class CPDetailsList {

    public String name;
    public String destination;
    public String seats;
    public String phoneNumber;

    public CPDetailsList()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CPDetailsList (String sname, String sdestination, String sseats, String sphoneno){
        this.name = sname;
        this.destination = sdestination;
        this.seats = sseats;
        this.phoneNumber = sphoneno;
    }
}
