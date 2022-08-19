
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.examples.data.countrydata.CountryBubbleMapApp.DataEntry;
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
import de.fhpotsdam.unfolding.data.MarkerFactory;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.MapPosition;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class PracticeProject extends PApplet {
	UnfoldingMap map1;

	List<Marker> countryMarkers;
	HashMap<String, DataEntry> dataEntriesMap;

	public void setup() {
		size(800, 600, OPENGL);
		smooth();
		// map1 = new UnfoldingMap(this, new Microsoft.AerialProvider());
		map1 = new UnfoldingMap(this, new Microsoft.AerialProvider());
		map1.zoomToLevel(2);
		map1.setBackgroundColor(240);
		/*
		 * map2 = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
		 * map3 = new UnfoldingMap(this, new Google.GoogleTerrainProvider());
		 */
		MapUtils.createDefaultEventDispatcher(this, map1);

		/*
		 * List<Feature> countries = GeoJSONReader.loadData(this, "countries.geo.json");
		 * MarkerFactory markerFactory = new MarkerFactory();
		 * markerFactory.setPointClass(MyPolygonMarker.class); List<Marker>
		 * countryMarkers = markerFactory.createMarkers(countries); MyPolygonMarker
		 * fMarker = new MyPolygonMarker();
		 * fMarker.addLocations(getFranceShapeLocations()); MyPolygonMarker cMarker =
		 * new MyPolygonMarker(); cMarker.addLocations(getCorsicaShapeLocations());
		 * MultiMarker multiMarker = new MultiMarker(); multiMarker.addMarkers(fMarker,
		 * cMarker); //map1.addMarkers(multiMarker);
		 */		
		List<Feature> countries = GeoJSONReader.loadData(this, "countries.geo.json");
		List<Marker> countryMarkers = MapUtils.createSimpleMarkers(countries);
		for (Marker marker : countryMarkers) {
			marker.setColor(color(255, 0, 0, 0));

	}
		map1.addMarkers(countryMarkers);
		
		//removeFill();
		

	}

	public void draw() {
		background(240);
		map1.draw();
	}

	public void mouseMoved() {
		// Not via marker.isInside(...) as this example supports both MultiMarker and
		// two markers.
		// multiMarker.isInside(map, mouseX, mouseY);

		Marker hitMarker = map1.getDefaultMarkerManager().getFirstHitMarker(mouseX, mouseY);
		if (hitMarker != null) {
			hitMarker.setSelected(true);
		} else {
			for (Marker marker : map1.getDefaultMarkerManager().getMarkers()) {
				marker.setSelected(false);
			}
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

// Very simple custom PolygonMarker. Extends Unfolding's SimplePolygonMarker to create own drawing methods.
	class MyPolygonMarker extends SimplePolygonMarker {

		public void draw(PGraphics pg, List<MapPosition> mapPositions) {
			pg.pushStyle();

			// Here you should do your custom drawing
			pg.strokeWeight(2);
			pg.stroke(0, 0, 0);
			pg.noFill();
			pg.beginShape();

			for (MapPosition mapPosition : mapPositions) {
				pg.vertex(mapPosition.x, mapPosition.y);
			}
			pg.endShape();

			pg.popStyle();
		}

	}
	
	

	public static List<Location> getFranceShapeLocations() {
		// Crude shape of France
		List<Location> franceLocations = new ArrayList<Location>();
		franceLocations.add(new Location(48.985985f, 8.173828f));
		franceLocations.add(new Location(51.074539f, 2.460938f));
		franceLocations.add(new Location(49.33085f, -0.043945f));
		franceLocations.add(new Location(48.522426f, -4.746094f));
		franceLocations.add(new Location(46.231533f, -1.054687f));
		franceLocations.add(new Location(43.427392f, -1.801758f));
		franceLocations.add(new Location(42.397499f, 3.208008f));
		franceLocations.add(new Location(43.682174f, 3.911133f));
		franceLocations.add(new Location(43.075308f, 6.28418f));
		franceLocations.add(new Location(43.935879f, 7.734375f));
		franceLocations.add(new Location(46.534681f, 6.064453f));
		return franceLocations;
	}

	public static List<Location> getCorsicaShapeLocations() {
		// Crude shape of Corsica
		List<Location> corsicaLocations = new ArrayList<Location>();
		corsicaLocations.add(new Location(41.380106f, 9.162598f));
		corsicaLocations.add(new Location(42.231771f, 8.547363f));
		corsicaLocations.add(new Location(42.991791f, 9.404297f));
		corsicaLocations.add(new Location(42.052556f, 9.558105f));
		return corsicaLocations;
	}

}
