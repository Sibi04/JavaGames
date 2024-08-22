import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
    
     final static int SCREEN_WIDTH=600;
     final static int SCREEN_HEIGHT=600;
     Random random;
     final static int UNIT_SIZE=25;
     final static int DELAY=75;
     final static int GAME_SIZE=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
     int x[]=new int[GAME_SIZE];
     int y[]=new int[GAME_SIZE];
     int appleX;
     int appleY;
     int bodyParts=6;
     int appleEaten=0;
     Timer timer;
     char direction='R';
     boolean running=false;

    GamePanel()
    {
          random=new Random();
          //panel=new JPanel();
          this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
          this.setBackground(Color.black);
          this.setFocusable(true);
          this.addKeyListener(new MyKeyAdapter());
          startGame();
    }

   

    public void startGame()
    { 
        newApple();
        running=true;
        timer=new Timer(DELAY,this);
        timer.start();   
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // paints background
         draw(g);
    }

    public void draw(Graphics g)
    {
         // Graphics2D g2d=()
          for(int i=0; i<(SCREEN_HEIGHT/UNIT_SIZE); i++)
          {
              g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
              g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
          }

          g.setColor(Color.red);
          
          g.drawOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);
          g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);

          //g.drawRect()
         
          for(int i=0; i<bodyParts; i++)
          {
                if(i==0) // head
                {
                    g.setColor(Color.blue);
                     g.drawRect(x[0],y[0],UNIT_SIZE,UNIT_SIZE);
                     g.fillRect(x[0],y[0],UNIT_SIZE,UNIT_SIZE);
                }
                else{
                    g.setColor(Color.blue);
                    g.drawRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                    g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                }
          }
    }
    public void move()
    {
         for(int i=bodyParts; i>0 ; i--)
         {
              x[i]=x[i-1];
              y[i]=y[i-1];
         }    
               switch(direction)
               {
                   case 'U':
                   y[0]=y[0]-UNIT_SIZE;
                   break;

                   case 'D':
                   y[0]=y[0]+UNIT_SIZE;
                   break;

                   case 'R':
                   x[0]=x[0]+UNIT_SIZE;
                   break;

                   case 'L':
                   x[0]=x[0]-UNIT_SIZE;
               }
         
           
    }
    public void newApple()
    {
           int x=random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
           int y=random.nextInt(SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
             
           appleX=x;
           appleY=y;
    }
     public void checkCollisions()
     {
           for(int i=1; i<bodyParts; i++)
           { 
              if(x[0]==x[i] && y[0]==y[i])
               running=false;
           }

          if(x[0] < 0 || x[0] > SCREEN_WIDTH)
          running=false;

          if(y[0] <0 || y[0] > SCREEN_HEIGHT)
          running=false;

          if(running==false)
           timer.stop();
          
     }
     public void checkApple()
     {
          if(x[0]==appleX && y[0]==appleY)
          {
             appleEaten++;
             bodyParts++;
             newApple();
          }
     }
   
     @Override
    public void actionPerformed(ActionEvent e) {
        
           if(running)
           {   
               move();
               checkApple();
               checkCollisions();
              
           }
           
           repaint();
    }

     public class MyKeyAdapter extends KeyAdapter{
           public void keyPressed(KeyEvent e)
           {
                switch(e.getKeyCode())
                {
                     case KeyEvent.VK_LEFT:
                     if(direction!='R')
                     direction='L';
                     break;
                     case KeyEvent.VK_RIGHT:
                     if(direction!='L')
                     direction='R';
                     break;
                     case KeyEvent.VK_UP:
                     if(direction!='D')
                     direction='U';
                     break;
                     case KeyEvent.VK_DOWN:
                     if(direction!='U')
                     direction='D';
                }
           }
     }


   
}
