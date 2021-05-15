package pack;

public class Professor {
	int rapport = 0, rapportReq;
	String name;
	boolean likesPlayer = false;
	public Professor(int rapportReq, String name) {
		this.name = name;
		this.rapportReq = rapportReq;
	}
	//returns current rapport
	public int getRapport() {
		return rapport;
	}
	//raises rapport
	public void raiseRapport() {
		rapport++;
		if(rapport >= rapportReq) {
			likesPlayer = true;
		}else {
			likesPlayer = false;
		}
	}
	//returns professor's name
	public String getName() {
		return name;
	}
}
