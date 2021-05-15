package pack;

public class Professor {
	int rapport = 0, rapportReq;
	String name;
	boolean likesPlayer = false;
	public Professor(int rapportReq, String name) {
		this.name = name;
		this.rapportReq = rapportReq;
	}
	public int getRapport() {
		return rapport;
	}
	public void raiseRapport() {
		rapport++;
		if(rapport >= rapportReq) {
			likesPlayer = true;
		}else {
			likesPlayer = false;
		}
	}
	public String getName() {
		return name;
	}
}
