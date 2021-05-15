package pack;
import java.util.Random;

public class NPC extends Student{
	int rapport = 0, rapportReq;
	boolean isFriend = false;
	public NPC(String name, String major, String housing, Player player) {
		
		Random rand = new Random();
		this.name = name;
		this.major = major;
		this.housing = housing;
		rapportReq = rand.nextInt(3) + 3;
		
		if(player.housing.equals(housing)) {
			rapport++;
		}
		if(player.major.equals(major)) {
			rapport++;
		}
		
	}
	public void raiseRapport() {
		rapport++;
	}
	public void lowerRapport() {
		rapport--;
	}
	public void setRapportReq(int num) {
		rapportReq = num;
	}
	public boolean rapportCheck() {
		if(rapport >= rapportReq) {
			isFriend = true;
			return true;
		}else {
			return false;
		}
	}
	public int getRapportReq() {
		return rapportReq;
	}
}
