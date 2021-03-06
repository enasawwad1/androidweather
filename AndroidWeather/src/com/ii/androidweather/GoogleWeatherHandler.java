package com.ii.androidweather;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GoogleWeatherHandler extends DefaultHandler {

	// ===========================================================
	// Fields
	// ===========================================================
	private static  GoogleWeatherHandler instance=null;
	public static GoogleWeatherHandler getInstance (){
	if(instance==null)
	{instance =new GoogleWeatherHandler();
		
	}
		return  instance;	
	};
	private  WeatherSet myWeatherSet = null;
	private boolean in_forecast_information = false;
	private boolean in_current_conditions = false;
	private boolean in_forecast_conditions = false;

	private boolean formatSI = false; // false means Fahrenheit

	public WeatherSet getWeatherSet() {
		return this.myWeatherSet;
	}

	// Methods
	
	@Override
	public void startDocument() throws SAXException {
		this.myWeatherSet = new WeatherSet();
	}

	@Override
	public void endDocument() throws SAXException {
		// Nothing
	}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		// 'Outer' Tags
		if (localName.equals("forecast_information")) {
			this.myWeatherSet.setMyForecastInfromation(new WeatherForecastInformationVO());
			this.in_forecast_information = true;
		} else if (localName.equals("current_conditions")) {
			this.myWeatherSet
					.setWeatherCurrentCondition(new WeatherCurrentConditionVO());
			this.in_current_conditions = true;
		} else if (localName.equals("forecast_conditions")) {
			this.myWeatherSet.getWeatherForecastConditions().add(
					new WeatherForecastConditionVO());
			this.in_forecast_conditions = true;
		} else {
			String dataAttribute = atts.getValue("data");
			// 'Inner' Tags of "<forecast_information>"
			if (localName.equals("city")) {
			this.myWeatherSet.getMyForecastInfromation().setCity(dataAttribute);	
			} else if (localName.equals("postal_code")) {
				this.myWeatherSet.getMyForecastInfromation().setPostal_code(dataAttribute);
			} else if (localName.equals("latitude_e6")) {
				this.myWeatherSet.getMyForecastInfromation().setLatitude_e6(dataAttribute);
				/* One could use this to convert city-name to Lat/Long. */
			} else if (localName.equals("longitude_e6")) {
				this.myWeatherSet.getMyForecastInfromation().setLongitude_e6(dataAttribute);
				/* One could use this to convert city-name to Lat/Long. */
			} else if (localName.equals("forecast_date")) {
				this.myWeatherSet.getMyForecastInfromation().setForecast_date(dataAttribute);
			} else if (localName.equals("current_date_time")) {
				this.myWeatherSet.getMyForecastInfromation().setCurrent_date_time(dataAttribute);
			} else if (localName.equals("unit_system")) {
				this.myWeatherSet.getMyForecastInfromation().setUnit_system(dataAttribute);
				if (dataAttribute.equals("SI"))
					this.formatSI = true;
			}
			// SHARED(!) 'Inner' Tags within "<current_conditions>" AND
			// "<forecast_conditions>"
			else if (localName.equals("day_of_week")) {
				if (this.in_current_conditions) {
					this.myWeatherSet.getWeatherCurrentCondition()
							.setDayofWeek(dataAttribute);
				} else if (this.in_forecast_conditions) {
					this.myWeatherSet.getLastWeatherForecastCondition()
							.setDayofWeek(dataAttribute);
				}
			} else if (localName.equals("icon")) {
				if (this.in_current_conditions) {
					this.myWeatherSet.getWeatherCurrentCondition().setIconURL(
							dataAttribute);
				} else if (this.in_forecast_conditions) {
					this.myWeatherSet.getLastWeatherForecastCondition()
							.setIconURL(dataAttribute);
				}
			} else if (localName.equals("condition")) {
				if (this.in_current_conditions) {
					this.myWeatherSet.getWeatherCurrentCondition()
							.setCondition(dataAttribute);
				} else if (this.in_forecast_conditions) {
					this.myWeatherSet.getLastWeatherForecastCondition()
							.setCondition(dataAttribute);
				}
			}
			// 'Inner' Tags within "<current_conditions>"
			else if (localName.equals("temp_f")) {
				this.myWeatherSet.getWeatherCurrentCondition()
						.setTempFahrenheit(Integer.parseInt(dataAttribute));
			} else if (localName.equals("temp_c")) {
				this.myWeatherSet.getWeatherCurrentCondition().setTempCelcius(
						Integer.parseInt(dataAttribute));
			} else if (localName.equals("humidity")) {
				this.myWeatherSet.getWeatherCurrentCondition().setHumidity(
						dataAttribute.split(":")[1]);
			} else if (localName.equals("wind_condition")) {
				this.myWeatherSet.getWeatherCurrentCondition()
						.setWindCondition(dataAttribute.split(":")[1]);
			}
			// 'Inner' Tags within "<forecast_conditions>"
			else if (localName.equals("low")) {
				int temp = Integer.parseInt(dataAttribute);
				if (this.formatSI) {
					this.myWeatherSet.getLastWeatherForecastCondition()
							.setTempMinCelsius(temp);
				} else {
					this.myWeatherSet.getLastWeatherForecastCondition()
							.setTempMinCelsius(
									WeatherUtils.fahrenheitToCelsius(temp));
				}
			} else if (localName.equals("high")) {
				int temp = Integer.parseInt(dataAttribute);
				if (this.formatSI) {
					this.myWeatherSet.getLastWeatherForecastCondition()
							.setTempMaxCelsius(temp);
				} else {
					this.myWeatherSet.getLastWeatherForecastCondition()
							.setTempMaxCelsius(
									WeatherUtils.fahrenheitToCelsius(temp));
				}
			}
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (localName.equals("forecast_information")) {
			this.in_forecast_information = false;
		} else if (localName.equals("current_conditions")) {
			this.in_current_conditions = false;
		} else if (localName.equals("forecast_conditions")) {
			this.in_forecast_conditions = false;
		}
	}

	@Override
	public void characters(char ch[], int start, int length) {
		/*
		 * Would be called on the following structure: <element>characters</element>
		 */
	}
}
