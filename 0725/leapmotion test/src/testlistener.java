import com.leapmotion.leap.*;

public class testlistener extends Listener {
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
	
    	for(Gesture gesture : frame.gestures()) {
    		switch(gesture.type()) {
    			case TYPE_CIRCLE:
    				System.out.println("circle gesture");
    				break;
    			case TYPE_SCREEN_TAP:
    				System.out.println("screen tap gesture");
    				break;
    			case TYPE_KEY_TAP:
    				System.out.println("key tap gesture");
    				break;
    			case TYPE_SWIPE:
    				System.out.println("swipe gesture");
    				break;
    		}
    	}
    }
}
