import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.Type;
import com.leapmotion.leap.Hand;


public class rightswipe extends newgesture {
	
	boolean start = false;
	
	public rightswipe(Controller controller) {
		// TODO Auto-generated constructor stub
		
		controller.enableGesture(Type.TYPE_SWIPE, true);
	}

	@Override
	public boolean isgesture(Frame frame) {
		// TODO Auto-generated method stub
		
		if(valid) {
			for(Gesture gesture : frame.gestures()) {
				if(gesture.type()==Type.TYPE_SWIPE) {
					for(Hand hand : frame.hands()){
						switch(gesture.state()) {
						case STATE_START:
							if(start==false && swipedirection.direction(hand)==swipedirection.right)
								start = true;
							break;
						case STATE_STOP:
							if(start){
								start = false;
								return true;
							}
							break;
						}
					}
				}
			}
		}
		
		return false;
	}

}
