import processing.core.PApplet;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.MapUtils;


public class PracticeProject extends PApplet {
    UnfoldingMap map;
    
    public void setup() {
        size(800, 600);
        map = new UnfoldingMap(this, new Microsoft.AerialProvider());
        MapUtils.createDefaultEventDispatcher(this, map);
    }
 
    public void draw() {
        map.draw();
    }
}
    
 

