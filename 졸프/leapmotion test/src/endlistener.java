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
		System.out.println("�ʱ�ȭ��");
	}
	
	public void onConnect(Controller controller) 
	{
        System.out.println("�����");
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
        System.out.println("����������");
    }

    public void onExit(Controller controller) 
	{
        System.out.println("�����");
    }
    
    public void onFrame(Controller controller) 
	{
    	Frame frame = controller.frame();
    	
    	for(Hand hand : frame.hands()) 
		{ 
			// ���콺 ��� üũ
			if (ismousemode == true)
				continue;
			

			// �������� üũ
    		if(hand.isLeft() && hand.grabAngle()>3 && act[0] == 1 /*&& (hand.palmNormal().roll()>2 || hand.palmNormal().roll()<-2)*/) // �ָ����
    			action = "windowsbar";
    		
    		else if(hand.isLeft() && (hand.palmNormal().roll()<1 && hand.palmNormal().roll()>-1) && act[1] == 1) // �հ������� V�׸���
    			action = "leftparm";
    		// �Ʒ��κ����δ� ���������� ����� �������� �ʾ��ִ�. ������ �� �ּ��� �����Ұ�
			else if (hand.isLeft() &&  && act[2] == 1)	// ���������� ����
				action = "turnright";

			else if (hand.isLeft() &&  && act[3] == 1)	// �������� ����
				action = "turnleft";
			
			else if (&& act[4] == 1)
				action = "kakaotalk";
			
			else if (&& act[] == 1)	// ���콺 ��� ����

			


			// �����
			
			Process p;
			
			switch (action)		
			{
			case "no_act":		// �ƹ��� �ൿ ��������
				break;
			case "leftparm":	// �޸��� ����
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
			case "windowsbar":	// ���۸޴� ����
				rb.keyPress(KeyEvent.VK_CONTROL);
    			rb.keyPress(KeyEvent.VK_ESCAPE);
    			rb.keyRelease(KeyEvent.VK_ESCAPE);
    			rb.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case "turnright":	// ������ Ű���� �Է�
				rb.keyPress(KeyEvent.VK_RIGHT);
				rb.keyRelease(KeyEvent.VK_RIGHT);
				break;
			case "turnleft":	// ����Ű���� �Է�
				rb.keyPress(KeyEvent.VK_LEFT);
				rb.keyRelease(KeyEvent.VK_LEFT);
				break;
			case "kakaotalk":	// īī���� ����
				try 
				{// ���� ����������� ���������� �����δ� �����ڵ��� �ݺ��� ���ϱ����� �������� �ٽ� �ڵ��ϴ����� ������ ����.
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
// �ؾ��Ұ�
//	
// 1. ���콺 ��� True �϶� ������ ���콺 ��ǥ�� �������־�� �� 
// 2. �پ��� ��ǿ� ���� ���� �ʿ��ϴ�.
// 3. �ڹ� ������ �� �𸣰ڴ�. �ϴ� define , enum ���Ұ��� ��ſ� switch���� int���̿ܿ� ���ڸ� ������ ���� ���� �� �ִ�.
// 4. ������ �� �Ұ��� ���̵� �ʿ��ϴ�.
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////