import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector
import java.lang.Math; 

//////////////////////////////////////////////////////////////////////
// �̸� : Turnclockwise_palm 
// ���� : �޼��� �չٴ��� �����ʹ��⿡�� �Ʒ��������� �������� true ��ȯ
// ���ϰ� : boolean
//////////////////////////////////////////////////////////////////////
public class turnclockwise_palm extends newgesture {
	
	boolean start = false;

	@Override
	public boolean isgesture(Frame frame) {
		// TODO Auto-generated method stub
		// PI�� ����
		// float PI = Math.PI;
		for(Hand hand : frame.hands()) {
			if(hand.isLeft()){
				Vector palmvector = hand.palmNormal();
				Vector rightvector = Vector.right();
				Vector downvector = Vector.down(); 
				
				if (!palmvector.isValid()) //���Ѵ�����
					return false;

				// angleInRadians = PI/2 (90 degrees)
				// boolean equals(Vector other) ������
				
				// ������ 10 degrees
				if ((double)palmvector.angleTo(rightvector) < Math.PI / 2 / 9)
					start = true;
				else if((double)palmvector.angleTo(downvector) < Math.PI / 2 / 9 && start){
					start = false;
					return true;
				}
			}
		}
		
		return false;
	}

}
