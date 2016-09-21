import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector; 
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


//////////////////////////////////////////////////////////////////////
// 이름 : touching zone
// 설명 : zone 안에 touch했을 때 처리
// 리턴값 : boolean
//////////////////////////////////////////////////////////////////////


boolean zone()
{
	
	//PShape cursor;
	Controller leap;
	//int windowWidth = 800;
	//int windowHeight = 800;

	InteractionBox iBox = leap.frame().interactionBox();
	PointableList pointables = leap.frame().pointables();
	FingerList fingers = leap.frame().fingers();
	
		
	// 손가락 개수가 0일때  
	if (frame.fingers().isEmpty())
		return false;
	
	for(Finger finger : frame.fingers())
	{
		if(finger.type()!=Type.TYPE_INDEX)
			return false;
		// 부정확 가능성 있음 // 수정해야할듯
		//if (finger.direction() == Vector.forward())
		if(!finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction().getZ()>0)
			return false;
		// 이동기능 없이 클릭만 했을때 마우스 좌표계산을 위한것
		Vector normalizedPosition = iBox.normalizePoint(pointable.stabilizedTipPosition());
		float pixelX = normalizedPosition.getX() /* *  windowWidth*/;
		float pixelY = windowHeight - normalizedPosition.getY() /* * windowHeight*/;
		int	cx = (int)pixelX;
		int cy = (int)pixelY;
		// 한번의 클릭만을 위한 변수
		boolean click = false;
		
		switch (finger.touchZone())
		{
		case ZONE_NONE:
			click = true;
			break;
		case ZONE_HOVERRING:
			// 포인터 이동
			click = true;
			rb.mouseMove(coodinatetransform.transform_x(normalizepalmposition.getx(frame)), coodinatetransform.transform_y(normalizepalmposition.gety(frame)));
			break;
		case ZONE_TOUCHING:
			// 클릭
			if (click == false)
				return false;
			rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			click = false;
			break;
		default:
			break;
		}
		return true;
	}
}