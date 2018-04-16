/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider.Grounds;

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
public class Brick extends GameObject{

    private Game game;
    private BufferedImage brk;
    
    public Brick(int x, int y, ID id,Game game){
        super(x, y, id, game);
        this.game = game;
        this.x = x;
        this.y = y;
        SpriteSheet ss = new SpriteSheet(game.getEnvironmentSheet());
        brk = ss.grabImage(3,1, 32, 32);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(brk, x, y, null);
    }
    
    
}
