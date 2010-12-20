package com.tap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import com.tap.overlay.*;


public class TheAwesomeProject extends MapActivity {
	/*
	 * TODO Move the MapCoordinateOverlay class to a separate class
	 * 		Fixing the menu (Could be coz. of MapActivity)
	 * 		Better documentation
	 * 
	 * 		Features:
	 * 		Overlay for address
	 * 		Change pointer on map
	 */

	MapView mapView;
	MapController mapController;
	public static MapItemizedOverlay itemizedoverlay;
	List<Overlay> mapOverlays;
    OverlayItem overlayitem;

	private int zoom = 8;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		

		/*
		 * List of all the overlays which will be drawn on the map
		 */
		mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedoverlay = new MapItemizedOverlay(drawable);

		/*
		 * A specific point on the map
		 */
		GeoPoint point = new GeoPoint(57700000,11900000);
		overlayitem = new OverlayItem(point, "Hi", "Waddup");
		

		/*
		 * Control the map behavior in various ways
		 */
		mapController = mapView.getController();
		mapController.animateTo(point);
		mapController.setZoom(zoom);

		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_view, menu);
		return true;
	}

	//Handler for items in menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_streetView:
			mapView.setStreetView(true);
			return true;
		case R.id.menu_satelliteView:
			mapView.setSatellite(false);
			return true;
		case R.id.menu_addPoint:
			PointerOverlay pointer = new PointerOverlay();
			mapOverlays.add(pointer);
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	

	



}

