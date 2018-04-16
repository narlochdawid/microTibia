/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

import java.awt.Graphics;

/**
 *
 * @author jumbo
 */
public abstract class GameObject {
    
    protected int x, y;
    protected ID id;
    protected int VX, VY;
    protected int is_free = 1;
    
    public GameObject(int x, int y, ID id, Game game){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public void tick(int speed) {
        y = y-speed;
    }
    public abstract void render(Graphics g);
    
    public void setX(int x){
        this.x= x;
    }
    public void setY(int y){
        this.y= y;
    }
    public void setVX(int VX){
        this.VX= VX;
    }
    public void setVY(int VY){
        this.VY= VY;
    }
    public void setId(ID id){
        this.id= id;
    }
    
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getVX(){
        return this.VX;
    }
    public int getVY(){
        return this.VY;
    }
    public ID getId(){
        return this.id;
    }
    public int getIsFree(){
        return this.is_free;
    }
    public void setIsFree(int x){
        this.is_free = x;
        
    }
}
