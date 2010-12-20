package com.tap.overlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.tap.TheAwesomeProject;

public class PointerOverlay extends Overlay {
	
	private String title;
	private String snippet;
	
	public PointerOverlay(){
		title = "Hej";
		snippet = "";
	}
		
	@Override
	public boolean onTap(GeoPoint p, MapView mapView){
		OverlayItem overlayItem = new OverlayItem(p, title, snippet);
		TheAwesomeProject.itemizedoverlay.addOverlay(overlayItem);
		return true;
	}
	
	

	
}


