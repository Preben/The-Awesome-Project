package com.tap.overlay;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapCoordinateOverlay extends Overlay {

	Context mapContext;
	
	public MapCoordinateOverlay(Context mapContext) {
		this.mapContext = mapContext;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) 
	{   
		if (event.getAction() == MotionEvent.ACTION_UP) {                
			GeoPoint p = mapView.getProjection().fromPixels(
					(int) event.getX(),
					(int) event.getY());
			Toast.makeText(mapContext, 
					p.getLatitudeE6() / 1E6 + "," + 
					p.getLongitudeE6() /1E6 , 
					Toast.LENGTH_SHORT).show();
		}                            
		return false;
	}

	
	
	/*
	 * Move this to seperate class
	 * TODO Add isPresent() (check if geocoder is present on plattform)
	 * DOESNT work on the 2.2 AVD, but should work on an android
	 */
//	class MapCoordinateOverlayAdress extends Overlay {
//
//		public MapCoordinateOverlayAdress() {
//
//		}
//
//		@Override
//		public boolean onTouchEvent(MotionEvent event, MapView mapView){
//
//			if(event.getAction() == MotionEvent.ACTION_UP){
//				GeoPoint p = mapView.getProjection().fromPixels(
//						(int) event.getX(), 
//						(int) event.getY());
//
//				Geocoder geoCoder = new Geocoder(getBaseContext(),Locale.getDefault());
//
//				try{
//					List<Address> addresses = geoCoder.getFromLocation(
//							p.getLatitudeE6()  / 1E6, 
//							p.getLongitudeE6() / 1E6, 1);
//
//					String add = "";
//					if (addresses.size() > 0) 
//					{
//						for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); 
//						i++)
//							add += addresses.get(0).getAddressLine(i) + "\n";
//					}
//
//					Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
//				}
//				catch (IOException e) {                
//					e.printStackTrace();
//				}   
//				return true;
//
//			} else
//				return false;
//		}

	}



