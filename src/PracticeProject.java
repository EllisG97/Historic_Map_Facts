

import processing.core.PApplet;
import processing.core.PFont;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.examples.data.countrydata.CountryBubbleMapApp.DataEntry;
import de.fhpotsdam.unfolding.examples.marker.labelmarker.ManualLabelMarkerApp;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.marker.SimplePolygonMarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.GeoRSSReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.MapUtils;


public class PracticeProject extends PApplet {
    UnfoldingMap map1;
	/*
	 * UnfoldingMap map2; UnfoldingMap map3; UnfoldingMap currentMap;
	 */
    
    
    List<Marker> countryMarkers;
    HashMap<String, DataEntry> dataEntriesMap;
    
    public void setup() {
        size(800, 600, OPENGL);
        map1 = new UnfoldingMap(this, new Microsoft.AerialProvider());
		/*
		 * map2 = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
		 * map3 = new UnfoldingMap(this, new Google.GoogleTerrainProvider());
		 */
        MapUtils.createDefaultEventDispatcher(this, map1);
        
        SimplePointMarker marker = new SimplePointMarker(new Location(52.5, 13.4));
        marker.setSelected(true);

        // Add mouse and keyboard interactions
        //MapUtils.createDefaultEventDispatcher(this, currentMap);
        
		List<Feature> features = GeoRSSReader.loadData(this, "data/bbc-georss-test.xml");
		List<Marker> markers = createLabeledMarkers(features);
		map1.addMarkers(markers);
   
       
    }
 
    public void draw() {
       // currentMap.draw();
    	map1.draw();
    }
    
    
   	
   
   public void mouseMoved() {
       // Deselect all marker
       for (Marker marker : map1.getMarkers()) {
           marker.setSelected(false);
       }

       // Select hit marker
       Marker marker = map1.getFirstHitMarker(mouseX, mouseY);
       // NB: Use mm.getHitMarkers(x, y) for multi-selection.
       if (marker != null) {
           marker.setSelected(true);
       }
   }
   
   
   public List<Marker> createLabeledMarkers(List<Feature> features) {
       PFont font = loadFont("ui/OpenSans-12.vlw");
       List<Marker> markers = new ArrayList<Marker>();
       for (Feature feature : features) {
           String label = feature.getStringProperty("replacedle");
           PointFeature pointFeature = (PointFeature) feature;
           Marker marker = new LabeledMarker(pointFeature.getLocation(), label, font, 15);
           markers.add(marker);
       }
       return markers;
   }
   
   

   
   
}
    
 

