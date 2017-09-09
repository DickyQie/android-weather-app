package com.ard.weather.activity.widget;

public class MyChartItem {

	 private String x;
	    private float y;
	 
	    public MyChartItem(String vx, float vy) {
	        this.x = vx;
	        this.y = vy;
	    }
	 
	    public String getX() {
	        return x;
	    }
	 
	    public void setX(String x) {
	        this.x = x;
	    }
	 
	    public float getY() {
	        return y;
	    }
	 
	    public void setY(float y) {
	        this.y = y;
	    }
}
