import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;

public class testlistener extends JFrame implements Runnable {

	Controller controller = new Controller();

	public MyMotionListener motionListener;
	public MyMouseListener mouseListener;

	Thread th = null;
	BlockingQueue<Point> bq = new ArrayBlockingQueue<Point>(100);

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

		// com.leapmotion.leap.Gesture.Type는 JFrame가 가진 Type과 겹쳐서 오류가 나는 것 같아
		// 이렇게 함.
		controller.enableGesture(com.leapmotion.leap.Gesture.Type.TYPE_CIRCLE,
				true);
		controller.enableGesture(
				com.leapmotion.leap.Gesture.Type.TYPE_SCREEN_TAP, true);
		controller.enableGesture(com.leapmotion.leap.Gesture.Type.TYPE_KEY_TAP,
				true);
		controller.enableGesture(com.leapmotion.leap.Gesture.Type.TYPE_SWIPE,
				true);
		controller.addListener(motionListener);

		th = new Thread(this);
		th.start();
	}

	@Override
	public void update(Graphics g) {
		while (!bq.isEmpty()) {
			mouseListener.move(bq.poll());
			mouseListener.lineRender();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				update(null);
				Thread.sleep(50);
			}
		} catch (Exception e) {

		}
	}

	class MyMouseListener extends MouseAdapter {
		Point startP = new Point(0, 0);
		Point endP = new Point(0, 0);

		public void mousePressed(MouseEvent e) {
			begin(e.getX(), e.getY());
		}

		public void mouseDragged(MouseEvent e) {
			end(startP.x, startP.y);
			begin(e.getX(), e.getY());

			lineRender();
		}

		public void begin(Point p) {
			begin(p.x, p.y);
		}

		public void begin(int x, int y) {
			startP.x = x;
			startP.y = y;
		}

		public void end(Point p) {
			end(p.x, p.y);
		}

		public void end(int x, int y) {
			endP.x = x;
			endP.y = y;
		}

		public void move(Point p) {
			move(p.x, p.y);
		}

		public void move(int x, int y) {
			endP.x = startP.x;
			endP.y = startP.y;
			startP.x = endP.x + x;
			startP.y = endP.y + y;
		}

		public synchronized void lineRender() {
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
					break;

				case TYPE_KEY_TAP:
					System.exit(0);
					break;

				case TYPE_SWIPE: {

					if (gesture.state() == com.leapmotion.leap.Gesture.State.STATE_STOP) {
						Pointable point = frame.pointables().frontmost();
						try {
							bq.put(new Point(
									(int) point.tipVelocity().getX(),
									(int) point.tipVelocity().getY()));

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
					break;
				}
			}
		}
	}
}
