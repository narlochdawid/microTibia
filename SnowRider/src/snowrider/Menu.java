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
public class Menu {
    
    public Rectangle playB = new Rectangle(W/2-100, 175, 100, 50);
    public Rectangle helpB = new Rectangle(W/2-100, 225, 100, 50);
    public Rectangle quitB = new Rectangle(W/2-100, 275, 100, 50);
            
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial",Font.BOLD,50);
        Font fnt1 = new Font("arial",Font.PLAIN,15);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("Menu", W/2 - 100, 100);
        g.setFont(fnt1);
        g2d.draw(playB);
        g.drawString("Nowa gra", playB.x + 5, playB.y + 30);
        g2d.draw(helpB);
        g.drawString("Pomoc", helpB.x + 5, helpB.y + 30);
        g2d.draw(quitB);
        g.drawString("Wyjście", quitB.x + 5, quitB.y + 30);
    }
}
