import processing.core.PApplet;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;

import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.utils.MapUtils;


public class PracticeProject extends PApplet {
    UnfoldingMap map1;
    UnfoldingMap map2;
    UnfoldingMap map3;
    UnfoldingMap currentMap;
    
    public void setup() {
        size(800, 600);
        map1 = new UnfoldingMap(this, new Microsoft.AerialProvider());
        map2 = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
        map3 = new UnfoldingMap(this, new Google.GoogleTerrainProvider());
        MapUtils.createDefaultEventDispatcher(this, map1, map2, map3);
     
        currentMap = map1;

        
        // Show map around the location in the given zoom level.
        //map.zoomAndPanTo(new Location(54.544540f, -1.927440f), 20);
        
        //Add Pan & Zoom Animations
       //map.setTweening(true);
 
        // Add mouse and keyboard interactions
        MapUtils.createDefaultEventDispatcher(this, currentMap);
        
		/*
		 * //Adding markers List<Feature> countries = GeoJSONReader.loadData(this,
		 * "countries.geo.json"); List<Marker> countryMarkers =
		 * MapUtils.createSimpleMarkers(countries); map1.addMarkers(countryMarkers);
		 */
       
       
    }
 
    public void draw() {
        currentMap.draw();
    }
    
    
   public void keyPressed() {
        if (key == '1') {
            currentMap = map1;
        } else if (key == '2') {
            currentMap = map2;
        } else if (key == '3') {
            currentMap = map3;
        }
    
   	}
}
    
 

