package pack;

public class Time {
	private int hour = 0, currentDay = 0, dayNum = 1;
	private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	public Time() {
		
	}
	//increases the time by int hours
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
	//returns the day as a string
	public String getDay() {
		return days[currentDay];
	}
	//returns the number of days since the start of the game
	public int getDayNum() {
		return dayNum;
	}
	//returns the time
	public int getTime() {
		return hour;
	}
	//sets the time to int time
	public void setTime(int time) {
		hour = time;
	}
}
