import java.awt.Component;

import javax.swing.*;

public class GameFrame 
{
       JFrame frame=new JFrame();
       
       GameFrame()
       {  
            frame.add(new GamePanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Snake");
             frame.setSize(600,600);
          //  frame.setResizable(false);
             frame.setVisible(true);
             frame.pack();
             frame.setLocationRelativeTo(null);
       }
}