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
public class SnowTree extends GameObject{

    private Game game;
    private BufferedImage Stree;
    
    public SnowTree(int x, int y, ID id,Game game){
        super(x, y, id, game);
        this.game = game;
        SpriteSheet ss = new SpriteSheet(game.getEnvironmentSheet());
        Stree = ss.grabImage(2,4, 64, 64);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Stree, x, y, null);
    }
    
}
