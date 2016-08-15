import java.io.IOException;

import com.leapmotion.leap.*;


public class testlistener3 extends Listener {
	
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
    	
    	for(Hand hand : frame.hands()) {
    		if(hand.isLeft() && hand.grabAngle()>3) {
    			try {
					Process p = rt.exec("notepad");
					int x = p.waitFor();
					System.out.println(x);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
}
