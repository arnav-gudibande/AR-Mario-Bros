import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameLogic
{
    public Mario s;
    public Coin c1;
    public Box b1;
    public Grumpy g1;
    public ArrayList<Coin> aC = new ArrayList<Coin>();
    
    public GameLogic() throws IOException
    {
        s = new Mario(0,0,1,10);
        createCoins();
        b1 = new Box(200,130);
        g1 = new Grumpy(100,100);
    }
    
    public void createCoins(){
        for(int i=25; i<550; i=i+30){
            aC.add(new Coin(i,10));
        }
    }
    
}
