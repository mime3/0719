import java.awt.Dimension;
import java.awt.Toolkit;


public class coodinatetransform {
	
	static Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int transform_x(float x) {
		
		int tx = (int)((resolution.getWidth()-1)*x);
		
		return tx;
	}
	
	public static int transform_y(float y) {
		
		int ty = (int)((resolution.getWidth()-1)*(1-y));
		
		return ty;
	}
}
