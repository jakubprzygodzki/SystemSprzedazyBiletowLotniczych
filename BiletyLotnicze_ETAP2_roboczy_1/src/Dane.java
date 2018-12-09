import java.util.LinkedList;

public class Dane {
	
	public String inputFromCity;
	public String inputToCity;
	public String odlot;
	public String IDLotu;

	
	public void setSkad(String inputFromCity) {
		this.inputFromCity = inputFromCity;
	}
	public void setDokad(String inputToCity) {
		this.inputToCity = inputToCity;
	}
	public void setOdlot(String odlot) {
		this.odlot = odlot;
	}
	public void setIDLotu(String IDLotu) {
		this.IDLotu = IDLotu;
	}
	
	
	public String getSkad() {
		return this.inputFromCity;
	}	
	public String getDokad() {
		return this.inputToCity;
	}
	public String getOdlot() {
		return this.odlot;
	}
	public String getIDLotu() {
		return this.IDLotu;
	}

}
