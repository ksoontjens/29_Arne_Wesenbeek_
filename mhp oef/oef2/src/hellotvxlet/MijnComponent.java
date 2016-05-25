
package hellotvxlet;

import java.awt.Graphics;
import java.awt.Image;
import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;

public class MijnComponent extends HComponent{

    int setWidth;
    int setHeight;
    Image heart = this.getToolkit().getImage("heartgreen.png"); 
    Image Undyne = this.getToolkit().getImage("Kopie_van_Undyne.gif");
    
    public MijnComponent(int x, int y, int width, int height)
    {
    this.setBounds(x, y, width, height); //instantieer de grootte en locatie
    setWidth = width;
    setHeight = height;    
    }
    public void paint(Graphics g)
    {
        g.setColor(new DVBColor(0, 0, 179, 179));
        g.fillRoundRect(10, 10, setWidth, setHeight, 15, 15);
        //g.fillRoundRect(10, 10, setWidth, setHeight, 15, 15);
        //g.setColor(new DVBColor(0,0,0,150));
        //g.fillRoundRect(10, 10, setWidth, setHeight,15,15);
        g.setColor(new DVBColor(0,0, 63, 179)); //donkerblauwe achtergrond transp
        g.fillRoundRect(15, 15, setWidth-10, setHeight-10, 15, 15); //maakt rechthoek
        
        g.setColor(new DVBColor(255,255,0,255));
        //g.fillRoundRect(10, 10, setWidth, setHeight, 15, 15);
        g.drawString("Text", 50, 50);
        
      
        
         
    }
}
