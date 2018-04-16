/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

import snowrider.Buffers.SpriteSheet;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static snowrider.Game.alfa;

/**
 *
 * @author jumbo
 */
public class Bullet extends GameObject{

    private Game game;
    private BufferedImage B;
    private int xT;
    private int yT;
    private int xS;
    private int yS;
    private int xrat;
    private int yrat;
    private double dist;
    private double r = 0;
    private int bullet;
    private double dst = 0;
    private int max_dst;
    private int bID;
    
    private int bulletSrc;
    private int dmg;
    private int isAlive;
    
    private double x;
    private double y;
    
    float alfa;

    
    public Bullet(int x, int y, ID id,Game game,int b,int bulletID,int isalive,float alfa){
        super(x, y, id, game);
        this.x = x;
        this.y = y;
        this.game = game;
        this.bullet = b;
        this.isAlive = isalive;
        this.bID = bulletID;
        this.alfa = alfa;
        if(b == 1){
            dmg = 170;
            max_dst = 300;
        }
        else if(b == 2){
            dmg = 140;
            max_dst = 550;
        }
        else if(b == 3){
            dmg = 150;
            max_dst = 450;
        }
        SpriteSheet ss = new SpriteSheet(game.getEnvironmentSheet());
        B = ss.grabImage(5,bullet, 32, 32);
    }
    
    @Override
    public void tick(int speed) {     
        if(r == 0){
            AffineTransform tx = new AffineTransform();
            tx.rotate(this.alfa, B.getWidth() / 2, B.getHeight() / 2);

            AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
            B = op.filter(B, null);
            
            
            xrat = xS - xT;
            yrat = yS - yT;
            dist = sqrt(xrat*xrat + yrat*yrat);
            r = 1;
           
        }
        if(this.isAlive == 0 )
            game.handler.removeObject(this);
        
    }
    
    public void Target(int x, int y,int startx,int starty){
        this.xT = x;
        this.yT = y;
        this.xS = startx;
        this.yS = starty;
    }
    

    @Override
    public void render(Graphics g) {

        g.drawImage(B, (int)x, (int)y, null);

    }
    
    public double getDst(){
        return this.dst;
    }
    
    public void setDst(double d){
        this.dst = d;
    }
    
    public int getMaxDst(){
        return this.max_dst;
    }
    
    public void setMaxDst(int d){
        this.max_dst = d;
    }
    
    public int getDmg(){
        return this.dmg;
    }
    
    public void setDmg(int d){
        this.dmg = d;
    }
    
    public int getSrc(){
        return this.bulletSrc;
    }
    
    public void setSrc(int d){
        this.bulletSrc = d;
    }
    
    public int getBId(){
        return this.bID;
    }
    
    public void setBId(int d){
        this.bID = d;
    }
    
    @Override
    public int getX(){
        return (int) this.x;
    }
    @Override
    public int getY(){
        return (int) this.y;
    }
    
    @Override
    public void setX(int x){
        this.x = x;
    }
    @Override
    public void setY(int y){
        this.y = y;
    }
    
    
    public void setType(int x){
        this.isAlive = x;
    }

    
}


