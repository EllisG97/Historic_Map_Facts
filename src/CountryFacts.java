import java.util.HashMap;
import java.util.List;

import processing.core.PFont;
import processing.core.PGraphics;

import processing.core.PApplet;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePolygonMarker;
import de.fhpotsdam.unfolding.utils.MapPosition;



public class CountryFacts extends SimplePolygonMarker {
	
	
	PFont font;
	

	public void draw(List<Feature> countries) {

		PGraphics pg = new PGraphics();
//		PFont fonts = new PFont();
//
//		fonts.getNative();
//		fonts.getSize();
		
		
		pg.noFill();
		pg.stroke(200);
		pg.strokeWeight(1);
		pg.rect(1000, 250, 350, 600);
		
		
			if (getId() != null && selected) {
				
				pg.pushStyle();
				
				String country = getStringProperty("name");
				System.out.println(country);
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
