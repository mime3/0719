import com.leapmotion.leap.*;

public class testlistener1 extends Listener {
	
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
    	
    	for(Hand hand : frame.hands()) {
    		if(hand.isLeft()){
    			System.out.println("lefthand grabangle : "+hand.grabAngle());
//    			System.out.println("lefthand pitch : "+hand.direction().pitch());
//    			System.out.println("lefthand yaw : "+hand.direction().yaw());
    			System.out.println("lefthand roll : "+hand.palmNormal().roll());
    		}
    	}
    }
}
