import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class TicTac implements ActionListener
{
        JFrame frame=new JFrame(); 
        JPanel title_panel=new JPanel();
        JPanel button_panel=new JPanel();
        JLabel textField=new JLabel();
        JButton[][] button=new JButton[3][3];
        Random rand=new Random();
        boolean turn=true,win=false;
        int cnt=0;

        TicTac()
        {
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             frame.setLayout(new BorderLayout());
             frame.setVisible(true);
             frame.setSize(1000,1000);

             textField.setOpaque(true);
             textField.setText("TIC TAC TOE GAME");
             textField.setHorizontalAlignment(JLabel.CENTER);
             textField.setFont(new Font("arial",Font.BOLD,100));
             textField.setForeground(Color.RED);
             textField.setBackground(Color.BLACK);

             title_panel.setBounds(0,0,1000,100);
             title_panel.setBackground(Color.BLACK);
             title_panel.setLayout(new BorderLayout());
             title_panel.add(textField);
            
             
             button_panel.setBounds(0,100,900,900);
             button_panel.setBackground(Color.GREEN);
             button_panel.setLayout(new GridLayout(3,3));

               for(int i=0; i<3; i++)
               {
                  for(int j=0; j<3; j++)
                  {
                     button[i][j]=new JButton();
                     button[i][j].addActionListener(this);
                     button[i][j].setFocusable(false);
                     button[i][j].setFont(new Font("arial",Font.BOLD,60));
                     button_panel.add(button[i][j]);
                  }

               }
             

            

             frame.add(title_panel,BorderLayout.NORTH);
             frame.add(button_panel);
             firstTurn();
        }

        public void firstTurn()
        {
             if(rand.nextInt(2)==0)
             {
                 turn= true;
                 textField.setText("X's Turn");
             }
             else
             {  turn = false;
                textField.setText("O's Turn");
             }
        }



        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
           // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
          
           for(int i=0; i<3; i++)
           {
              for(int j=0; j<3; j++) 
              {
                  if(e.getSource()==button[i][j])
                  {
                       if(button[i][j].getText()=="")
                       {
                            cnt++;
                             if(turn)
                             {
                                 button[i][j].setText("X");
                                 textField.setText("O's turn");
                                 check(turn);
                                 turn=false;
                             }
                             else
                             { button[i][j].setText("O");
                                 textField.setText("X's turn");
                                 check(turn);
                                 turn=true;
                             }
                             if(cnt==9 && win==false)
                             {
                                          textField.setText("Draw Match");
                                              for(int a=0; a<3; a++)
                                               {
                                                    for(int b=0; b<3; b++)
                                                    {
                                                       button[a][b].setEnabled(false);
                                                    }
                                               }
                             }
                       }
                       break;
                  }
              }
           }
     
        }
        public void check(boolean player)
        {
              String play;
              if(player)
              {
                 play="X";
              }
              else
               play="O";


                // row check

                  for(int i=0; i<3; i++)
                  {
                        if(button[i][0].getText()==play && button[i][1].getText()==play && button[i][2].getText()==play)
                        {
                            for(int k=0; k<3; k++)
                             button[i][k].setBackground(Color.GREEN);

                             for(int a=0; a<3; a++)
                             {
                                  for(int b=0; b<3; b++)
                                  {
                                     button[a][b].setEnabled(false);
                                  }
                             }
                             textField.setText(play +" Wins");
                             win=true;
                             return ;
                        }
                  }

                  // col  check

                  for(int j=0; j<3; j++)
                  {
                    if(button[0][j].getText()==play && button[1][j].getText()==play && button[2][j].getText()==play)
                    {
                        for(int k=0; k<3; k++)
                         button[k][j].setBackground(Color.GREEN);
                         for(int a=0; a<3; a++)
                         {
                              for(int b=0; b<3; b++)
                              {
                                 button[a][b].setEnabled(false);
                              }
                         }
                         textField.setText(play +" Wins");
                         win=true;
                         return ;
                    }
                  }

                  // diagonal check
                  if(button[0][0].getText()==play && button[1][1].getText()==play && button[2][2].getText()==play)
                    {
                        for(int k=0; k<3; k++)
                         button[k][k].setBackground(Color.GREEN);
                         for(int a=0; a<3; a++)
                         {
                              for(int b=0; b<3; b++)
                              {
                                 button[a][b].setEnabled(false);
                              }
                         }
                         textField.setText(play +" Wins");
                         win=true;
                         return ;
                    }

                    if(button[0][2].getText()==play && button[1][1].getText()==play && button[2][0].getText()==play)
                    {
                       
                         button[0][2].setBackground(Color.GREEN);
                         button[1][1].setBackground(Color.GREEN);
                         button[2][0].setBackground(Color.GREEN);
                         for(int a=0; a<3; a++)
                         {
                              for(int b=0; b<3; b++)
                              {
                                 button[a][b].setEnabled(false);
                              }
                         }
                         textField.setText(play +" Wins");
                         win=true;
                         return ;
                    }
                  
        }
}
