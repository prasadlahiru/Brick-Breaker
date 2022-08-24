/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;

import javax.swing.Timer;

/**
 *
 * @author Acer
 */
public class Game extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 200;
   
    private int delay = 8;
    private int playerX = 310;
    
    private int ballpositionX = getRandomNumber(50,550);
    private int ballpositionY = getRandomNumber(250,450);
    
    private int ballXDirection = -1;
    private int ballYDirection= -2;
    private Timer timer;
    
   
    private MapGen map;
   
    
    public Game(){
        map =new MapGen(10,20);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
        
    }
    
    public void paint(Graphics g){
        Color myColor1 = new Color(0,104,132);
        g.setColor(myColor1); //background
        g.fillRect(1,1, 692, 592);
        
        
        map.draw((Graphics2D)g); //drawing map
        
        Color myColor2 = new Color(76,146,177);
        g.setColor(myColor2);//borders
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Your Score : "+score,500,30);
        
        Color myColor3 = new Color(176,0,81);
        g.setColor(myColor3); //paddle
        g.fillRect(playerX, 550, 100,8);
        
        Color myColor4 = new Color(250,157,0);
        g.setColor(myColor4); // ball
        g.fillOval(ballpositionX, ballpositionY, 20,20);
        
        if(totalBricks <= 0){
            play=false;
            ballXDirection = 0;
            ballYDirection = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("You won!, Your Score: "+score ,230,300 );
            
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart " ,230,350 );
        }
        
        if(ballpositionY >570){
            play=false;
            ballXDirection = 0;
            ballYDirection = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Game Over!",270,300 );
            
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Your Score : "+ score ,270,330 );
            
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart " ,240,370 );
        }
        
        g.dispose();
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
             if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(playerX >=600){
                playerX = 600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_LEFT){
            if(playerX <10){
                playerX = 10;
            }else{
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            
            if(!play){
               play = true;
               ballpositionX=getRandomNumber(50,550);
               ballpositionY=getRandomNumber(250,450);
               ballXDirection=-1;
               ballYDirection=-2;
               playerX = 310;
               score = 0;
               totalBricks = 200;
               map = new MapGen(10,20);
               
               repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        
        if(play){
            if(new Rectangle(ballpositionX, ballpositionY,20,20).intersects(new Rectangle(playerX, 550,100,8))){
                ballYDirection = -ballYDirection;
            }
            A:for(int i=0; i<map.map.length;i++){
                for(int j=0; j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX = j*map.brickWidth+80;
                        int brickY = i*map.brickHeight+50;
                        int brickWidth = map.brickWidth;
                        int brickHeight =map.brickHeight;
                        
                        Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect = new Rectangle(ballpositionX, ballpositionY,20,20);
                        Rectangle brickRect = rect;
                        
                        if(ballRect.intersects(brickRect)){
                          map.setBrickValue(0,i,j);
                          totalBricks --;
                          score +=10;
                          
                          if(ballpositionX + 19 <= brickRect.x || ballpositionX+1 >=brickRect.x+brickRect.width){
                              ballXDirection = -ballXDirection;
                              
                          }else {
                              ballYDirection = -ballYDirection;
                          }
                          
                          break A;
                        }
                    }
                }
            }
            
            
            ballpositionX += ballXDirection;
            ballpositionY += ballYDirection;
            
            if(ballpositionX <0){
                ballXDirection = -ballXDirection;
            }
            if(ballpositionX >670){
                ballXDirection = -ballXDirection;
            }
            if(ballpositionY <0){
                ballYDirection = -ballYDirection;
            }
        
        }
        repaint();
    
    }
    public void moveRight(){
        play = true;
        playerX +=20;
                
    }
    public void moveLeft(){
        play = true;
        playerX -=20;
                
    }

    private int getRandomNumber(int min, int max) {
    int n =(int)(Math.random()*(max - min)+min);
    return n;
    }
        
        
    
}
