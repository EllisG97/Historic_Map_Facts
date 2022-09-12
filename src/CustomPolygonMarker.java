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

/**
 * Displays the polygon ID as label at the geometric center of its shape. For this, it uses the geo-spatial centroid of
 * the polygon, and converts it to object coordinates, to be consistent to other marker draw methods.
 */
public class CustomPolygonMarker extends SimplePolygonMarker {
	
	PFont font;
	PApplet app;

	public CustomPolygonMarker(List<Location> locations) {
		super(locations);
	}


	// Overrides the method with map, as we need to convert the centroid location ourself.
	@Override
	protected void draw(PGraphics pg, List<MapPosition> mapPositions, HashMap<String, Object> properties,
			UnfoldingMap map) {

		// Polygon shape is drawn by the SimplePolygonMarker
		super.draw(pg, mapPositions, properties, map);
		String name = " ";
		String population = " ";
		String capital = " ";
		String fact = " ";

		// Draws the country code at the centroid of the polygon 

		
		
		if (getId() != null && selected) {
			pg.pushStyle();
			// Gets geometric center as geo-location
			Location centerLocation = getCentroid();
			// Converts geo-location to position on the map (NB: Not the screen!)
			float[] xy = map.mapDisplay.getObjectFromLocation(centerLocation);
			int x = Math.round(xy[0] - pg.textWidth(getId()) / 2);
			int y = Math.round(xy[1] + 6);
			// Draws label
			pg.noFill();
			pg.text(getStringProperty("name"), x, y);
			pg.popStyle();
			
			
			pg.pushStyle();
			
			pg.fill(255, 255, 255);
			pg.textSize(20);
			name = getStringProperty("name");
			population = getStringProperty("population");
			capital = getStringProperty("capital");
			fact = getStringProperty("fact");
			
			pg.popStyle();
			
		}	
		
	
		
		pg.pushStyle();
		pg.fill(255);
		//pg.textFont(font);
		pg.textSize(20);
		pg.text("Country: " + name, 1010, 285);
		pg.text("Population: " + population, 1010, 310);
		pg.text("Capital: " + capital, 1010, 335);
		pg.text("Did You Know: " + fact, 1010, 400, 280, 320);
		pg.popStyle();
		
		
	}
	
	public  String countryName() {
		if (getId() != null && selected) {
			return getStringProperty("name");
		}
		return " ";
	}
	
}
