/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static snowrider.Game.W;

/**
 *
 * @author jumbo
 */
public class MouseInput implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
    
        if(mx >=W/2-100 && mx <=W/2 && my>=175 && my <=225 && Game.State == Game.STATE.MENU){
            Game.State = Game.STATE.SELECT;
            return;
        }
        
        if(mx >=W/2-100 && mx <=W/2 + 100 && my>=175 && my <=225 && Game.State == Game.STATE.SELECT ){
            Game.postac = 1;
            Game.State = Game.STATE.GAME;
            Game.chmp = 1;
            return;
        }
        else if(mx >=W/2-100 && mx <=W/2 + 100 && my>=225 && my <=275 && Game.State == Game.STATE.SELECT){
            Game.postac = 4;
            Game.State = Game.STATE.GAME;
            Game.chmp = 1;
            return;
        }
        else if(mx >=W/2-100 && mx <=W/2 + 100 && my>=275 && my <=325 && Game.State == Game.STATE.SELECT){
            Game.postac = 7;
            Game.State = Game.STATE.GAME;
            Game.chmp = 1;
            return;
        }
        
        if(Game.State == Game.STATE.GAME){
          Game.mouseClicked(e,(Game) e.getSource());
      } 
         if(mx >=W/2-100 && mx <=W/2 && my>=275 && my <=325 && Game.State ==Game.STATE.MENU){
            System.exit(1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
