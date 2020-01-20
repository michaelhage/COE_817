/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro2;

import java.util.ArrayList;
/**
 *
 * @author Michael Hage
 */
public class UseCarRental {
    
    public static void main(String[] args){
        ArrayList<CarRental> carArr = new ArrayList<CarRental>();
        
        
        carArr.add(new LuxuryCarRental("Michael","M1R2I4",4,true));
        carArr.add(new LuxuryCarRental("Bichael","M1R2I4",4,false));
        carArr.add(new CarRental("Cameron","M3F4R9",'E',2));
        carArr.add(new CarRental("Angelo","L6PQA1",'M',10));
        carArr.add(new CarRental("Angelo","M8F9V9",'Z',1));
        
        for(int i = 0;i < carArr.size();i++){
            carArr.get(i).display();
        }
    }
}
