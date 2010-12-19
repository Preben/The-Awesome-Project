package com.tap;

import java.util.List;

import android.graphics.drawable.Drawable;
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

	private int zoom = 5;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		/*
		 * List of all the overlays which will be drawn on the map
		 */
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		MapItemizedOverlay itemizedoverlay = new MapItemizedOverlay(drawable);

		/*
		 * A specific point on the map
		 */
		GeoPoint point = new GeoPoint(57700000,11900000);
		OverlayItem overlayitem = new OverlayItem(point, "Hi", "Waddup");

		/*
		 * Control the map behavior in various ways
		 */
		mapController = mapView.getController();
		mapController.animateTo(point);
		mapController.setZoom(zoom);

		MapCoordinateOverlay m = new MapCoordinateOverlay();

		itemizedoverlay.addOverlay(overlayitem);
		//		mapOverlays.add(itemizedoverlay);
		mapOverlays.add(m);
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
			System.out.println("street");
			mapView.setStreetView(true);
			return true;
		case R.id.menu_satelliteView:
			System.out.println("satellite");
			mapView.setSatellite(true);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


	class MapCoordinateOverlay extends Overlay {

		public MapCoordinateOverlay() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) 
		{   
			if (event.getAction() == MotionEvent.ACTION_UP) {                
				GeoPoint p = mapView.getProjection().fromPixels(
						(int) event.getX(),
						(int) event.getY());
				Toast.makeText(getBaseContext(), 
						p.getLatitudeE6() / 1E6 + "," + 
						p.getLongitudeE6() /1E6 , 
						Toast.LENGTH_SHORT).show();
			}                            
			return false;
		}


	}


}

