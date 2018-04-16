/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

import snowrider.Buffers.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static snowrider.Game.H;
import static snowrider.Game.W;

/**
 *
 * @author jumbo
 */
public class Player extends GameObject{
    
    private BufferedImage player;
    private int chmp = 1;
    private int vec = 1;
    private int selected = 0;
    private int tickC = 0;
    private int playerId;
    private int health;
    
    private int prevX;
    private int prevY;
    
    
    
    private int velX = 0;
    private int velY = 0;
    
    private Game game;
    private int runs = 0;

    public Player(int x, int y, ID id,Game game,int ch,int pid,int hp){
        super(x, y, id,game);
        this.game = game;
        this.vec = ch;
        this.selected = ch;
        this.playerId = pid;
        this.prevX = x;
        this.prevY = y;
        this.health = hp;
        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        player = ss.grabImage(vec, chmp, 32, 32);
    }
    
    @Override
    public void tick(int speed) {    
        
        if(prevX == x && prevY == y)
            runs = 0;
        else runs = 1;
        
        if(runs == 1){
            
            if(x  < prevX){
                this.setChmp(4);
                this.setVelX(-4);
            }
            else if(x >prevX){
                this.setChmp(3);
                this.setVelX(4);
            }
            
            if(y  < prevY){
                this.setChmp(2);
                this.setVelY(-4);
            }
            else if(y >prevY){
                this.setChmp(1);
                this.setVelY(4);
            }
            
            
            tickC++;
            tickC = tickC % 2;
            if(tickC == 0){
            SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
            player = ss.grabImage(vec, chmp, 32, 32);
            vec++;
            vec = (vec %3) + selected;
            
            
                if(x<=0)
                    x=0;
                else if(x>W-32)
                    x = W-32;
                if(y<=0)
                    y=0;
                else if(y>H-32)
                    y=H-32;
                
                this.prevX = this.x;
                this.prevY = this.y;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawString(Integer.toString(this.health), x, y-5);
        g.drawImage(player, x, y, null);
        
    }
    
    public int getChmp(){
        return this.chmp;
    }
    public int getVec(){
        return this.vec;
    }
    public int getRuns(){
        return this.runs;
    }
    public void setRuns(int x){
        this.runs = x;
    }
    public void setChmp(int x){
        this.chmp = x;
    }
    public void setVec(int x){
        this.vec = x;
    }
    public int getVelX(){
        return this.velX;
    }
    public int getVelY(){
        return this.velY;
    }
    public void setVelX(int x){
        this.velX = x;
    }
    public void setVelY(int x){
        this.velY = x;
    }
    public int getPlayerId(){
        return this.playerId;
    }
    public void setPlayerId(int x){
        this.playerId = x;
    }
    
    public int getPrevX(){
        return this.prevX;
    }
    public void setPrevX(int x){
        this.prevX = x;
    }
    
    public int getPrevY(){
        return this.prevY;
    }
    public void setPrevY(int y){
        this.prevY = y;
    }
    
     public int getHealth(){
        return this.health;
    }
    public void setHealth(int y){
        this.health = y;
    }
    
}
