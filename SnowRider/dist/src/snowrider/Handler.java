/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

import java.awt.Graphics;
import static java.lang.Math.abs;
import java.util.LinkedList;
import static snowrider.Game.H;
import static snowrider.Game.W;

/**
 *
 * @author jumbo
 */
public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    LinkedList<GameObject> Hobject = new LinkedList<GameObject>();
    LinkedList<GameObject> Bullets = new LinkedList<GameObject>();
    LinkedList<GameObject> Pickable = new LinkedList<GameObject>();
    
    public void tick(int speed){
        for(int i =0; i <object.size(); i++){
            GameObject tempObj = object.get(i);
        
            tempObj.tick(speed);
        }
        
        for(int i =0; i <Bullets.size(); i++){
            Bullet tempObj = (Bullet) Bullets.get(i);
            tempObj.tick(speed);
        }
        
        for(int i =0; i <Hobject.size(); i++){
        GameObject tempObj = Hobject.get(i);
        
        tempObj.tick(speed);
        }
        
    }
    
    public void renderLower(Graphics g){
        for(int i =0; i <object.size(); i++){
        GameObject tempObj = object.get(i);
        
        tempObj.render(g);
        }
        
        for(int i =0; i <Pickable.size(); i++){
        GameObject tempObj = Pickable.get(i);     
        tempObj.render(g);
        }
        
        for(int i =0; i <Bullets.size(); i++){
        Bullet tempObj = (Bullet) Bullets.get(i);     
        tempObj.render(g);
        }
        
    }
    
    public void renderHigher(Graphics g){
        for(int i =0; i <Hobject.size(); i++){
        GameObject tempObj = Hobject.get(i);
        
        tempObj.render(g);
        }
        
    }
    
     public void addPickable(GameObject object){
        
            this.Pickable.add(object);
    }
    
    public void removePickable(GameObject object){
            this.Pickable.remove(object);
    }
   
    public void removeAllPickable(){
        for(int i =0; i <Pickable.size(); i++){
            GameObject tempObj = Pickable.get(i);
            this.Pickable.remove(tempObj);
        }
    }
    
    
    public void addObject(GameObject object){
        
        if(object.getId() == ID.Bullet)
            this.Bullets.add(object);
        else if(object.getId() == ID.Brick || object.getId() == ID.Sand || object.getId() == ID.Snow || object.getId() == ID.Ground || object.getId() == ID.Bush)
            this.object.add(object);
        else //if (object.getId() == ID.SnowTree || object.getId() == ID.Tree || object.getId() == ID.PinkStone || object.getId() == ID.BlueStone)
            this.Hobject.add(object);
    }
    
    public void removeObject(GameObject object){
        if(object.getId() == ID.Bullet)
            this.Bullets.remove(object);
        else if(object.getId() != ID.SnowTree && object.getId() != ID.Tree && object.getId() != ID.PinkStone && object.getId() != ID.BlueStone)
            this.object.remove(object);
        else if (object.getId() == ID.SnowTree || object.getId() == ID.Tree || object.getId() != ID.PinkStone || object.getId() != ID.BlueStone)
            this.Hobject.remove(object);
    }
    
    
    public int checkWay(int x,int y,int veX,int veY){
        GameObject temp;
        int xt;
        int yt;
        for(int i=0;i<this.object.size();i++){
            temp = this.object.get(i);
            xt = temp.getX();
            yt = temp.getY();
            int a=xt/32;
            if(((x + veX)+15) >=(xt) && (x + veX-15)<=xt && ((y + veY)+15) >=yt && (y + veY-15)<=yt )
                if(temp.getIsFree() == 0)
                    return 0;
        }
        return 1;
    }
    
}
