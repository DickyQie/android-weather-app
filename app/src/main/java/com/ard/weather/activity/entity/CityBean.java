package com.ard.weather.activity.entity;

import java.util.List;

/**
 * Created by zhangqie on 2019/3/5
 * Describe:
 */
public class CityBean {


    private List<CitiesBean> cities;

    public List<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(List<CitiesBean> cities) {
        this.cities = cities;
    }

    public static class CitiesBean {
        /**
         * city : 北京
         * cityid : 101010100
         */

        private String city;
        private String cityid;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }
    }
}
