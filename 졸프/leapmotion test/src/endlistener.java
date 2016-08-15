import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.leapmotion.leap.*;

public class testlistener2 extends Listener 
{
	public static int act[];
	act = new int[10];
	for (int i = 0;i<10 ;i++ )
		act[i] = 0;
	
	public static boolean ismousemode = false;
	
	String action = "no_act";

	Robot rb;




	public void onInit(Controller controller) 
	{
		System.out.println("초기화됨");
	}
	
	public void onConnect(Controller controller) 
	{
        System.out.println("연결됨");
        try 
		{
    		rb = new Robot();
    	} 
		catch (AWTException e) 
		{
    		e.printStackTrace();
    	}
    }

    public void onDisconnect(Controller controller) 
	{
        System.out.println("연결해제됨");
    }

    public void onExit(Controller controller) 
	{
        System.out.println("종료됨");
    }
    
    public void onFrame(Controller controller) 
	{
    	Frame frame = controller.frame();
    	
    	for(Hand hand : frame.hands()) 
		{ 
			// 마우스 모드 체크
			if (ismousemode == true)
				continue;
			

			// 실행조건 체크
    		if(hand.isLeft() && hand.grabAngle()>3 && act[0] == 1 /*&& (hand.palmNormal().roll()>2 || hand.palmNormal().roll()<-2)*/) // 주먹쥐기
    			action = "windowsbar";
    		
    		else if(hand.isLeft() && (hand.palmNormal().roll()<1 && hand.palmNormal().roll()>-1) && act[1] == 1) // 손가락으로 V그리기
    			action = "leftparm";
    		// 아래부분으로는 실행조건이 제대로 설정되지 않아있다. 설정후 본 주석을 삭제할것
			else if (hand.isLeft() &&  && act[2] == 1)	// 오른쪽으로 손짓
				action = "turnright";

			else if (hand.isLeft() &&  && act[3] == 1)	// 왼쪽으로 손짓
				action = "turnleft";
			
			else if (&& act[4] == 1)
				action = "kakaotalk";
			
			else if (&& act[] == 1)	// 마우스 모드 변경

			


			// 실행부
			
			Process p;
			
			switch (action)		
			{
			case "no_act":		// 아무런 행동 하지않음
				break;
			case "leftparm":	// 메모장 실행
				for(Finger finger : hand.fingers()) 
				{
    				if(finger.type()==Type.TYPE_THUMB && finger.direction().getZ()<0 && finger.direction().getX()<0)
    					scissors = scissors+1;
    				if(finger.type()==Type.TYPE_INDEX && finger.direction().getZ()<0)
    					scissors = scissors+1;
    				if(finger.type()==Type.TYPE_MIDDLE && finger.direction().getZ()<0)
    					scissors = scissors+1;
    				if(finger.type()==Type.TYPE_RING && finger.direction().getZ()>0)
    					scissors = scissors+1;
    				if(finger.type()==Type.TYPE_PINKY && finger.direction().getZ()>0)
    					scissors = scissors+1;

			    	if(scissors==5)
					{
						try 
						{
							p = rt.exec("notepad");
							int x = p.waitFor();
							System.out.println(x);
						} 
						catch (IOException | InterruptedException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
    			}
				break;
			case "windowsbar":	// 시작메뉴 띄우기
				rb.keyPress(KeyEvent.VK_CONTROL);
    			rb.keyPress(KeyEvent.VK_ESCAPE);
    			rb.keyRelease(KeyEvent.VK_ESCAPE);
    			rb.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case "turnright":	// 오른쪽 키보드 입력
				rb.keyPress(KeyEvent.VK_RIGHT);
				rb.keyRelease(KeyEvent.VK_RIGHT);
				break;
			case "turnleft":	// 왼쪽키보드 입력
				rb.keyPress(KeyEvent.VK_LEFT);
				rb.keyRelease(KeyEvent.VK_LEFT);
				break;
			case "kakaotalk":	// 카카오톡 실행
				try 
				{// 위와 같은방법으로 실행했으나 앞으로는 같은코드의 반복을 피하기위해 범용으로 다시 코딩하는편이 좋을것 같다.
					p = rt.exec("C:\Program Files (x86)\Kakao\KakaoTalk\KakaoTalk.exe");
					int x = p.waitFor();
					System.out.println(x);
				}
				catch (IOException | InterruptedException e)
				{
					e.printStackTrace();
				}
				
				break;

		}
    	}
	
		
    	

    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 해야할것
//	
// 1. 마우스 모드 True 일때 손으로 마우스 좌표를 변경해주어야 함 
// 2. 다양한 모션에 대한 값이 필요하다.
// 3. 자바 문법을 잘 모르겠다. 일단 define , enum 사용불가능 대신에 switch에서 int값이외에 문자를 포함해 좀더 받을 수 있다.
// 4. 무엇을 더 할건지 아이디어가 필요하다.
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////