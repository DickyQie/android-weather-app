package com.ard.weather.activity.entity;

import java.util.List;

/**
 * Created by zhangqie on 2019/2/28
 * Describe: 数据实体类
 */
public class WeatherBean {


    /**
     * code : 200
     * message :
     * redirect :
     * value : [{"alarms":[],"city":"杭州","cityid":101210101,"indexes":[{"abbreviation":"ct","alias":"","content":"天气偏凉，增加衣物厚度。","level":"温凉","name":"穿衣指数"},{"abbreviation":"gm","alias":"","content":"感冒可能发生，体质较弱的童鞋们要注意了，要及时增减衣物，适时补充水分，坚持锻炼身体。","level":"可能","name":"感冒指数"},{"abbreviation":"pp","alias":"","content":"天气寒冷，多补水，选用滋润保湿型化妆品，使用润唇膏。","level":"保湿","name":"化妆指数"},{"abbreviation":"yd","alias":"","content":"气压有点偏低了，较不适宜在户外进行剧烈运动。","level":"不适宜","name":"运动指数"},{"abbreviation":"xc","alias":"","content":"雨(雪)水和泥水会弄脏您的爱车，不适宜清洗车辆。","level":"不宜","name":"洗车指数"},{"abbreviation":"uv","alias":"","content":"辐射弱，涂擦SPF8-12防晒护肤品。","level":"最弱","name":"紫外线强度指数"}],"pm25":{"advice":"0","aqi":"72","citycount":590,"cityrank":44,"co":"9","color":"0","level":"0","no2":"20","o3":"19","pm10":"62","pm25":"71","quality":"良","so2":"4","timestamp":"","upDateTime":"2019-02-28 15:00:00"},"provinceName":"浙江省","realtime":{"img":"2","sD":"73","sendibleTemp":"8","temp":"9","time":"2019-02-28 16:25:08","wD":"东北风","wS":"2级","weather":"阴","ziwaixian":"N/A"},"weatherDetailsInfo":{"publishTime":"2019-02-28 16:00:00","weather3HoursDetailsInfos":[{"endTime":"2019-02-28 20:00:00","highestTemperature":"9","img":"2","isRainFall":"","lowerestTemperature":"9","precipitation":"0","startTime":"2019-02-28 17:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-02-28 23:00:00","highestTemperature":"7","img":"2","isRainFall":"","lowerestTemperature":"7","precipitation":"0","startTime":"2019-02-28 20:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-03-01 02:00:00","highestTemperature":"6","img":"0","isRainFall":"","lowerestTemperature":"6","precipitation":"0","startTime":"2019-02-28 23:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-03-01 05:00:00","highestTemperature":"7","img":"0","isRainFall":"","lowerestTemperature":"7","precipitation":"0","startTime":"2019-03-01 02:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-03-01 08:00:00","highestTemperature":"7","img":"2","isRainFall":"","lowerestTemperature":"7","precipitation":"0","startTime":"2019-03-01 05:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-03-01 11:00:00","highestTemperature":"8","img":"2","isRainFall":"","lowerestTemperature":"8","precipitation":"0","startTime":"2019-03-01 08:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-03-01 14:00:00","highestTemperature":"10","img":"2","isRainFall":"","lowerestTemperature":"10","precipitation":"0","startTime":"2019-03-01 11:00:00","wd":"","weather":"阴","ws":""}]},"weathers":[{"date":"2019-02-28","img":"2","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"10","temp_day_f":"50.0","temp_night_c":"6","temp_night_f":"42.8","wd":"","weather":"阴","week":"星期四","ws":""},{"date":"2019-03-01","img":"7","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"11","temp_day_f":"51.8","temp_night_c":"7","temp_night_f":"44.6","wd":"","weather":"小雨","week":"星期五","ws":""},{"date":"2019-03-02","img":"7","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"13","temp_day_f":"55.4","temp_night_c":"7","temp_night_f":"44.6","wd":"","weather":"小雨","week":"星期六","ws":""},{"date":"2019-03-03","img":"1","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"11","temp_day_f":"51.8","temp_night_c":"2","temp_night_f":"35.6","wd":"","weather":"多云","week":"星期日","ws":""},{"date":"2019-03-04","img":"1","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"16","temp_day_f":"60.8","temp_night_c":"6","temp_night_f":"42.8","wd":"","weather":"多云","week":"星期一","ws":""},{"date":"2019-03-05","img":"8","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"10","temp_day_f":"50.0","temp_night_c":"7","temp_night_f":"44.6","wd":"","weather":"中雨","week":"星期二","ws":""},{"date":"2019-02-27","img":"7","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"9","temp_day_f":"48.2","temp_night_c":"7","temp_night_f":"44.6","wd":"","weather":"小雨","week":"星期三","ws":""}]}]
     */

    private String code;
    private String message;
    private String redirect;
    private List<ValueBean> value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public List<ValueBean> getValue() {
        return value;
    }

    public void setValue(List<ValueBean> value) {
        this.value = value;
    }

    public static class ValueBean {
        /**
         * alarms : []
         * city : 杭州
         * cityid : 101210101
         * indexes : [{"abbreviation":"ct","alias":"","content":"天气偏凉，增加衣物厚度。","level":"温凉","name":"穿衣指数"},{"abbreviation":"gm","alias":"","content":"感冒可能发生，体质较弱的童鞋们要注意了，要及时增减衣物，适时补充水分，坚持锻炼身体。","level":"可能","name":"感冒指数"},{"abbreviation":"pp","alias":"","content":"天气寒冷，多补水，选用滋润保湿型化妆品，使用润唇膏。","level":"保湿","name":"化妆指数"},{"abbreviation":"yd","alias":"","content":"气压有点偏低了，较不适宜在户外进行剧烈运动。","level":"不适宜","name":"运动指数"},{"abbreviation":"xc","alias":"","content":"雨(雪)水和泥水会弄脏您的爱车，不适宜清洗车辆。","level":"不宜","name":"洗车指数"},{"abbreviation":"uv","alias":"","content":"辐射弱，涂擦SPF8-12防晒护肤品。","level":"最弱","name":"紫外线强度指数"}]
         * pm25 : {"advice":"0","aqi":"72","citycount":590,"cityrank":44,"co":"9","color":"0","level":"0","no2":"20","o3":"19","pm10":"62","pm25":"71","quality":"良","so2":"4","timestamp":"","upDateTime":"2019-02-28 15:00:00"}
         * provinceName : 浙江省
         * realtime : {"img":"2","sD":"73","sendibleTemp":"8","temp":"9","time":"2019-02-28 16:25:08","wD":"东北风","wS":"2级","weather":"阴","ziwaixian":"N/A"}
         * weatherDetailsInfo : {"publishTime":"2019-02-28 16:00:00","weather3HoursDetailsInfos":[{"endTime":"2019-02-28 20:00:00","highestTemperature":"9","img":"2","isRainFall":"","lowerestTemperature":"9","precipitation":"0","startTime":"2019-02-28 17:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-02-28 23:00:00","highestTemperature":"7","img":"2","isRainFall":"","lowerestTemperature":"7","precipitation":"0","startTime":"2019-02-28 20:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-03-01 02:00:00","highestTemperature":"6","img":"0","isRainFall":"","lowerestTemperature":"6","precipitation":"0","startTime":"2019-02-28 23:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-03-01 05:00:00","highestTemperature":"7","img":"0","isRainFall":"","lowerestTemperature":"7","precipitation":"0","startTime":"2019-03-01 02:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-03-01 08:00:00","highestTemperature":"7","img":"2","isRainFall":"","lowerestTemperature":"7","precipitation":"0","startTime":"2019-03-01 05:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-03-01 11:00:00","highestTemperature":"8","img":"2","isRainFall":"","lowerestTemperature":"8","precipitation":"0","startTime":"2019-03-01 08:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-03-01 14:00:00","highestTemperature":"10","img":"2","isRainFall":"","lowerestTemperature":"10","precipitation":"0","startTime":"2019-03-01 11:00:00","wd":"","weather":"阴","ws":""}]}
         * weathers : [{"date":"2019-02-28","img":"2","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"10","temp_day_f":"50.0","temp_night_c":"6","temp_night_f":"42.8","wd":"","weather":"阴","week":"星期四","ws":""},{"date":"2019-03-01","img":"7","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"11","temp_day_f":"51.8","temp_night_c":"7","temp_night_f":"44.6","wd":"","weather":"小雨","week":"星期五","ws":""},{"date":"2019-03-02","img":"7","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"13","temp_day_f":"55.4","temp_night_c":"7","temp_night_f":"44.6","wd":"","weather":"小雨","week":"星期六","ws":""},{"date":"2019-03-03","img":"1","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"11","temp_day_f":"51.8","temp_night_c":"2","temp_night_f":"35.6","wd":"","weather":"多云","week":"星期日","ws":""},{"date":"2019-03-04","img":"1","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"16","temp_day_f":"60.8","temp_night_c":"6","temp_night_f":"42.8","wd":"","weather":"多云","week":"星期一","ws":""},{"date":"2019-03-05","img":"8","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"10","temp_day_f":"50.0","temp_night_c":"7","temp_night_f":"44.6","wd":"","weather":"中雨","week":"星期二","ws":""},{"date":"2019-02-27","img":"7","sun_down_time":"17:57","sun_rise_time":"06:27","temp_day_c":"9","temp_day_f":"48.2","temp_night_c":"7","temp_night_f":"44.6","wd":"","weather":"小雨","week":"星期三","ws":""}]
         */

        private String city;
        private int cityid;
        private Pm25Bean pm25;
        private String provinceName;
        private RealtimeBean realtime;
        private WeatherDetailsInfoBean weatherDetailsInfo;
        private List<?> alarms;
        private List<IndexesBean> indexes;
        private List<WeathersBean> weathers;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public Pm25Bean getPm25() {
            return pm25;
        }

        public void setPm25(Pm25Bean pm25) {
            this.pm25 = pm25;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public RealtimeBean getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeBean realtime) {
            this.realtime = realtime;
        }

        public WeatherDetailsInfoBean getWeatherDetailsInfo() {
            return weatherDetailsInfo;
        }

        public void setWeatherDetailsInfo(WeatherDetailsInfoBean weatherDetailsInfo) {
            this.weatherDetailsInfo = weatherDetailsInfo;
        }

        public List<?> getAlarms() {
            return alarms;
        }

        public void setAlarms(List<?> alarms) {
            this.alarms = alarms;
        }

        public List<IndexesBean> getIndexes() {
            return indexes;
        }

        public void setIndexes(List<IndexesBean> indexes) {
            this.indexes = indexes;
        }

        public List<WeathersBean> getWeathers() {
            return weathers;
        }

        public void setWeathers(List<WeathersBean> weathers) {
            this.weathers = weathers;
        }

        public static class Pm25Bean {
            /**
             * advice : 0
             * aqi : 72
             * citycount : 590
             * cityrank : 44
             * co : 9
             * color : 0
             * level : 0
             * no2 : 20
             * o3 : 19
             * pm10 : 62
             * pm25 : 71
             * quality : 良
             * so2 : 4
             * timestamp :
             * upDateTime : 2019-02-28 15:00:00
             */

            private String advice;
            private String aqi;
            private int citycount;
            private int cityrank;
            private String co;
            private String color;
            private String level;
            private String no2;
            private String o3;
            private String pm10;
            private String pm25;
            private String quality;
            private String so2;
            private String timestamp;
            private String upDateTime;

            public String getAdvice() {
                return advice;
            }

            public void setAdvice(String advice) {
                this.advice = advice;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public int getCitycount() {
                return citycount;
            }

            public void setCitycount(int citycount) {
                this.citycount = citycount;
            }

            public int getCityrank() {
                return cityrank;
            }

            public void setCityrank(int cityrank) {
                this.cityrank = cityrank;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getQuality() {
                return quality;
            }

            public void setQuality(String quality) {
                this.quality = quality;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getUpDateTime() {
                return upDateTime;
            }

            public void setUpDateTime(String upDateTime) {
                this.upDateTime = upDateTime;
            }
        }

        public static class RealtimeBean {
            /**
             * img : 2
             * sD : 73
             * sendibleTemp : 8
             * temp : 9
             * time : 2019-02-28 16:25:08
             * wD : 东北风
             * wS : 2级
             * weather : 阴
             * ziwaixian : N/A
             */

            private String img;
            private String sD;
            private String sendibleTemp;
            private String temp;
            private String time;
            private String wD;
            private String wS;
            private String weather;
            private String ziwaixian;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSD() {
                return sD;
            }

            public void setSD(String sD) {
                this.sD = sD;
            }

            public String getSendibleTemp() {
                return sendibleTemp;
            }

            public void setSendibleTemp(String sendibleTemp) {
                this.sendibleTemp = sendibleTemp;
            }

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getWD() {
                return wD;
            }

            public void setWD(String wD) {
                this.wD = wD;
            }

            public String getWS() {
                return wS;
            }

            public void setWS(String wS) {
                this.wS = wS;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getZiwaixian() {
                return ziwaixian;
            }

            public void setZiwaixian(String ziwaixian) {
                this.ziwaixian = ziwaixian;
            }
        }

        public static class WeatherDetailsInfoBean {
            /**
             * publishTime : 2019-02-28 16:00:00
             * weather3HoursDetailsInfos : [{"endTime":"2019-02-28 20:00:00","highestTemperature":"9","img":"2","isRainFall":"","lowerestTemperature":"9","precipitation":"0","startTime":"2019-02-28 17:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-02-28 23:00:00","highestTemperature":"7","img":"2","isRainFall":"","lowerestTemperature":"7","precipitation":"0","startTime":"2019-02-28 20:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-03-01 02:00:00","highestTemperature":"6","img":"0","isRainFall":"","lowerestTemperature":"6","precipitation":"0","startTime":"2019-02-28 23:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-03-01 05:00:00","highestTemperature":"7","img":"0","isRainFall":"","lowerestTemperature":"7","precipitation":"0","startTime":"2019-03-01 02:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-03-01 08:00:00","highestTemperature":"7","img":"2","isRainFall":"","lowerestTemperature":"7","precipitation":"0","startTime":"2019-03-01 05:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-03-01 11:00:00","highestTemperature":"8","img":"2","isRainFall":"","lowerestTemperature":"8","precipitation":"0","startTime":"2019-03-01 08:00:00","wd":"","weather":"阴","ws":""},{"endTime":"2019-03-01 14:00:00","highestTemperature":"10","img":"2","isRainFall":"","lowerestTemperature":"10","precipitation":"0","startTime":"2019-03-01 11:00:00","wd":"","weather":"阴","ws":""}]
             */

            private String publishTime;
            private List<Weather3HoursDetailsInfosBean> weather3HoursDetailsInfos;

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public List<Weather3HoursDetailsInfosBean> getWeather3HoursDetailsInfos() {
                return weather3HoursDetailsInfos;
            }

            public void setWeather3HoursDetailsInfos(List<Weather3HoursDetailsInfosBean> weather3HoursDetailsInfos) {
                this.weather3HoursDetailsInfos = weather3HoursDetailsInfos;
            }

            public static class Weather3HoursDetailsInfosBean {
                /**
                 * endTime : 2019-02-28 20:00:00
                 * highestTemperature : 9
                 * img : 2
                 * isRainFall :
                 * lowerestTemperature : 9
                 * precipitation : 0
                 * startTime : 2019-02-28 17:00:00
                 * wd :
                 * weather : 阴
                 * ws :
                 */

                private String endTime;
                private String highestTemperature;
                private String img;
                private String isRainFall;
                private String lowerestTemperature;
                private String precipitation;
                private String startTime;
                private String wd;
                private String weather;
                private String ws;

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public String getHighestTemperature() {
                    return highestTemperature;
                }

                public void setHighestTemperature(String highestTemperature) {
                    this.highestTemperature = highestTemperature;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getIsRainFall() {
                    return isRainFall;
                }

                public void setIsRainFall(String isRainFall) {
                    this.isRainFall = isRainFall;
                }

                public String getLowerestTemperature() {
                    return lowerestTemperature;
                }

                public void setLowerestTemperature(String lowerestTemperature) {
                    this.lowerestTemperature = lowerestTemperature;
                }

                public String getPrecipitation() {
                    return precipitation;
                }

                public void setPrecipitation(String precipitation) {
                    this.precipitation = precipitation;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getWd() {
                    return wd;
                }

                public void setWd(String wd) {
                    this.wd = wd;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getWs() {
                    return ws;
                }

                public void setWs(String ws) {
                    this.ws = ws;
                }
            }
        }

        public static class IndexesBean {
            /**
             * abbreviation : ct
             * alias :
             * content : 天气偏凉，增加衣物厚度。
             * level : 温凉
             * name : 穿衣指数
             */

            private String abbreviation;
            private String alias;
            private String content;
            private String level;
            private String name;

            public String getAbbreviation() {
                return abbreviation;
            }

            public void setAbbreviation(String abbreviation) {
                this.abbreviation = abbreviation;
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class WeathersBean {
            /**
             * date : 2019-02-28
             * img : 2
             * sun_down_time : 17:57
             * sun_rise_time : 06:27
             * temp_day_c : 10
             * temp_day_f : 50.0
             * temp_night_c : 6
             * temp_night_f : 42.8
             * wd :
             * weather : 阴
             * week : 星期四
             * ws :
             */

            private String date;
            private String img;
            private String sun_down_time;
            private String sun_rise_time;
            private String temp_day_c;
            private String temp_day_f;
            private String temp_night_c;
            private String temp_night_f;
            private String wd;
            private String weather;
            private String week;
            private String ws;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSun_down_time() {
                return sun_down_time;
            }

            public void setSun_down_time(String sun_down_time) {
                this.sun_down_time = sun_down_time;
            }

            public String getSun_rise_time() {
                return sun_rise_time;
            }

            public void setSun_rise_time(String sun_rise_time) {
                this.sun_rise_time = sun_rise_time;
            }

            public String getTemp_day_c() {
                return temp_day_c;
            }

            public void setTemp_day_c(String temp_day_c) {
                this.temp_day_c = temp_day_c;
            }

            public String getTemp_day_f() {
                return temp_day_f;
            }

            public void setTemp_day_f(String temp_day_f) {
                this.temp_day_f = temp_day_f;
            }

            public String getTemp_night_c() {
                return temp_night_c;
            }

            public void setTemp_night_c(String temp_night_c) {
                this.temp_night_c = temp_night_c;
            }

            public String getTemp_night_f() {
                return temp_night_f;
            }

            public void setTemp_night_f(String temp_night_f) {
                this.temp_night_f = temp_night_f;
            }

            public String getWd() {
                return wd;
            }

            public void setWd(String wd) {
                this.wd = wd;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getWs() {
                return ws;
            }

            public void setWs(String ws) {
                this.ws = ws;
            }
        }
    }
}
