/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro2;

/**
 *
 * @author Michael Hage
 */
public class CarRental {
    
    private String name;
    private String zip;
    private char size;
    private double fee;
    private int dayLength;
    private double total;

    public CarRental(String name, String zip, int dayLength) {
        this.name = name;
        this.zip = zip;
        this.dayLength = dayLength;
    }
    
    public CarRental(String name, String zip, char size, int dayLength) {
        this.name = name;
        this.zip = zip;
        this.size = size;
        this.dayLength = dayLength;
        
        // If size contains an error, then the full size fee will be the default
        switch (size) {
            case 'E':
                fee = 29.99;
                break;
            case 'M':
                fee = 38.99;
                break;
            default:
                fee = 43.5;
                break;
        }
        
        total = fee * dayLength;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getDayLength() {
        return dayLength;
    }

    public void setDayLength(int dayLength) {
        this.dayLength = dayLength;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String display() {
        return "name= " + name + ", zip= " + zip + ", size= " + 
                size + ", fee= " + fee + ", length of rental days= " + dayLength + 
                ", total= " + total;
    }

    
}
