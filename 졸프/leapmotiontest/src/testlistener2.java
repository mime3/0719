import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.leapmotion.leap.*;


public class testlistener2 extends Listener {
	
	Robot rb;
	
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}
	
	public void onConnect(Controller controller) {
        System.out.println("Connected");
        try {
    		rb = new Robot();
    	} catch (AWTException e) {
    		e.printStackTrace();
    	}
    }

    public void onDisconnect(Controller controller) {
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    
    public void onFrame(Controller controller) {
    	Frame frame = controller.frame();
    	
    	for(Hand hand : frame.hands()) {
    		if(hand.isLeft() && hand.grabAngle()>3 /*&& (hand.palmNormal().roll()>2 || hand.palmNormal().roll()<-2)*/) {
    			rb.keyPress(KeyEvent.VK_CONTROL);
    			rb.keyPress(KeyEvent.VK_ESCAPE);
    			rb.keyRelease(KeyEvent.VK_ESCAPE);
    			rb.keyRelease(KeyEvent.VK_CONTROL);
    		}
    	}
    }
}
