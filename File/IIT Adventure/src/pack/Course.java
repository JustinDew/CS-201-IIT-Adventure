package pack;

public class Course {
	double grade;
	String name;
	double currentPoints, totalPoints, totalClasses;
	int testReadiness, hours, attendence;
	Professor prof;
	public Course(String name, String profName, int rapportReq, int hours) {
		testReadiness = 0;
		prof = new Professor(rapportReq, profName);
		this.hours = hours;
		this.name = name;
	}
	//method for having a test. Calculates score based on how many hours the student has studied
	//versus how many hours of studying the test requires
	public void test(int studyReq, int points) {
		double score = 0;
		totalPoints+=points;
		if(testReadiness >= studyReq) {
			score = points;
			currentPoints+=points;
		}
		else {
			currentPoints+=points - (studyReq - testReadiness);
			score = points - (studyReq - testReadiness);
		}
		
		grade = currentPoints/totalPoints;
		if(grade < 0) {
			grade = 0;
		}

		System.out.println("You took the test today and got " + ((score/points) * 100) + "%");
		System.out.println("hours needed: " + studyReq + "\nhours Studied: " + testReadiness);
		testReadiness = 0;
	}
	//returns the professor of the class
	public Professor getProfessor() {
		return prof;
	}
}
