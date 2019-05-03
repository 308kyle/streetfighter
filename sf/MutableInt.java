package sf;

public class MutableInt {
	private int i;
	public MutableInt(int i) {
		this.i = i;
	}
	public int getInt() {
		return i;
	}
	public void setInt(int newInt) {
		i = newInt;
	}
}
