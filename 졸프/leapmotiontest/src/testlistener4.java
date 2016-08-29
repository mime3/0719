import java.io.IOException;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Finger.Type;

public class testlistener4 extends Listener {
	Runtime rt = Runtime.getRuntime();
	
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}
	
	public void onConnect(Controller controller) {
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
    	int scissors = 0;
    	
    	for(Hand hand : frame.hands()) {
    		if(hand.isLeft() && (hand.palmNormal().roll()<1 && hand.palmNormal().roll()>-1)){
    			for(Finger finger : hand.fingers()) {
    				if(finger.type()==Type.TYPE_THUMB && finger.direction().getZ()<0 && finger.direction().getX()<0)
    					scissors = scissors+1;
    				if(finger.type()==Type.TYPE_INDEX && finger.direction().getZ()<0)
    					scissors = scissors+1;
    				if(finger.type()==Type.TYPE_MIDDLE && finger.direction().getZ()<0)
    					scissors = scissors+1;
    				if(finger.type()==Type.TYPE_RING && finger.direction().getZ()>0)
    					scissors = scissors+1;
    				if(finger.type()==Type.TYPE_PINKY && finger.direction().getZ()>0)
    					scissors = scissors+1;
    			}
    		}
    	}
    	
    	if(scissors==5){
    		Process p;
			try {
				p = rt.exec("notepad");
	    		int x = p.waitFor();
	    		System.out.println(x);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
