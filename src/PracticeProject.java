
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.examples.data.countrydata.CountryBubbleMapApp.DataEntry;
import de.fhpotsdam.unfolding.examples.marker.advanced.centroid.CentroidLabelMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.events.EventDispatcher;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.MarkerFactory;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;

public class PracticeProject extends PApplet {
	UnfoldingMap map1;
	UnfoldingMap mapOverviewStatic;
	UnfoldingMap mapOverview;

	List<Marker> countryMarkers;
	HashMap<String, DataEntry> dataEntriesMap;

	public void setup() {
		size(1280, 960, OPENGL);
		smooth();
		// map1 = new UnfoldingMap(this, new Microsoft.AerialProvider());
		map1 = new UnfoldingMap(this, 10, 10, 1025, 1020, new Microsoft.AerialProvider());
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
		markerFactory.setPolygonClass(LabeledMarker.class);
		List<Marker> countryMarkers = markerFactory.createMarkers(countries);

		map1.addMarkers(countryMarkers);
		for (Marker marker : countryMarkers) {
			marker.setColor(color(255, 0, 0, 0));

	}
		map1.addMarkers(countryMarkers);
		setBackground(Color.BLACK);
		background(color(181, 101, 29)); 

	}

	public void draw() {
		background(240);
		map1.draw();
		mapOverview.draw();
		ScreenPosition tl1 = mapOverview.getScreenPosition(map1.getTopLeftBorder());
		ScreenPosition br1 = mapOverview.getScreenPosition(map1.getBottomRightBorder());
		drawDetailSelectionBox(tl1, br1);
	}

	public void mouseMoved() {
		// Deselect all marker
		for (Marker marker : map1.getMarkers()) {
			marker.setSelected(false);
		}

		// Select hit marker
		Marker marker = map1.getFirstHitMarker(mouseX, mouseY);
		if (marker != null) {
			marker.setSelected(true);
		}
	}

	public void drawDetailSelectionBox(ScreenPosition tl, ScreenPosition br) {
		noFill();
		stroke(251, 114, 0, 240);
		float w = br.x - tl.x;
		float h = br.y - tl.y;
		rect(tl.x, tl.y, w, h);
	}

}
