
import java.util.Arrays;

public class CountryJson {

	private int id;
	private String name;
	private int population;
	private String capital;
	private String fact;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		}
	
	public int getPopulation(){
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public String getCapital() {
		return capital;
	}
	
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	public String getFact() {
		return fact;
	}
	
	public void setFact(String fact) {
		this.fact = fact;
	}
	

	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("***** Employee Details *****\n");
		sb.append("ID="+getId()+"\n");
		sb.append("Name="+getName()+"\n");
		sb.append("Population="+getPopulation()+"\n");
		sb.append("Capital="+getCapital()+"\n");
		sb.append("Fact="+getFact());
		sb.append("\n*****************************");
		
		return sb.toString();
	}
	
	
	
}
