/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author jumbo
 */
public class KeyInput extends KeyAdapter {
    
    private Game game;

    public KeyInput(Game game) {
        this.game = game;
    }
    
    public void keyPressed(KeyEvent e){
       game.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e){
        game.keyReleased(e);
    }
    
}
