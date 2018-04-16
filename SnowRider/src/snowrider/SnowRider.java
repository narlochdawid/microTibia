/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowrider;

/**
 *
 * @author jumbo
 */


public class SnowRider {
    /**
     * @param args the command line arguments
     */
    
    static int WIDTH = 42*32;
    static int HEIGH = 24*32;
    
    static String host = "192.168.56.101";
    //static String host = "10.42.0.1";
    
    public static void main(String[] args) {
        if(args.length>1) host = args[1];
        // TODO code application logic here
        System.out.println(SnowRider.host);
        new Window(WIDTH,HEIGH,"Snow Rider",new Game());
    }
    
}
