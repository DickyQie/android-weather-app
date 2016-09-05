package com.ard.weather.entity;


public class UserRequest {
	private int showapi_res_code;
	private String showapi_res_error;
	private showapi_res_body showapi_res_body;
	public class showapi_res_body
	{
		private int confidence;
		private String level;
		private int precise; 
		private Location location;
		public class Location
		{
			private double lat;
			private double lng;
			public double getLat() {
				return lat;
			}
			public void setLat(double lat) {
				this.lat = lat;
			}
			public double getLng() {
				return lng;
			}
			public void setLng(double lng) {
				this.lng = lng;
			}
		}
		public int getConfidence() {
			return confidence;
		}
		public void setConfidence(int confidence) {
			this.confidence = confidence;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public int getPrecise() {
			return precise;
		}
		public void setPrecise(int precise) {
			this.precise = precise;
		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
	}
	public int getShowapi_res_code() {
		return showapi_res_code;
	}
	public void setShowapi_res_code(int showapi_res_code) {
		this.showapi_res_code = showapi_res_code;
	}
	public String getShowapi_res_error() {
		return showapi_res_error;
	}
	public void setShowapi_res_error(String showapi_res_error) {
		this.showapi_res_error = showapi_res_error;
	}
	public showapi_res_body getShowapi_res_body() {
		return showapi_res_body;
	}
	public void setShowapi_res_body(showapi_res_body showapi_res_body) {
		this.showapi_res_body = showapi_res_body;
	}

}
