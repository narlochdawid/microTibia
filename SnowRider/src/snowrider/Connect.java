/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static snowrider.Game.keyCode;
import snowrider.Objects.Bag;

/**
 *
 * @author jumbo
 */
public class Connect extends Thread {
    
    private Socket s;
    
    private Game g;
    
    private InputStream is;
    private OutputStream os;
    
    private int con = 0;
    private byte buffer[] = new byte[2047];
    Connect(Game game){
        this.g = game;
    }
    // Serwer/
    // make dontsendmap
    //cd build/
    //./gameserver-debug
    public void end(){
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void 
        run(){
        try {
            s = new Socket(SnowRider.host,9528);
            
            os = s.getOutputStream();
            is = s.getInputStream();
            
            int len = is.read(buffer);
            if(len > 0){
                if(buffer[0] == 0x7){
                    //System.out.println(buffer);
                }
                else if(buffer[0] == 0x15){
                    System.err.println("Brak miejsca na serwerze");
                    s.close();
                    return;
                }
            }
            if(con == 0){
                buffer[0] = 0x11;
                buffer[1] = 0x09;
                byte chmp = 0x02;
                switch (g.getPostac()){
                    case 1:
                        chmp = 0x03;
                        break;
                    case 4:
                        chmp = 0x02;
                        break;
                    case 7:
                        chmp = 0x01;
                        break;
                }
                buffer[2] = chmp;
                buffer[3] = 0x00;
                try {
                    os.write(buffer,0,4);
                } catch (IOException ex) {
                    Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
                }
                con = 1;
            }
            
             len = is.read(buffer);
            
            
            
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        while(true){

            int len = 0;
            int i=0;
            
            buffer[0] = 0x11;
            if(Game.shotted == 0){
            buffer[1] = 0x3;
            buffer[2] = keyCode;
            buffer[3] = 0;
            }
            //0x0a podnoszenie
            else{
                buffer[1] = 0x8;
                ByteBuffer fb = ByteBuffer.allocate(4);
                fb.order(ByteOrder.LITTLE_ENDIAN);
                fb.putFloat(Game.alfa);
                
                fb.rewind();
                
                byte xxx[]=new byte[4];
                fb.get(xxx, 0, 4);
                Game.shotted = 0;
                System.arraycopy(xxx, 0, buffer, 2, 4);
            }
            
            try {
                os.write(buffer, 0, 6);
                len = is.read(buffer);
                
                
                if(len >0){
                    int x,y,d,hp;
                    x = buffer[0] + buffer[4]*32;
                    y = buffer[1] + buffer[5]*32;
                    d = buffer[2];
                    hp = buffer[3];//HPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP GRACZA 1
                    if(g.p != null){
                        keyCode = 0;
                        g.p.setX(x);
                        g.p.setY(y);
                        g.p.setChmp(d);
                        g.p.setHealth(hp);
                    }
                    
                    while(i < len){
                        if(buffer[4+i] == 0x50){
                            int pId = buffer[5+i];
                            int eX1 = buffer[6+i]*32 + buffer[9+i];
                            int eY1 = buffer[7+i]*32 + buffer[10+i];
                            int eD1 = buffer[8+i];// 11 wyglad 12 hp
                            int look = buffer[11 + i];
                            int hp1 = buffer[12 + i];//USTAWIC HPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
                            if(look == 2)
                                look = 4;
                            else if(look == 3)
                                look = 1;
                            else if(look == 1)
                                look = 7;
                            if(g.e1==null )//&& g.e2==null && g.e3==null)
                                g.e1 = new Player(eX1, eY1, ID.Player, g, look, pId,hp1);
                            else if((g.e1!=null && g.e1.getPlayerId()!=pId) && g.e2==null && g.e3==null)
                                 g.e2 = new Player(eX1, eY1, ID.Player, g, look, pId,hp1);
                            else if((g.e1!=null && g.e1.getPlayerId()!=pId) && (g.e2!=null && g.e2.getPlayerId()!=pId) && g.e3==null)
                                 g.e3 = new Player(eX1, eY1, ID.Player, g, look, pId,hp1);
                           if(g.e1!=null && pId == g.e1.getPlayerId()){
                                g.e1.setX(eX1);
                                g.e1.setY(eY1);
                                g.e1.setChmp(eD1);
                                g.e1.setHealth(hp1);
                            }
                            if(g.e2!=null && pId == g.e2.getPlayerId()){
                                g.e2.setX(eX1);
                                g.e2.setY(eY1);
                                g.e2.setChmp(eD1);
                                g.e2.setHealth(hp1);
                            }
                            if(g.e3!=null && pId == g.e3.getPlayerId()){
                                g.e3.setX(eX1);
                                g.e3.setY(eY1);
                                g.e3.setChmp(eD1);
                                g.e3.setHealth(hp1);
                            }
                            
                            i += 9;
                        }
                        
                        else if(buffer[4+i] == 0x40){
                            byte bId = buffer[5+i];
                            byte type = buffer[6+i];
                            byte a[] = new byte[12];
                            byte alfab[] = new byte[4];
                            System.arraycopy(buffer,7+i,alfab,0,4);
                            ByteBuffer balfa = ByteBuffer.wrap(alfab);
                            balfa.order(ByteOrder.LITTLE_ENDIAN);
                            balfa.rewind();
                            float alfa = balfa.getFloat();
                            System.arraycopy(buffer,11+i,alfab,0,4);
                            balfa = ByteBuffer.wrap(alfab);
                            balfa.order(ByteOrder.LITTLE_ENDIAN);
                            balfa.rewind();
                            float xB = balfa.getFloat();
                            System.arraycopy(buffer,15+i,alfab,0,4);
                            balfa = ByteBuffer.wrap(alfab);
                            balfa.order(ByteOrder.LITTLE_ENDIAN);
                            balfa.rewind();
                            float yB = balfa.getFloat();
                            if(type == 3)
                                type = 1;
                            else if(type == 1)
                                type = 3;
                            
                            for(int j =0;j<64;j++){
                                if(g.handler.Bullets.size()>j){
                                Bullet b = (Bullet) g.handler.Bullets.get(j);
                                if(b.getBId() == bId){
                                    b.setType(type);
                                    b.setX((int)(xB*32f));
                                    b.setY((int)(yB*32f));
                                    break;
                                }
                                else if(j==g.handler.Bullets.size()-1 && type!=0){
                                    g.handler.addObject(new Bullet((int) (xB*32f),(int)(yB*32f),ID.Bullet,g,type,bId,type,alfa));
                                }
                            }
                                else if(g.handler.Bullets.isEmpty() && type!=0){
                                    g.handler.addObject(new Bullet((int) (xB*32f),(int)(yB*32f),ID.Bullet,g,type,bId,type,alfa));
                                }
                            }
                            
                            
                            i += 15;
                        }
                        
                        else if(buffer[4+i] == 0x60){
                            g.handler.removeAllPickable();
                            for(int a=0;a<24;a++)
                                for(int b=0;b<42;b++){
                                    if(buffer[5+i]==(byte) 0xfe){
                                        g.handler.Pickable.add(new Bag(b*32, a*32, ID.Bag, g));
                                    }
                                    i++;
                                }
                        }
                        
                        
                        
                        else
                            i++;
                    
                    
                    
                    }
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try{
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
