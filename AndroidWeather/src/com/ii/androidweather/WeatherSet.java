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

	public WeatherSet(){}
	private static WeatherSet instance ;
	public static WeatherSet getInstance (){
	if(instance==null)
	{instance =new WeatherSet();
		
	}
		return  instance;	
	};
	private WeatherForecastInformationVO myForecastInfromation=null;
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

	public WeatherForecastInformationVO getMyForecastInfromation() {
		return myForecastInfromation;
	}

	public void setMyForecastInfromation(
			WeatherForecastInformationVO myForecastInfromation) {
		this.myForecastInfromation = myForecastInfromation;
	}

	public ArrayList<WeatherForecastConditionVO> getWeatherForecastConditions() {
		return this.myForecastConditions;
	}

	public WeatherForecastConditionVO getLastWeatherForecastCondition() {
		return this.myForecastConditions
				.get(this.myForecastConditions.size() - 1);
	}
}
