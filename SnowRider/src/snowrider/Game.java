/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

import snowrider.Buffers.BufferedImageLoader;
import snowrider.Buffers.SpriteSheet;
import snowrider.Objects.Tree;
import snowrider.Grounds.Snow;
import snowrider.Grounds.Bush;
import snowrider.Grounds.Ground;
import snowrider.Grounds.Brick;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import snowrider.Grounds.Sand;
import static snowrider.ID.BlueStone;
import static snowrider.ID.Sand;
import snowrider.Objects.Bag;
import snowrider.Objects.Barell;
import snowrider.Objects.BlueStone;
import snowrider.Objects.Chair;
import snowrider.Objects.Chest;
import snowrider.Objects.Fire;
import snowrider.Objects.Flower;
import snowrider.Objects.Knight;
import snowrider.Objects.PinkStone;
import snowrider.Objects.SnowTree;

/**
 *
 * @author jumbo
 */
public class Game extends Canvas implements Runnable {
    
    static byte keyCode = 0;
    static int shotted = 0;
    static float alfa;
    static int W = 42*32;
    static int H = 24*32;
    
    
    private Connect c;
    private boolean running = false;
    private Thread thread;
    Handler handler;
    private Menu menu;
    private SelectMenu smenu;
    private BufferedImage spriteSheet;
    private BufferedImage environment;
    private BufferedImage bg;
    private BufferedImage px;
    public Player p;
    public Player e1;
    public Player e2;
    public Player e3;
    public Player e4;
    static int postac = 1;
    static int chmp = 0;
    private int vec = 1;
    
    public static enum STATE{
        MENU,
        SELECT,
        GAME,
    };
    
    public static STATE State = STATE.MENU;
    
    public Game(){
        handler = new Handler();
        menu = new Menu();
        smenu = new SelectMenu();
        

    }
    
    public void init() throws FileNotFoundException, IOException{
        BufferedImageLoader loader = new BufferedImageLoader();

        char [][] grounds = new char[24][42];
        char [][] elems = new char[24][42];
        
        try {
            spriteSheet = loader.loadImage("src\\chmp.png");
            environment = loader.loadImage("src\\env.png");
            bg = loader.loadImage("src\\bg.png");
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        BufferedReader r=new BufferedReader(new FileReader("src\\mapa.txt"));
        int ch;
        int i=-1;
        int j=0;
        while((ch=r.read())!=-1){
            if(ch == 10)
                continue;
            i++;  
            grounds[j][i]=(char) ch;
            
            if(i==41 && j==24)
                break;            
            if(i==41){
                j++;
                i=-1;
            }
        }
        r=new BufferedReader(new FileReader("src\\elementy.txt"));
        //PrintWriter writer = new PrintWriter("pts.txt", "UTF-8");

        i=-1;
        j=0;
        while((ch=r.read())!=-1){
            if(ch == 10)
                continue;
            i++;  
            elems[j][i]=(char) ch;
            //if( ch == 'R' || ch == 'Z' || ch == 'K'|| ch == 'O'){
            //    writer.println(i+ " " + j);  
            //}
            //else if(ch == 'T' || ch == 'X' || ch == 'P' || ch == 'B'){
            //    writer.println((i+1)+ " " + (j+1));  
            //}
            if(i==41 && j==24)
                break;            
            if(i==41){
                j++;
                i=-1;
            }
        }
        //writer.close();
        
        for(i=0;i<24;i++)
            for(j=0;j<42;j++){
                if(grounds[i][j] == 'G')
                   handler.addObject(new Bush(j*32,i*32,ID.Bush,this)); 
                else if(grounds[i][j] == 'B')
                    handler.addObject(new Brick(j*32,i*32,ID.Brick,this)); 
                else if(grounds[i][j] == 'C')
                    handler.addObject(new Ground(j*32,i*32,ID.Ground,this)); 
                else if(grounds[i][j] == 'S')
                    handler.addObject(new Snow(j*32,i*32,ID.Snow,this)); 
                else if(grounds[i][j] == 'D')
                    handler.addObject(new Sand(j*32,i*32,ID.Sand,this));
            }
        
        for(i=0;i<24;i++)
            for(j=0;j<42;j++){
                if(elems[i][j] == 'T')
                   handler.addObject(new Tree((j-1)*32,(i-1)*32,ID.Tree,this)); 
                else if(elems[i][j] == 'R')
                    handler.addObject(new Knight(j*32,i*32,ID.Knight,this)); 
                else if(elems[i][j] == 'X')
                    handler.addObject(new SnowTree((j-1)*32,(i-1)*32,ID.SnowTree,this)); 
                else if(elems[i][j] == 'P')
                    handler.addObject(new PinkStone((j-1)*32,(i-1)*32,ID.PinkStone,this));
                else if(elems[i][j] == 'K')
                    handler.addObject(new Chest(j*32,i*32,ID.Chest,this)); 
                else if(elems[i][j] == 'B')
                    handler.addObject(new BlueStone((j-1)*32,(i-1)*32,ID.BlueStone,this));
                else if(elems[i][j] == 'Z')
                    handler.addObject(new Flower(j*32,i*32,ID.Flower,this));
                else if(elems[i][j] == 'O')
                    handler.addObject(new Fire(j*32,i*32,ID.Fire,this));
            }
        addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());
        
        
        
    }
    
    
    public synchronized void start(){
        
        if(running){
            return;
        }
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while(running){
            long now = System.nanoTime();
            delta +=(now - lastTime) / ns;
            lastTime = now;
            
            while(delta >= 1){
                tick(0);
                delta--;
            }
            if(running)
                render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer +=1000;
                System.out.println("FPS: " + frames);
                frames = 0;
                     
            }
        }
        stop();
    }
    
    private void tick(int speed){
        if(State == STATE.GAME){
            handler.tick(speed);
            if(p!=null)
                p.tick(0);
            if(e1!=null)
                e1.tick(0);
            if(e2!=null)
                e2.tick(0);
            if(e3!=null)
                e3.tick(0);
        }
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        if(State !=STATE.GAME)
            g.drawImage(bg, 0, 0, null);

        
        if(State == STATE.GAME){
            if(chmp !=0){
                c = new Connect(this);
                c.start();
                p = new Player(200, 200, ID.Player, this,postac,1,100);
                System.out.println(postac);
                chmp = 0;
            }
            handler.renderLower(g);
            p.render(g);
            if(e1!=null && e1.getHealth()==-1)
                e1=null;
            if(e2!=null && e2.getHealth() == -1)
                e2 = null;
            if(e3!=null && e3.getHealth() == -1)
                e3 = null;
            
            
            if(e1!=null)
                e1.render(g);
            if(e2!=null)
                e2.render(g);
            if(e3!=null)
                e3.render(g);
            handler.renderHigher(g);
            
            
            p.renderHP(g);
             if(e1!=null)
                e1.renderHP(g);
            if(e2!=null)
                e2.renderHP(g);
            if(e3!=null)
                e3.renderHP(g);
            
            
        }
        else if(State == STATE.MENU){
            menu.render(g);
        }
        else if(State == STATE.SELECT){
            smenu.render(g);
        }
        g.dispose();
        bs.show();
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_A){
            keyCode = 'a';

        }
        else if(key == KeyEvent.VK_D){
            keyCode = 'd';
        }
        else if(key == KeyEvent.VK_W){
            keyCode = 'w';
        }
        else if(key == KeyEvent.VK_S ){
            keyCode = 's';
        }
        else if(key == KeyEvent.VK_SPACE){
            keyCode = ' ';
        }
        else if(key == KeyEvent.VK_ESCAPE){
            c.end();
            State = STATE.MENU;
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_A){

        }
        else if(key == KeyEvent.VK_D){

        }
        else if(key == KeyEvent.VK_W){
;
        }
        else if(key == KeyEvent.VK_S){
        }
    }
    
    public static void mouseClicked(MouseEvent e,Game game){
          int mx = e.getX();
          int my = e.getY();
          if(State == Game.STATE.GAME){
              alfa = (float)Math.atan2(my - game.p.getY(),mx - game.p.getX());
              shotted = 1;       
          }
        
    }
    
    
    
    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
    public BufferedImage getEnvironmentSheet(){
        return environment;
    }
    
    public int getPostac(){
        return this.postac;
    }
    
    public void setPostac(int x){
        this.postac = x;
    }
    
}
