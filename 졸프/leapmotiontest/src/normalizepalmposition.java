import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Vector;


public class normalizepalmposition {
	
	public static float getx(Frame frame) {
		
		float ipx;
		
		Vector ip = getposition(frame); 
		ipx = ip.getX();
		
		return ipx;
	}
	
	public static float gety(Frame frame) {
		
		float ipy;
		
		Vector ip = getposition(frame);
		ipy = ip.getY();
		
		return ipy;
	}
	
	public static Vector getposition(Frame frame) {
		
		InteractionBox ib = frame.interactionBox();
		
		for(Hand hand : frame.hands()) {
			return ib.normalizePoint(hand.stabilizedPalmPosition());
		}
		
		return null;
	}
}
