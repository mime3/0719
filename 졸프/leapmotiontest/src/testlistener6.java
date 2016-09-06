import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;


public class testlistener6 extends Listener {
	
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
    	Vector lhp = null, rhp = null;
    	
    	for(Hand hand : frame.hands()) {
    		if(hand.isLeft())
    			lhp = hand.palmPosition();
    		if(hand.isRight())
    			rhp = hand.palmPosition();
    	}
    	
    	System.out.println(lhp.distanceTo(rhp));
    }
}
