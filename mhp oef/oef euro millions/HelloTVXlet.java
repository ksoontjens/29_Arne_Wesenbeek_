package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {

        HScene scene;
        HTextButton button1;
        HTextButton button2;
        HTextButton button3;
        HTextButton button4;
        HTextButton button5;
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
     scene = HSceneFactory.getInstance().getDefaultHScene();
     
    }

    public void startXlet() {
        System.out.println("Started");
        HStaticText label = new HStaticText("Wat is 2+2", 25, 300, 675, 100);
        
        label.setBackgroundMode(HVisible.BACKGROUND_FILL);
        label.setBackground(new DVBColor(0,0, 255, 200));
        
        button1 = new HTextButton("4", 25, 410, 325, 50);
        button2 = new HTextButton("2", 375, 410, 325, 50);
        button3 = new HTextButton("een strikvraag", 25, 475, 325, 50);
        button4 = new HTextButton("8", 375, 475, 325, 50);
        button5 = new HTextButton("Hulplijn", 375, 10, 325, 50);
            
        scene.add(button1);
        scene.add(button2);
        scene.add(button3);
        scene.add(button4);
        scene.add(button5);
        
        button1.setFocusTraversal(button5, button3, null, button2);
        button2.setFocusTraversal(button5, button4, button1, null);
        button3.setFocusTraversal(button1, null, null, button4);
        button4.setFocusTraversal(button2, null, button3, null);
        button5.setFocusTraversal(null, button1, null, null);

        button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button1.setBackground(new DVBColor(0,0, 255, 255)); 
        button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button2.setBackground(new DVBColor(0,0, 255, 255)); 
        button3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button3.setBackground(new DVBColor(0,0, 255, 255)); 
        button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button4.setBackground(new DVBColor(0,0, 255, 255)); 
        button5.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button5.setBackground(new DVBColor(0,0, 255, 255)); 
       
        button1.addHActionListener(this);
        
        button2.setActionCommand("wrong2");
        button2.addHActionListener(this);
        
        button3.setActionCommand("wrong3");
        button3.addHActionListener(this);
        
        button4.setActionCommand("wrong4");
        button4.addHActionListener(this);
        
        button5.setActionCommand("Hulp");
        button5.addHActionListener(this);
        
        scene.add(label);
        scene.validate();
        scene.setVisible(true);
        button1.requestFocus(); //zet focus op button1
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println(event.getActionCommand());
        if (event.getActionCommand().equals("wrong2"))
        {
            button2.setBackground(Color.RED);
        }
        if (event.getActionCommand().equals("wrong3"))
        {
            button3.setBackground(Color.RED);
        }
        if (event.getActionCommand().equals("wrong4"))
        {
            button4.setBackground(Color.RED);
        }
        if (event.getActionCommand().equals("Hulp"))
        {
            scene.remove(button4);
            scene.remove(button2);
        }
        
        button1.setBackground(Color.GREEN);
        scene.repaint();
    }
}
