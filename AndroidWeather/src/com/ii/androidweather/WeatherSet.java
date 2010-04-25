package com.ii.androidweather;

import java.util.ArrayList;

/**
 * Combines one WeatherCurrentCondition with a List of
 * WeatherForecastConditions.
 */
public class WeatherSet {
	
	// ===========================================================
	// Fields
	// ===========================================================

	private WeatherCurrentConditionVO myCurrentCondition = null;
	private ArrayList<WeatherForecastConditionVO> myForecastConditions = 
		new ArrayList<WeatherForecastConditionVO>(4);

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public WeatherCurrentConditionVO getWeatherCurrentCondition() {
		return myCurrentCondition;
	}

	public void setWeatherCurrentCondition(
			WeatherCurrentConditionVO myCurrentWeather) {
		this.myCurrentCondition = myCurrentWeather;
	}

	public ArrayList<WeatherForecastConditionVO> getWeatherForecastConditions() {
		return this.myForecastConditions;
	}

	public WeatherForecastConditionVO getLastWeatherForecastCondition() {
		return this.myForecastConditions
				.get(this.myForecastConditions.size() - 1);
	}
}
