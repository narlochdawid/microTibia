/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import snowrider.Game;
import snowrider.GameObject;
import snowrider.ID;
import snowrider.Buffers.SpriteSheet;

/**
 *
 * @author jumbo
 */
public class Flower extends GameObject{

    private Game game;
    private BufferedImage fl;
    
    public Flower(int x, int y, ID id,Game game){
        super(x, y, id, game);
        this.game = game;
        SpriteSheet ss = new SpriteSheet(game.getEnvironmentSheet());
        fl = ss.grabImage(7,2, 32, 32);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(fl, x, y, null);
    }
    
}
