/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider.Buffers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jumbo
 */
public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage img) {
        this.image = img;
    }
    
    public BufferedImage grabImage(int col,int row,int width,int height){
        BufferedImage img = image.getSubimage(col*32 - 32, row*32 - 32, width, height);
        return img;
    }
    public BufferedImage grabGrave() throws IOException{
        BufferedImage img = ImageIO.read(new File("src/grave.png"));;
        return img;
    }
    
    
}
