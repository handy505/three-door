/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threedoor;
import java.util.ArrayList;
import java.util.Random;


class Conestant{
    private int myguess;
    public int sayMy1stGuess(){ 
        Random ran = new Random();
        myguess = ran.nextInt(3);
        return myguess;
    }
    
    private int leftover;
    public void getLeftover(int number){ leftover = number; }
    
    public int sayMy2ndOriginalGuess(){ return myguess; }
    public int sayMy2ndChangeGuess(){ return leftover; }
    
    
    private int originStrategyWin;
    public void increaseOriginalStrategyWin(){ originStrategyWin++; }
    public int getOriginalStrategyWin(){ return originStrategyWin; }
    
    
    private int changeStrategyWin;
    public void increaseChangeStrategyWin(){ changeStrategyWin++; }
    public int getChangeStrategyWin(){ return changeStrategyWin; }
    
}


class Host{
    private int opendoor;
    private int leftoverdoor;
    
    public int excludeSomething(ArrayList<Boolean> doors, int firstGuess){
        for(int i=0; i<3; i++){
            if( i != firstGuess ){
                if( doors.get(i) == false ){
                    opendoor = i;
                }
            }
        }
        
        for(int i=0; i<3; i++){
            if((i != firstGuess) && (i != opendoor)){
                leftoverdoor = i;
            }
        }
        return leftoverdoor;
    }
}

/**
 *
 * @author handy
 */
public class ThreeDoor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Conestant c = new Conestant();
        Host h = new Host();

        int gameloop;
        for(gameloop=0; gameloop<10000; gameloop++){
         
            ArrayList<Boolean> doors = new ArrayList<Boolean>(3);
            for(int i=0; i<3; i++){
                doors.add(false);
            }
            //System.out.println(doors); // debug

            Random ran = new Random();
            int carNumber = ran.nextInt(3);
            doors.set(carNumber, true);
            System.out.println(carNumber + ", " + doors); // debug



            int firstGuess = c.sayMy1stGuess();
            int leftover = h.excludeSomething(doors, firstGuess);

            c.getLeftover(leftover);

            int secondeGuess1 = c.sayMy2ndOriginalGuess();
            int secondeGuess2 = c.sayMy2ndChangeGuess();

            if(secondeGuess1 == carNumber){
                c.increaseOriginalStrategyWin();
            }

            if(secondeGuess2 == carNumber){
                c.increaseChangeStrategyWin();
            }
         
        }
        
        System.out.println("gameloop: " + gameloop + " originWin: " + c.getOriginalStrategyWin() + " changeWin: " + c.getChangeStrategyWin());

    }
    
}
