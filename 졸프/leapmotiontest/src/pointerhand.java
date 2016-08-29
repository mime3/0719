import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Finger.Type;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;


public class pointerhand extends newgesture {

	@Override
	public boolean isgesture(Frame frame) {
		// TODO Auto-generated method stub
		
		int pf = 0;
		
		for(Hand hand : frame.hands()) {
			if(hand.isRight()){
				for(Finger finger : hand.fingers()) {
					if(finger.type()==Type.TYPE_THUMB && finger.direction().getZ()<0)
						pf = pf+1;
					if(finger.type()==Type.TYPE_INDEX && finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction().getZ()>0)
						pf = pf+1;
					if(finger.type()==Type.TYPE_MIDDLE && finger.direction().getZ()>0)
						pf = pf+1;
					if(finger.type()==Type.TYPE_RING && finger.direction().getZ()>0)
						pf = pf+1;
					if(finger.type()==Type.TYPE_PINKY && finger.direction().getZ()>0)
						pf = pf+1;
				}
				if(pf==5)
					return true;
			}
		}
		
		return false;
	}

}
