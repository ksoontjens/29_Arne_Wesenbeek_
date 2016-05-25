package hellotvxlet;

import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.event.HRcEvent;
import org.havi.ui.HStaticText;

public class HelloTVXlet implements Xlet, UserEventListener
{
        HScene scene;
        MijnComponent mc=new MijnComponent(0,0,720,576);
        HStaticText label;
        
        int[] seq;
        int num;
        int numb;
        int numbe;
        int score;
        int randomNum;
        
        boolean pTurn = false;
        boolean nTurn = true;
        boolean flick = false;
        boolean gStart = false;

    public void destroyXlet(boolean unconditional) {
  
    }

    public void initXlet(XletContext ctx)
    {
        seq = new int[50];
        num = 0;
        numb = 0;
        numbe = 0;
        score = 1;
        
        scene=HSceneFactory.getInstance().getDefaultHScene();
        mc =new MijnComponent(0, 0, 720, 570);        
        label = new HStaticText("Score:", 275, 240, 150, 50);
        
        scene.add(label);
        scene.add(mc);
        scene.validate();
        scene.setVisible(true);
    }
    
    
    public void pauseXlet() {
    
    }

    public void startXlet() throws XletStateChangeException {
        
       UserEventRepository rep = new UserEventRepository("naam");
       rep.addAllArrowKeys(); //alle pijlti-jes toevoegen
       EventManager m=EventManager.getInstance(); 
       m.addUserEventListener(this,rep); // stuur event this voor alle knoppen in rep
       
       MijnTimerTask objMijnTimerTask = new MijnTimerTask(this);
       Timer t=new Timer();
       t.scheduleAtFixedRate(objMijnTimerTask, 0, 250); //start op 0 ms, elke 2.5 ms
       
       
    }

    public void userEventReceived(UserEvent e) {
       if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_UP)
        {
           //start het spel
           mc.tw = 0;
           mc.reset();
           gStart = true;
        }  
       if(pTurn == true){
           //leest player input als het zijn beurt is
        if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_LEFT)
        {
        mc.moveleft();
        check(3);
        } 
        else if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_RIGHT)
        {
        mc.moveright();
        check(4);
        }
        else if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_UP)
        {
        mc.moveup();
        check(1);
        } 
        else if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_DOWN)
        {
        mc.movedown();
        check(2);
        }
       }
    }
    public void sw(){
        //zorgt er voor dat het "licht" na 2.5ms verdwijnt
        //en daarna pas na 2.5 ms weer een ander licht aansteekt
        
        if(flick == true){
            
            simon();
            flick = false;
        }
        else{
            mc.leave();
            flick = true;
        }
        
    }
    public void simon(){//maakt een sequentie van richtingen aan voor de speler om na te doen
       if(pTurn == false && gStart == true){
        if(nTurn == true){
         randomNum = 1 + (int)(Math.random() * 4);
         seq[numb]=randomNum;
         numb += 1;
         nTurn = false;
        }
        if(seq[num] != 0){
         switch(seq[num]){
             case 1:
                 mc.moveup();
                 num += 1;
                 break;
             case 2:
                 mc.movedown();
                 num += 1;
                 break;
             case 3:
                 mc.moveleft();
                 num += 1;
                 break;
             case 4:
                 mc.moveright();
                 num += 1;
                 break;
         }         
        } 
        else{
            pTurn = true;
            num = 0;
        }
        }
       }
    public void check(int x){//controlleert of de input juist is tegenover de sequentie van simon
       
        if(x == seq[numbe]){
            //indien juist gaat die naar de volgende in de sequentie
            numbe += 1;
            if(seq[numbe] == 0){
            //als er niets meer overschiet word de beurt naar simon overgelaten
           mc.score(score);
           score++;
           numbe = 0;
           nTurn = true;
           pTurn = false;
           
       }
        }
        else{//indien fout word het spel stop gezet en gereset
            num = 0;
            numb = 0;
            numbe = 0;
            int i = 0;
            for(i = 0; i<50 ; i++){seq[i] = 0;}
            score = 1;
            mc.scReset();
            gStart = false;
            nTurn = true;
            pTurn = false;
        }
    }
}
