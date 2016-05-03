import java.awt.*;
import javax.swing.*;
import java.io.*;

public class GameLogic
{
    public Mario s;
    public Coin c1;
    public Box b1;
    public Grumpy g1;
    
    public GameLogic() throws IOException
    {
        s = new Mario(0,0,1,10);
        c1 = new Coin(100,20);
        b1 = new Box(200,130);
        g1 = new Grumpy(100,100);
    }
}
