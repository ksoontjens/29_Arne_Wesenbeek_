package hellotvxlet;

import java.awt.Graphics;
import java.awt.Image;
import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;
import java.awt.MediaTracker;

public class MijnComponent extends HComponent{

    int w;
    int h;
    int t;
    int tw ;
    int duzend;
    String score;
    Image simon= this.getToolkit().getImage("simon.png"); 
    Image light= this.getToolkit().getImage("light.png"); 
    //zit in map C:\Program Files\TechnoTrend\TT-MHP-Browser\fileio\DSMCC\0.0.3
    
    
    public MijnComponent(int x, int y, int width, int height)
    {
    score = "0";
    duzend = 1000;
    w = duzend;
    tw = duzend;
        
    this.setBounds(x, y, width, height); //instantieer de grootte en locatie 
    MediaTracker mt=new MediaTracker(this);
    mt.addImage(simon,0);
    mt.addImage(light,0);
    try{
                 mt.waitForAll();
           }  catch (InterruptedException ex){
           ex.printStackTrace();      
           }
    }
    public void paint(Graphics g)
    {
        
        g.setColor(new DVBColor(0,0,0, 255));//zwart       
        g.fillRect(0,0,1000,1000);//zwarte achtergrond
        g.drawImage(simon, 70,0, null);
        g.drawImage(light, w, h, null);
        //light coord 
        //            red:    230,-20
        //            yellow: 425,155
        //            green:  45,155
        //            blue:   230,350
        g.setColor(new DVBColor(255,255,255, 255));//wit 
        g.drawString(score, 325, 300);
        g.drawString("Press up to start",20,40);
        g.setColor(new DVBColor(0,0,0, 255));
        g.fillRect(tw, 10, 200, 40);
        
        
        
    }
        public void moveleft()
    {
    w = 45;
    h = 155;
    reset();   
    }
    
    public void moveright()
    {
    w = 425;
    h = 155;
    reset();
    }
    
    public void moveup()
    {
    w = 230;
    h = -20;
    reset(); 
    }
    
    public void movedown()
    {
    w = 230;
    h = 350;
    reset();
    }
    public void leave(){
        w = duzend;
        reset();
    }
    public void score(int x){
        score = x + 0 + "";
    }
    public void scReset(){
        score = 0 + "";
        tw = duzend;
        reset();
    }
    public void reset(){this.repaint();}
}
