package com.ard.weather.entity;

import java.util.List;


public class WeatherAreaIdInfo {
	
	private int showapi_res_code;
	private String showapi_res_error;
	private RetherInfo showapi_res_body;
	public class RetherInfo
	{
		List<ListInfo> list;
		int ret_code;
		public class ListInfo
		{
			String area;
			String areaid;
			CityInfo cityInfo;
			String distric;
			String link;
			String prov;
			public String getArea() {
				return area;
			}
			public void setArea(String area) {
				this.area = area;
			}
			public String getAreaid() {
				return areaid;
			}
			public void setAreaid(String areaid) {
				this.areaid = areaid;
			}
			public CityInfo getCityInfo() {
				return cityInfo;
			}
			public void setCityInfo(CityInfo cityInfo) {
				this.cityInfo = cityInfo;
			}
			public String getDistric() {
				return distric;
			}
			public void setDistric(String distric) {
				this.distric = distric;
			}
			public String getLink() {
				return link;
			}
			public void setLink(String link) {
				this.link = link;
			}
			public String getProv() {
				return prov;
			}
			public void setProv(String prov) {
				this.prov = prov;
			}
		}
		public List<ListInfo> getList() {
			return list;
		}
		public void setList(List<ListInfo> list) {
			this.list = list;
		}
		public int getRet_code() {
			return ret_code;
		}
		public void setRet_code(int ret_code) {
			this.ret_code = ret_code;
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
	public RetherInfo getShowapi_res_body() {
		return showapi_res_body;
	}
	public void setShowapi_res_body(RetherInfo showapi_res_body) {
		this.showapi_res_body = showapi_res_body;
	}
	
}
