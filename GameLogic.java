import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class GameLogic
{
    public Mario s;
    public Coin c1;
    public Grumpy g1;
    public static ArrayList<Coin> aC = new ArrayList<Coin>();
    public static ArrayList<Grumpy> gC = new ArrayList<Grumpy>();
    public static int score = 0;
    public boolean game = true;
    
    public GameLogic() throws IOException
    {
        s = new Mario(0,0,1,10);
        createCoins();
        createGrumpy();
        //g1 = new Grumpy(100,100);
    }
    
    public void createCoins()
    {
        for(int i=25; i<560; i=i+25){
            aC.add(new Coin(i,10));
        }
    }
    
    public void createGrumpy(){
        for(int i=50; i<450; i=i+50){
            gC.add(new Grumpy(i,20));
        }
    }
    
    public boolean gameStatus(){
        return true;
    }

}
