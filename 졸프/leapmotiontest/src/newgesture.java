import com.leapmotion.leap.*;

public abstract class newgesture {
	
	boolean valid = false;
	
	public boolean isvalid(){
		return valid;
	}
	
	public void setvalid(boolean svalid){
		valid = svalid;
	}
	
	public abstract boolean isgesture(Frame frame);
}
