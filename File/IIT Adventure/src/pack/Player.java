package pack;

import java.util.Random;

public class Player extends Student{
	int studyEnergy;
	boolean didSleep = false;
	double GPA;
	Random rand = new Random();
	public Player(String name, String major, String housing) {
		studyEnergy = 4;
		this.name = name;
		this.housing = housing;
		this.major = major;
	}
	public void study(int hours, Course course, Time time) {
		if(studyEnergy == 0){
			System.out.println("You don't have it in you to study right now");
		}else if(studyEnergy >= hours) {
			course.testReadiness+=hours/2+1;
			studyEnergy-=hours;
			if(course.testReadiness >= (course.hours*4+4)){
				System.out.println("You studied for " + hours + " hours. You feel pretty comfortable with the material.");
			}else {
				System.out.println("You studied for " + hours + " hours.");
			}
			time.addTime(hours);
		}else{
			course.testReadiness+=studyEnergy/2+1;
			if(course.testReadiness >= (course.hours*4+4)){
				System.out.println("You studied for " + hours + " hours. You feel pretty comfortable with the material.");
			}else {
				System.out.println("You ended up studying for " + studyEnergy + " hours.");
			}
			time.addTime(studyEnergy);
			studyEnergy = 0;
		}
		System.out.println("course readiness: " + course.testReadiness);
	}
	public void sleep(Time time) {
		int hours;
		if(time.getTime() > 8) {
			hours = (24 - time.getTime()) + 8;
		}else {
			hours = 8 - time.getTime();
		}
		studyEnergy = hours/2;
		System.out.println("You slept for " + hours + " hours");
		time.addTime(hours);
	}
	public void attendClass(Course course, int hours, int testDay, Time time) {
		course.getProfessor().rapport++;
		if(testDay - time.getDayNum() <= 5 && testDay - time.getDayNum() > 0) {
			System.out.println("You attended " + course.name + " class, and the professor reminded you that your test is in " + (testDay - time.getDayNum()) + " days");
		}else {
			System.out.println("You attended " + course.name + " class.");
		}
		if(testDay == time.getDayNum()) {
			course.test((course.hours*4 + rand.nextInt(7)), 10);
		}
		course.attendence++;
		course.testReadiness+=hours;
		time.addTime(hours);
		System.out.println("Readiness: " + course.testReadiness);
	}
	public void attendClass(Course course, int hours, Time time) {
		course.getProfessor().rapport++;
		System.out.println("You attended " + course.name + " class.");
		time.addTime(hours);
		course.attendence++;
	}
	public void playVideoGames(int hours, Time time) {
		time.addTime(hours);
		studyEnergy+=(hours/3);
	}
	public void hangoutWithFriend(NPC npc, Time time) {
		int hours = rand.nextInt(4) + 1;
		studyEnergy+=hours/2;
		time.addTime(hours);
		npc.rapport++;
		System.out.println("You ended up hanging out with " + npc.name + " for " + hours + " hours.");
	}
	public void askForExtraCredit(Course course) {
		if(course.getProfessor().getRapport() >= course.prof.rapportReq) {
			System.out.println("Professor " + course.prof + " gave you extra credit.");
			course.currentPoints++;
			course.prof.rapport-=course.prof.rapportReq;
		}else {
			System.out.println("Professor " + course.prof + " did not give you extra credit.");
		}
	}
	public void checkGrades(Course course) {
		System.out.println(course.name.toUpperCase()+ ": " + course.grade);
	}

}
