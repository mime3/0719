import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.leapmotion.leap.*;

public class testlistener5 extends Listener {
	
	Robot rb;
	newgesture rs, ls, us, ds, ph, fi, cc;
	boolean click;
	
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}
	
	public void onConnect(Controller controller) {
		try {
			rb = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs = new rightswipe(controller);
		rs.setvalid(true);
		ls = new leftswpie(controller);
		ls.setvalid(true);
		us = new upswipe(controller);
		us.setvalid(true);
		ds = new downswipe(controller);
		ds.setvalid(true);
		ph = new pointerhand();
		ph.setvalid(true);
		fi = new foldindex();
		fi.setvalid(true);
		cc = new clench();
		cc.setvalid(true);
		click = false;
		
		System.out.println("Connected");
    }

    public void onDisconnect(Controller controller) {
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    
    public void onFrame(Controller controller) {
    	Frame frame = controller.frame();
    	
    	if(rs.isgesture(frame)) {
    		rb.keyPress(KeyEvent.VK_LEFT);
    		rb.keyRelease(KeyEvent.VK_LEFT);
    	}
    	
    	else if(ls.isgesture(frame)) {
    		rb.keyPress(KeyEvent.VK_RIGHT);
    		rb.keyRelease(KeyEvent.VK_RIGHT);
    	}
    	
    	else if(us.isgesture(frame)) {
    		rb.keyPress(KeyEvent.VK_DOWN);
    		rb.keyRelease(KeyEvent.VK_DOWN);
    	}
    	
    	else if(ds.isgesture(frame)) {
    		rb.keyPress(KeyEvent.VK_UP);
    		rb.keyRelease(KeyEvent.VK_UP);
    	}
    	
    	else if(ph.isgesture(frame)) {
    		rb.mouseMove(coodinatetransform.transform_x(normalizepalmposition.getx(frame)), coodinatetransform.transform_y(normalizepalmposition.gety(frame)));
    		
    		if(fi.isgesture(frame) && click==false){
    			click = true;
    			rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    		}
    		else if(fi.isgesture(frame)==false){
    			click = false;
    			rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    		}
    	}
    	
    	else if(cc.isgesture(frame)) {
    		rb.keyPress(KeyEvent.VK_ENTER);
    		rb.keyRelease(KeyEvent.VK_ENTER);
    	}
    }
}
