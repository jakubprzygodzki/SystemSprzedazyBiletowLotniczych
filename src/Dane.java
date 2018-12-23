import java.util.LinkedList;

public class Dane {
	
	public String inputFromCity;
	public String inputToCity;
	public String departure;
	public String arrival;
	public String IDLotu;
    public String date;
    public String uluru;


	
	public void setSkad(String inputFromCity) {
		this.inputFromCity = inputFromCity;
	}
	public void setDokad(String inputToCity) {
		this.inputToCity = inputToCity;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public void setIDLotu(String IDLotu) {
		this.IDLotu = IDLotu;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setUluru(String uluru) {
		this.uluru = uluru;
	}
	
	
	public String getSkad() {
		return this.inputFromCity;
	}	
	public String getDokad() {
		return this.inputToCity;
	}
	public String getOdlot() {
		return this.departure;
	}
	public String getIDLotu() {
		return this.IDLotu;
	}
	public String getArrival() {
		return this.arrival;
	}
	public String getDate() {
		return this.date;
	}
	public String getUluru() {
		return this.uluru;
	}

}
