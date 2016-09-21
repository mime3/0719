import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector
import java.lang.Math; 

//////////////////////////////////////////////////////////////////////
// 이름 : Turnreverseclockwise_palm 
// 설명 : 오른손의 손바닥이 왼쪽방향에서 아래방향으로 돌렸을때 true 반환
// 리턴값 : boolean
//////////////////////////////////////////////////////////////////////
//	손가락 방향이 들어가지 않으면 원하지 않을때 실행될 가능성이 있다.	
//	
//////////////////////////////////////////////////////////////////////
public class turnreverscclockwise_palm extends newgesture {
	
	boolean start = false;

	@Override
	public boolean isgesture(Frame frame) {
		// TODO Auto-generated method stub
		// PI값 정의
		// float PI = Math.PI;
		for(Hand hand : frame.hands()) {
			if(hand.isRight()){
				Vector palmvector = hand.palmNormal();
				Vector leftvector = Vector.left();
				Vector downvector = Vector.down(); 
				
				if (!palmvector.isValid()) //무한대인지
					return false;

				// angleInRadians = PI/2 (90 degrees)
				// boolean equals(Vector other) 같은지
				
				// 오차값 10 degrees
				if ((double)palmvector.angleTo(leftvector) < Math.PI / 2 / 9)
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
