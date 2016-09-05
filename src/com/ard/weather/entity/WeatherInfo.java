package com.ard.weather.entity;

public class WeatherInfo {
	
	private int showapi_res_code;
	private String showapi_res_error;
	private ResbodyInfo showapi_res_body;
			public class ResbodyInfo{
				private int ret_code;
				private String time;
				private CityInfo cityInfo;
				private FInfo f1;
				private FInfoInfo f2;
				private FInfoInfo f3;
				private FInfoInfo f4;
				private FInfoInfo f5;
				private FInfoInfo f6;
				private FInfoInfo f7;
				private UserInfo now;
				public int getRet_code() {
					return ret_code;
				}
				public void setRet_code(int ret_code) {
					this.ret_code = ret_code;
				}
				public String getTime() {
					return time;
				}
				public void setTime(String time) {
					this.time = time;
				}
				public CityInfo getCityInfo() {
					return cityInfo;
				}
				public void setCityInfo(CityInfo cityInfo) {
					this.cityInfo = cityInfo;
				}
				public FInfo getF1() {
					return f1;
				}
				public void setF1(FInfo f1) {
					this.f1 = f1;
				}
				public FInfoInfo getF2() {
					return f2;
				}
				public void setF2(FInfoInfo f2) {
					this.f2 = f2;
				}
				public FInfoInfo getF3() {
					return f3;
				}
				public void setF3(FInfoInfo f3) {
					this.f3 = f3;
				}
				public FInfoInfo getF4() {
					return f4;
				}
				public void setF4(FInfoInfo f4) {
					this.f4 = f4;
				}
				public FInfoInfo getF5() {
					return f5;
				}
				public void setF5(FInfoInfo f5) {
					this.f5 = f5;
				}
				public FInfoInfo getF6() {
					return f6;
				}
				public void setF6(FInfoInfo f6) {
					this.f6 = f6;
				}
				public FInfoInfo getF7() {
					return f7;
				}
				public void setF7(FInfoInfo f7) {
					this.f7 = f7;
				}
				public UserInfo getNow() {
					return now;
				}
				public void setNow(UserInfo now) {
					this.now = now;
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
		    public ResbodyInfo getShowapi_res_body() {
				return showapi_res_body;
			}
			public void setShowapi_res_body(ResbodyInfo showapi_res_body) {
				this.showapi_res_body = showapi_res_body;
			}
			


}
