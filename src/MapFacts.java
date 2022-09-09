
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.data.JSONObject;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.examples.data.countrydata.CountryBubbleMapApp.DataEntry;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.events.EventDispatcher;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.GeoRSSReader;
import de.fhpotsdam.unfolding.data.MarkerFactory;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.utils.DebugDisplay;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import de.fhpotsdam.unfolding.utils.GeoUtils;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;

public class MapFacts extends PApplet {
	UnfoldingMap map1;
	UnfoldingMap mapOverviewStatic;
	UnfoldingMap mapOverview;
	DebugDisplay debugDisplay;

	List<Marker> countryMarkers;
	HashMap<String, DataEntry> dataEntriesMap;
	
	PFont font;

	public void setup() {
		size(1400, 865, OPENGL);
		smooth();
		font = createFont("sans-serif", 14);
		
		
		// map1 = new UnfoldingMap(this, new Microsoft.AerialProvider());
		map1 = new UnfoldingMap(this,  10, 10, 950, 850, new Microsoft.AerialProvider());
		map1.zoomToLevel(3);
		map1.setZoomRange(3, 10);
		map1.setTweening(true);
		EventDispatcher eventDispatcher = MapUtils.createDefaultEventDispatcher(this, map1);
		
		mapOverview = new UnfoldingMap(this, 1050, 10, 185, 185, new Microsoft.AerialProvider());
		mapOverview.zoomToLevel(1);
		mapOverview.setZoomRange(1, 7);
		mapOverview.setTweening(true);
		eventDispatcher.register(mapOverview, "pan", map1.getId());
		eventDispatcher.register(mapOverview, "zoom", map1.getId());
		/*
		 * map2 = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
		 * map3 = new UnfoldingMap(this, new Google.GoogleTerrainProvider());
		 */
		MapUtils.createDefaultEventDispatcher(this, map1);


		List<Feature> countries = GeoJSONReader.loadData(this, "countries.geo.json");

		MarkerFactory markerFactory = new MarkerFactory();
		markerFactory.setPolygonClass(CustomPolygonMarker.class);
		//List<Marker> markers = createLabeledMarkers(countries);
		List<Marker> countryMarkers = markerFactory.createMarkers(countries);


		map1.addMarkers(countryMarkers);
		for (Marker marker : countryMarkers) {
			marker.setColor(color(255, 0, 0, 0));

	}
		map1.addMarkers(countryMarkers);
		//map1.addMarkers(markers);
		setBackground(Color.BLACK);
		background(color(181, 101, 29)); 
		
		
		
		
		debugDisplay = new DebugDisplay(this, map1);

	}

	public void draw() {
		background(0);
		map1.draw();
		mapOverview.draw();
		List<Feature> countries = GeoJSONReader.loadData(this, "countries.geo.json");
		
		CountryFacts countryFacts = new CountryFacts();
		countryFacts.draw(countries);
		
		ScreenPosition tl1 = mapOverview.getScreenPosition(map1.getTopLeftBorder());
		ScreenPosition br1 = mapOverview.getScreenPosition(map1.getBottomRightBorder());
		drawDetailSelectionBox(tl1, br1);

		createFactBox(countries);
		debugDisplay.draw();
	}
	

	public void mouseMoved() {
		// Deselect all marker
		for (Marker marker : map1.getMarkers()) {
			//marker.setSelected(false);
		}
		// Select hit marker
		Marker marker = map1.getFirstHitMarker(mouseX, mouseY);
		if (marker != null) {
			//marker.setSelected(true);
		}
	}

	public void mouseClicked() {
		Marker marker = map1.getFirstHitMarker(mouseX, mouseY);
		if (marker != null) {
			//map1.zoomAndPanToFit(GeoUtils.getLocations(marker));
			for (Marker m : map1.getMarkers()) {
				m.setSelected(false);
			}
			marker.setSelected(true);
			
		} else {
			map1.zoomAndPanTo(2, new Location(0, 0));
		}
	}
	
	
	
	public void drawDetailSelectionBox(ScreenPosition tl, ScreenPosition br) {
		noFill();
		stroke(251, 114, 0, 240);
		float w = br.x - tl.x;
		float h = br.y - tl.y;
		rect(tl.x, tl.y, w, h);
	}
	
	
	public List<Marker> createLabeledMarkers(List<Feature> countries) {
		PFont font = loadFont("ui/OpenSans-12.vlw");
		List<Marker> markers = new ArrayList<Marker>();
		for (Feature feature : countries) {
			String label = feature.getStringProperty("name");
			ShapeFeature pointFeature = (ShapeFeature) feature;
			Marker marker = new CustomLabeledPolygonMarker(pointFeature.getLocations(), label, font, 15);
			markers.add(marker);
		}
		return markers;
	}
	
	
	public void createFactBox(List<Feature> countries) {

		textFont(font);
		textSize(20);

		//String country = getStringProperty("name");

		// Simple Berlin button
		noFill();
		stroke(200);
		strokeWeight(1);
		rect(1000, 250, 350, 600);
		//text("Country: " + " Estonia", 1010, 285);
		text("Population: " + " 1,131,796", 1010, 310);
		text("Capital: " + " Tallinn", 1010, 335);
		text("Did You Know: " + " Lorem Ipsum", 1010, 400);


	}

}
