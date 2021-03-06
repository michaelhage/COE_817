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
public class LuxuryCarRental extends CarRental{

    private boolean isChauffer;
    
    public LuxuryCarRental(String name, String zip, int dayLength, boolean isChauffer) {
        super(name, zip, dayLength);
        this.isChauffer = isChauffer;
        this.setSize('L');
        this.setFee(79.99);
        
        if(isChauffer){
            this.setTotal(this.getFee() * this.getDayLength() + 200);
        }
        else{
            this.setTotal(this.getDayLength() * this.getFee());
        } 
    }

    public boolean isIsChauffer() {
        return isChauffer;
    }

    public void setIsChauffer(boolean isChauffer) {
        this.isChauffer = isChauffer;
    }

    @Override
    public String display() {
        return "name= " + this.getName() + ", zip= " + this.getZip() + 
                ", size= " + this.getSize() + ", fee= " + this.getFee() + 
                ", length of rental days= " + this.getDayLength() + 
                ", total= " + this.getTotal() + ", isChauffer=" + isChauffer;
    }   
}
