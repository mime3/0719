import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector; 
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


//////////////////////////////////////////////////////////////////////
// �̸� : touching zone
// ���� : zone �ȿ� touch���� �� ó��
// ���ϰ� : boolean
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
	
		
	// �հ��� ������ 0�϶�  
	if (frame.fingers().isEmpty())
		return false;
	
	for(Finger finger : frame.fingers())
	{
		if(finger.type()!=Type.TYPE_INDEX)
			return false;
		// ����Ȯ ���ɼ� ���� // �����ؾ��ҵ�
		//if (finger.direction() == Vector.forward())
		if(!finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction().getZ()>0)
			return false;
		// �̵���� ���� Ŭ���� ������ ���콺 ��ǥ����� ���Ѱ�
		Vector normalizedPosition = iBox.normalizePoint(pointable.stabilizedTipPosition());
		float pixelX = normalizedPosition.getX() /* *  windowWidth*/;
		float pixelY = windowHeight - normalizedPosition.getY() /* * windowHeight*/;
		int	cx = (int)pixelX;
		int cy = (int)pixelY;
		// �ѹ��� Ŭ������ ���� ����
		boolean click = false;
		
		switch (finger.touchZone())
		{
		case ZONE_NONE:
			click = true;
			break;
		case ZONE_HOVERRING:
			// ������ �̵�
			click = true;
			rb.mouseMove(coodinatetransform.transform_x(normalizepalmposition.getx(frame)), coodinatetransform.transform_y(normalizepalmposition.gety(frame)));
			break;
		case ZONE_TOUCHING:
			// Ŭ��
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