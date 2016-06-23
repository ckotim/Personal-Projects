
public class WallClock {

	public final int hour;
	public final int minute;
	
	public WallClock(int hour, int minute){
		this.hour = hour > 12 || hour < 0 ? 12 : hour;
		this.minute = minute > 60 || minute < 0 ? 0 : minute;
		
	}
	
	public WallClock(int hour){
		this(hour, 0);
	}
	
	public WallClock(){
		this(12);
	}
	
	public int getHour(){
		return this.hour;
	}
	
	public int getMinute(){
		return this.minute;
	}
	
	public WallClock addMinute (int minutes){
		return new WallClock(this.hour + (minutes / 60), this.minute + (minutes%60));
	}
	@Override
	public String toString(){
		String s1 = hour + "";
		String s2 = minute + "";
		if(this.minute < 10)
			s2 = "0" + minute;
		if(this.hour < 10)
			s1 = "0" + minute;
		return s1 + ":" + s2;
	}	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		WallClock wc = new WallClock();
		System.out.println(wc.toString());
		wc = wc.addMinute(60);
		System.out.println(wc.toString());
		

	}

}
