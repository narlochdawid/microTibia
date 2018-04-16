/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import static snowrider.Game.W;

/**
 *
 * @author jumbo
 */
public class SelectMenu {
    
    public Rectangle playB = new Rectangle(W/2-100, 175, 200, 50);
    public Rectangle helpB = new Rectangle(W/2-100, 225, 200, 50);
    public Rectangle quitB = new Rectangle(W/2-100, 275, 200, 50);
            
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial",Font.BOLD,50);
        Font fnt1 = new Font("arial",Font.PLAIN,15);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("Wybór postaci", W/2 - 100, 100);
        g.setColor(Color.ORANGE);
        g.setFont(fnt1);
        g2d.draw(playB);
        g.drawString("Golum", playB.x + 5, playB.y + 30);
        g.setColor(Color.GREEN);
        g2d.draw(helpB);
        g.drawString("Goblin", helpB.x + 5, helpB.y + 30);
        g.setColor(Color.RED);
        g2d.draw(quitB);
        g.drawString("Ognisty żywiołak", quitB.x + 5, quitB.y + 30);
    }
}
