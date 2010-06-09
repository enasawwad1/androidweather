package com.ii.androidweather;

public class WeatherForecastInformationVO {
	// ===========================================================
	// Fields
	// ===========================================================

	private String city = null;
	private String postal_code = null;
	private String latitude_e6 = null;
	private String 	longitude_e6 = null;
	private String forecast_date = null;
	private String current_date_time = null;
	private String unit_system = null;
	private boolean formatSI = false; // false means Fahrenheit

	// ===========================================================
	// Constructors
	// ===========================================================

	public boolean isFormatSI() {
		return formatSI;
	}

	public void setFormatSI(boolean formatSI) {
		this.formatSI = formatSI;
	}

	public WeatherForecastInformationVO() {

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postalCode) {
		postal_code = postalCode;
	}

	public String getLatitude_e6() {
		return latitude_e6;
	}

	public void setLatitude_e6(String latitudeE6) {
		latitude_e6 = latitudeE6;
	}

	public String getLongitude_e6() {
		return longitude_e6;
	}

	public void setLongitude_e6(String longitudeE6) {
		longitude_e6 = longitudeE6;
	}

	public String getForecast_date() {
		return forecast_date;
	}

	public void setForecast_date(String forecastDate) {
		forecast_date = forecastDate;
	}

	public String getCurrent_date_time() {
		return current_date_time;
	}

	public void setCurrent_date_time(String currentDateTime) {
		current_date_time = currentDateTime;
	}

	public String getUnit_system() {
		return unit_system;
	}

	public void setUnit_system(String unitSystem) {
		unit_system = unitSystem;
	}


}
