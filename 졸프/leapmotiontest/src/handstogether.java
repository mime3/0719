import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;


public class handstogether extends newgesture {

	@Override
	public boolean isgesture(Frame frame) {
		// TODO Auto-generated method stub
		
		Vector lhp = null, rhp = null;
		
		for(Hand hand : frame.hands()) {
			if(hand.isLeft())
				lhp = hand.palmPosition();
			if(hand.isRight())
				rhp = hand.palmPosition();
		}
		
		if(lhp.distanceTo(rhp)<10)
			return true;
		
		return false;
	}

}
