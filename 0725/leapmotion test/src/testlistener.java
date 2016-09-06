import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;

public class testlistener extends JFrame {

	Controller controller = new Controller();

	public MyMotionListener motionListener;
	public MyMouseListener mouseListener;

	public testlistener() {

		motionListener = new MyMotionListener();
		mouseListener = new MyMouseListener();
		
		JPanel panel = new JPanel();
		panel.addMouseListener(mouseListener);
		panel.addMouseMotionListener(mouseListener);
		
		setContentPane(panel);
		setSize(640, 580);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// com.leapmotion.leap.Gesture.Type는 JFrame가 가진 Type과 겹쳐서 오류가 나는 것 같아 이렇게 함.
		controller.enableGesture(com.leapmotion.leap.Gesture.Type.TYPE_CIRCLE, true);
		controller.enableGesture(com.leapmotion.leap.Gesture.Type.TYPE_SCREEN_TAP, true);
		controller.enableGesture(com.leapmotion.leap.Gesture.Type.TYPE_KEY_TAP, true);
		controller.enableGesture(com.leapmotion.leap.Gesture.Type.TYPE_SWIPE, true);
		controller.addListener(motionListener);
	}

	class MyMouseListener extends MouseAdapter {

		Point startP = null;
		Point endP = null;

		public void mousePressed(MouseEvent e) {

			begin(e.getPoint()); // 클릭한부분을 시작점으로
		}

		public void mouseDragged(MouseEvent e) {

			end(startP);
			begin(e.getPoint());
			
			lineRender();
		}

		public void begin(Point p) {
			startP = p;
		}

		public void end(Point p) {
			endP = p;
		}
		
		public void move(Point p) {
			move(p.x, p.y);
		}
		
		public void move(int x, int y) {
			endP = startP;
			startP.x = endP.x + x;
			startP.y = endP.y + y;
		}

		public void lineRender() {
			getGraphics().drawLine(startP.x, startP.y, endP.x, endP.y);
		}
	}

	class MyMotionListener extends Listener {
		
		

		public void onInit(Controller controller) {

		}

		public void onConnect(Controller controller) {

		}

		public void onDisconnect(Controller controller) {

		}

		public void onExit(Controller controller) {

		}

		public void onFrame(Controller controller) {

			Frame frame = controller.frame();

			for (Gesture gesture : frame.gestures()) {
				switch (gesture.type()) {
				case TYPE_CIRCLE:

					break;
				case TYPE_SCREEN_TAP:
					
				{
					switch (gesture.state()) {
					case STATE_START:
						// Handle starting gestures
						break;
					case STATE_UPDATE:
						// Handle continuing gestures
						Pointable point = frame.pointables().frontmost();
						mouseListener.move(
								(int)point.tipVelocity().getX(),
								(int)point.tipVelocity().getY());
						mouseListener.lineRender();
						break;
					case STATE_STOP:
						// Handle ending gestures						
						break;
					default:
						// Handle unrecognized states
						break;
					}
				}
					break;
					
				case TYPE_KEY_TAP:

					break;
				case TYPE_SWIPE:

					break;
				}
			}
		}
	}
}
