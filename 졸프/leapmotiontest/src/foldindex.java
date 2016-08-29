import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Finger.Type;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;


public class foldindex extends newgesture {

	@Override
	public boolean isgesture(Frame frame) {
		// TODO Auto-generated method stub
		
		for(Hand hand : frame.hands()) {
			for(Finger finger : hand.fingers()) {
				if(finger.type()==Type.TYPE_INDEX
						&& finger.bone(Bone.Type.TYPE_DISTAL).direction().getZ()<0
						&& finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction().getZ()>0)
					return true;
			}
		}
		
		return false;
	}

}
