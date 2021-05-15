package pack;

public class Time {
	private int hour = 0, currentDay = 0, dayNum = 1;
	private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	public Time() {
		
	}
	public void addTime(int hours) {
		for(int i = 0; i < hours; i++) {
			if(hour == 24) {
				hour = 1;
				dayNum++;
				if(currentDay == 6) {
					currentDay = 0;
				}
				else {
					currentDay++;
				}
			}
			else {
				hour++;	
			}
		}
		System.out.println("It is " + hour + " o'clock on " + days[currentDay] + ".");
	}
	public String getDay() {
		return days[currentDay];
	}
	public int getDayNum() {
		return dayNum;
	}
	public int getTime() {
		return hour;
	}
	public void setTime(int time) {
		hour = time;
	}
}
