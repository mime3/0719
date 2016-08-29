import java.io.IOException;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Controller.PolicyFlag;
import com.leapmotion.leap.Gesture.Type;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testlistener5 listener = new testlistener5();
		Controller controller = new Controller();
		
		controller.addListener(listener);
		controller.setPolicy(PolicyFlag.POLICY_BACKGROUND_FRAMES);
/*		
		controller.enableGesture(Type.TYPE_CIRCLE, true);
		controller.enableGesture(Type.TYPE_SCREEN_TAP, true);
		controller.enableGesture(Type.TYPE_KEY_TAP, true);
		controller.enableGesture(Type.TYPE_SWIPE, true);
*/			
		System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller.removeListener(listener);
	}

}
