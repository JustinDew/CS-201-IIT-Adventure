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

		System.out.println("You took the test today and got " + ((score/points) * 100) + "%");
		System.out.println("hours needed: " + studyReq + "\nhours Studied: " + testReadiness);
		testReadiness = 0;
	}
	public Professor getProfessor() {
		return prof;
	}
}
