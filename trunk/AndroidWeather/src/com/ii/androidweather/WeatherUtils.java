package com.ii.androidweather;

public class WeatherUtils {
	public static String url ="http://maps.google.com/maps/api/geocode/xml?latlng="; 
	public static final String SERVICE_ENDPOINT="http://api.wunderground." +
	"com/auto/wui/geo/GeoLookupXML/index.xml?query=";
	public static int fahrenheitToCelsius(int tFahrenheit) {
		return (int) ((5.0f / 9.0f) * (tFahrenheit - 32));
	}

	public static int celsiusToFahrenheit(int tCelsius) {
		return (int) ((9.0f / 5.0f) * tCelsius + 32);
	}
}
