import java.io.IOException;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.Type;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testlistener listener = new testlistener();
		Controller controller = new Controller();
		
		controller.addListener(listener);
		
		controller.enableGesture(Type.TYPE_CIRCLE, true);
		controller.enableGesture(Type.TYPE_SCREEN_TAP, true);
		controller.enableGesture(Type.TYPE_KEY_TAP, true);
		controller.enableGesture(Type.TYPE_SWIPE, true);
		
		System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller.removeListener(listener);
	}

}
