import java.util.HashMap;
import java.util.List;

import processing.core.PFont;
import processing.core.PGraphics;

import processing.core.PApplet;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePolygonMarker;
import de.fhpotsdam.unfolding.utils.MapPosition;



public class CountryFacts extends SimplePolygonMarker {
	
	
	public CountryFacts(List<Location> locations) {
		super(locations);
	}
	
	protected void draw(PGraphics pg, List<MapPosition> mapPositions, HashMap<String, Object> properties,
			UnfoldingMap map) {

			if (getId() != null && selected) {
				pg.pushStyle();

				
				pg.noFill();
				pg.stroke(200);
				pg.strokeWeight(1);
				pg.rect(1000, 250, 350, 600);
				
				String country = getStringProperty("name");
				//String population = getStringProperty("population");
				//String capital = getStringProperty("capital");
				//String fact = getStringProperty("fact");
				
				pg.text("Country: " + country, 1010, 285);
				pg.text("Population: " + " 1,131,796", 1010, 310);
				pg.text("Capital: " + " Tallinn", 1010, 335);
				pg.text("Did You Know: " + " Lorem Ipsum", 1010, 400);
				
				
				pg.popStyle();
				
			
			
		}
	}
	

}
