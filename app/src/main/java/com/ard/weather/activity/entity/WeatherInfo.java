package com.ard.weather.activity.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class WeatherInfo {


    /**
     * result : {"realtime":{"wind":{"windspeed":null,"direct":"东风","power":"2级","offset":null},"time":"15:00:00","weather":{"humidity":"31","img":"1","info":"多云","temperature":"18"},"dataUptime":"1488180845","date":"2017-02-27","city_code":"101200101","city_name":"武汉","week":"1","moon":"二月初二"},"life":{"date":"2017-2-27","info":{"kongtiao":["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"],"yundong":["较不宜","天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"],"ganmao":["易发","昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"wuran":null,"chuanyi":["较舒适","建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"]}},"weather":[{"date":"2017-02-27","week":"一","nongli":"二月初二","info":{"dawn":null,"day":["1","多云","18","","微风","06:51"],"night":["1","多云","4","","微风","18:20"]}},{"date":"2017-02-28","week":"二","nongli":"二月初三","info":{"dawn":["1","多云","4","无持续风向","微风","18:20"],"day":["1","多云","18","","微风","06:50"],"night":["1","多云","5","","微风","18:20"]}},{"date":"2017-03-01","week":"三","nongli":"二月初四","info":{"dawn":["1","多云","5","无持续风向","微风","18:20"],"day":["1","多云","17","","微风","06:48"],"night":["1","多云","1","","微风","18:21"]}},{"date":"2017-03-02","week":"四","nongli":"二月初五","info":{"dawn":["1","多云","1","无持续风向","微风","18:21"],"day":["1","多云","15","","微风","06:47"],"night":["1","多云","6","","微风","18:22"]}},{"date":"2017-03-03","week":"五","nongli":"二月初六","info":{"dawn":["1","多云","6","无持续风向","微风","18:22"],"day":["1","多云","15","","微风","06:46"],"night":["7","小雨","4","","微风","18:22"]}}],"pm25":{"key":"Wuhan","show_desc":"1","pm25":{"curPm":"119","pm25":"87","pm10":"145","level":"3","quality":"轻度污染","des":"敏感人群应避免高强度户外锻炼，外出时做好防护措施"},"dateTime":"2017年02月27日10时","cityName":"武汉"},"isForeign":0}
     * error_code : 0
     * reason : Succes
     */

    private ResultBean result;
    private int error_code;
    private String reason;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static class ResultBean {
        /**
         * realtime : {"wind":{"windspeed":null,"direct":"东风","power":"2级","offset":null},"time":"15:00:00","weather":{"humidity":"31","img":"1","info":"多云","temperature":"18"},"dataUptime":"1488180845","date":"2017-02-27","city_code":"101200101","city_name":"武汉","week":"1","moon":"二月初二"}
         * life : {"date":"2017-2-27","info":{"kongtiao":["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"],"yundong":["较不宜","天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"],"ganmao":["易发","昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"wuran":null,"chuanyi":["较舒适","建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"]}}
         * weather : [{"date":"2017-02-27","week":"一","nongli":"二月初二","info":{"dawn":null,"day":["1","多云","18","","微风","06:51"],"night":["1","多云","4","","微风","18:20"]}},{"date":"2017-02-28","week":"二","nongli":"二月初三","info":{"dawn":["1","多云","4","无持续风向","微风","18:20"],"day":["1","多云","18","","微风","06:50"],"night":["1","多云","5","","微风","18:20"]}},{"date":"2017-03-01","week":"三","nongli":"二月初四","info":{"dawn":["1","多云","5","无持续风向","微风","18:20"],"day":["1","多云","17","","微风","06:48"],"night":["1","多云","1","","微风","18:21"]}},{"date":"2017-03-02","week":"四","nongli":"二月初五","info":{"dawn":["1","多云","1","无持续风向","微风","18:21"],"day":["1","多云","15","","微风","06:47"],"night":["1","多云","6","","微风","18:22"]}},{"date":"2017-03-03","week":"五","nongli":"二月初六","info":{"dawn":["1","多云","6","无持续风向","微风","18:22"],"day":["1","多云","15","","微风","06:46"],"night":["7","小雨","4","","微风","18:22"]}}]
         * pm25 : {"key":"Wuhan","show_desc":"1","pm25":{"curPm":"119","pm25":"87","pm10":"145","level":"3","quality":"轻度污染","des":"敏感人群应避免高强度户外锻炼，外出时做好防护措施"},"dateTime":"2017年02月27日10时","cityName":"武汉"}
         * isForeign : 0
         */

        private RealtimeBean realtime;
        private LifeBean life;
        private Pm25BeanX pm25;
        private int isForeign;
        private List<WeatherBeanX> weather;

        public RealtimeBean getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeBean realtime) {
            this.realtime = realtime;
        }

        public LifeBean getLife() {
            return life;
        }

        public void setLife(LifeBean life) {
            this.life = life;
        }

        public Pm25BeanX getPm25() {
            return pm25;
        }

        public void setPm25(Pm25BeanX pm25) {
            this.pm25 = pm25;
        }

        public int getIsForeign() {
            return isForeign;
        }

        public void setIsForeign(int isForeign) {
            this.isForeign = isForeign;
        }

        public List<WeatherBeanX> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherBeanX> weather) {
            this.weather = weather;
        }

        public static class RealtimeBean {
            /**
             * wind : {"windspeed":null,"direct":"东风","power":"2级","offset":null}
             * time : 15:00:00
             * weather : {"humidity":"31","img":"1","info":"多云","temperature":"18"}
             * dataUptime : 1488180845
             * date : 2017-02-27
             * city_code : 101200101
             * city_name : 武汉
             * week : 1
             * moon : 二月初二
             */

            private WindBean wind;
            private String time;
            private WeatherBean weather;
            private String dataUptime;
            private String date;
            private String city_code;
            private String city_name;
            private String week;
            private String moon;

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public WeatherBean getWeather() {
                return weather;
            }

            public void setWeather(WeatherBean weather) {
                this.weather = weather;
            }

            public String getDataUptime() {
                return dataUptime;
            }

            public void setDataUptime(String dataUptime) {
                this.dataUptime = dataUptime;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCity_code() {
                return city_code;
            }

            public void setCity_code(String city_code) {
                this.city_code = city_code;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getMoon() {
                return moon;
            }

            public void setMoon(String moon) {
                this.moon = moon;
            }

            public static class WindBean {
                /**
                 * windspeed : null
                 * direct : 东风
                 * power : 2级
                 * offset : null
                 */

                private Object windspeed;
                private String direct;
                private String power;
                private Object offset;

                public Object getWindspeed() {
                    return windspeed;
                }

                public void setWindspeed(Object windspeed) {
                    this.windspeed = windspeed;
                }

                public String getDirect() {
                    return direct;
                }

                public void setDirect(String direct) {
                    this.direct = direct;
                }

                public String getPower() {
                    return power;
                }

                public void setPower(String power) {
                    this.power = power;
                }

                public Object getOffset() {
                    return offset;
                }

                public void setOffset(Object offset) {
                    this.offset = offset;
                }
            }

            public static class WeatherBean {
                /**
                 * humidity : 31
                 * img : 1
                 * info : 多云
                 * temperature : 18
                 */

                private String humidity;
                private String img;
                private String info;
                private String temperature;

                public String getHumidity() {
                    return humidity;
                }

                public void setHumidity(String humidity) {
                    this.humidity = humidity;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }
            }
        }

        public static class LifeBean {
            /**
             * date : 2017-2-27
             * info : {"kongtiao":["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"],"yundong":["较不宜","天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"],"ganmao":["易发","昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"wuran":null,"chuanyi":["较舒适","建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"]}
             */

            private String date;
            private InfoBean info;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public static class InfoBean {
                /**
                 * kongtiao : ["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"]
                 * yundong : ["较不宜","天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。"]
                 * ziwaixian : ["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"]
                 * ganmao : ["易发","昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"]
                 * xiche : ["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"]
                 * wuran : null
                 * chuanyi : ["较舒适","建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"]
                 */

                private Object wuran;
                private List<String> kongtiao;
                private List<String> yundong;
                private List<String> ziwaixian;
                private List<String> ganmao;
                private List<String> xiche;
                private List<String> chuanyi;

                public Object getWuran() {
                    return wuran;
                }

                public void setWuran(Object wuran) {
                    this.wuran = wuran;
                }

                public List<String> getKongtiao() {
                    return kongtiao;
                }

                public void setKongtiao(List<String> kongtiao) {
                    this.kongtiao = kongtiao;
                }

                public List<String> getYundong() {
                    return yundong;
                }

                public void setYundong(List<String> yundong) {
                    this.yundong = yundong;
                }

                public List<String> getZiwaixian() {
                    return ziwaixian;
                }

                public void setZiwaixian(List<String> ziwaixian) {
                    this.ziwaixian = ziwaixian;
                }

                public List<String> getGanmao() {
                    return ganmao;
                }

                public void setGanmao(List<String> ganmao) {
                    this.ganmao = ganmao;
                }

                public List<String> getXiche() {
                    return xiche;
                }

                public void setXiche(List<String> xiche) {
                    this.xiche = xiche;
                }

                public List<String> getChuanyi() {
                    return chuanyi;
                }

                public void setChuanyi(List<String> chuanyi) {
                    this.chuanyi = chuanyi;
                }
            }
        }

        public static class Pm25BeanX {
            /**
             * key : Wuhan
             * show_desc : 1
             * pm25 : {"curPm":"119","pm25":"87","pm10":"145","level":"3","quality":"轻度污染","des":"敏感人群应避免高强度户外锻炼，外出时做好防护措施"}
             * dateTime : 2017年02月27日10时
             * cityName : 武汉
             */

            private String key;
            private String show_desc;
            private Pm25Bean pm25;
            private String dateTime;
            private String cityName;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getShow_desc() {
                return show_desc;
            }

            public void setShow_desc(String show_desc) {
                this.show_desc = show_desc;
            }

            public Pm25Bean getPm25() {
                return pm25;
            }

            public void setPm25(Pm25Bean pm25) {
                this.pm25 = pm25;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public static class Pm25Bean {
                /**
                 * curPm : 119
                 * pm25 : 87
                 * pm10 : 145
                 * level : 3
                 * quality : 轻度污染
                 * des : 敏感人群应避免高强度户外锻炼，外出时做好防护措施
                 */

                private String curPm;
                private String pm25;
                private String pm10;
                private String level;
                private String quality;
                private String des;

                public String getCurPm() {
                    return curPm;
                }

                public void setCurPm(String curPm) {
                    this.curPm = curPm;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getQuality() {
                    return quality;
                }

                public void setQuality(String quality) {
                    this.quality = quality;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }
        }

        public static class WeatherBeanX {
            /**
             * date : 2017-02-27
             * week : 一
             * nongli : 二月初二
             * info : {"dawn":null,"day":["1","多云","18","","微风","06:51"],"night":["1","多云","4","","微风","18:20"]}
             */

            private String date;
            private String week;
            private String nongli;
            private InfoBeanX info;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getNongli() {
                return nongli;
            }

            public void setNongli(String nongli) {
                this.nongli = nongli;
            }

            public InfoBeanX getInfo() {
                return info;
            }

            public void setInfo(InfoBeanX info) {
                this.info = info;
            }

            public static class InfoBeanX {
                /**
                 * dawn : null
                 * day : ["1","多云","18","","微风","06:51"]
                 * night : ["1","多云","4","","微风","18:20"]
                 */

                private Object dawn;
                private List<String> day;
                private List<String> night;

                public Object getDawn() {
                    return dawn;
                }

                public void setDawn(Object dawn) {
                    this.dawn = dawn;
                }

                public List<String> getDay() {
                    return day;
                }

                public void setDay(List<String> day) {
                    this.day = day;
                }

                public List<String> getNight() {
                    return night;
                }

                public void setNight(List<String> night) {
                    this.night = night;
                }
            }
        }
    }
}
