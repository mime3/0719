import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Finger.Type;

public class testlistener1 extends Listener {
	
	Robot rb;
	Dimension win;
	
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
        win = Toolkit.getDefaultToolkit().getScreenSize();
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
    	InteractionBox interactionbox = frame.interactionBox();
    	Vector iv = null, tv = null, mv = null;
    	
    	for(Hand hand : frame.hands()) {
/*    		if(hand.isLeft()){
    			System.out.println("lefthand grabangle : "+hand.grabAngle());
//    			System.out.println("lefthand pitch : "+hand.direction().pitch());
//    			System.out.println("lefthand yaw : "+hand.direction().yaw());
    			System.out.println("lefthand roll : "+hand.palmNormal().roll());
    		}
*/    	
//    		System.out.println("pinchdistance : "+hand.pinchDistance());
    		for(Finger finger : frame.fingers()) {
/*    			if(finger.type()==Type.TYPE_INDEX) {
//    				rb.mouseMove((int)((win.width-1)*interactionbox.normalizePoint(finger.tipPosition()).getX()), (int)((win.height-1)*(1-interactionbox.normalizePoint(finger.tipPosition()).getY())));
//    				rb.mouseMove(interactionbox.normalizePoint(finger.tipPosition()).getX(), interactionbox.normalizePoint(finger.tipPosition()).getY());
//    				System.out.println("indexfinger tipposition : "+interactionbox.normalizePoint(finger.tipPosition()));
    				switch(finger.touchZone()) {
    				case ZONE_NONE:
    					System.out.println("none");
    					break;
    				case ZONE_HOVERING:
    					System.out.println("hovering");
    					break;
    				case ZONE_TOUCHING:
    					System.out.println("touching");
    					break;
    				}
    			}
*/    		
    			switch(finger.type()) {
    			case TYPE_INDEX:
//    				System.out.println("index : "+finger.stabilizedTipPosition());
    				iv = finger.direction();
//    				System.out.println("finger : "+finger.direction());
    				Bone bone1 = finger.bone(Bone.Type.TYPE_DISTAL);
    				Bone bone2 = finger.bone(Bone.Type.TYPE_INTERMEDIATE);
    				Bone bone3 = finger.bone(Bone.Type.TYPE_PROXIMAL);
    				System.out.println("tip : "+iv);
    				System.out.println("distal : "+bone1.direction());
    				System.out.println("intermediate : "+bone2.direction());
    				System.out.println("proximal : "+bone3.direction());
    				break;
    			case TYPE_THUMB:
//    				System.out.println("thumb : "+finger.stabilizedTipPosition());
    				tv = finger.stabilizedTipPosition();
    				break;
    			case TYPE_MIDDLE:
    				mv = finger.direction();
    			}    			
    		}
    	}
//    	System.out.println("angle : "+iv.angleTo(mv));
    	
/*    	for(Pointable pointable : frame.pointables()) {
    		System.out.println("touchdistance : "+pointable.touchDistance());
    	}
*/    }
}
