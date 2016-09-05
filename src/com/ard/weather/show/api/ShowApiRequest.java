package com.ard.weather.show.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class ShowApiRequest     {
	protected int connectTimeout = 3000;//3秒
	protected int readTimeout = 15000;//15秒
	protected String charset="utf-8";  //出去时的编码
	protected String appSecret;
	protected String url;
	protected Map<String,String> textMap=new HashMap<String, String>();
	protected RequestParams params=new RequestParams();
	AsyncHttpClient client = new AsyncHttpClient();
	protected AsyncHttpResponseHandler handler=new AsyncHttpResponseHandler(){
		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
			// TODO Auto-generated method stub
			
		}};
	public ShowApiRequest( String url,String appid,String appSecret    ) {
		this.url=url;
		this.appSecret = appSecret;
		params.put(Constants.SHOWAPI_APPID,appid);
		textMap.put(Constants.SHOWAPI_APPID,appid);
	}
	
	public ShowApiRequest addTextPara(String key,String value) {
		if(key==null)return this;
		if(key.trim().length()==0)return this;
		params.add(key,value);
		textMap.put(key,value); //为了计算sign
		return this;
	}
	public ShowApiRequest addFilePara(String key,File file) {
		if(key==null)return this;
		if(key.trim().length()==0)return this;
		try {
			params.put(key, file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return this;
	}
	public ShowApiRequest addHeadPara(String key,String value) {
		if(key==null)return this;
		if(key.trim().length()==0)return this;
		client.addHeader(key, value);
		return this;
	}
	
	private void addSign() throws  Exception{
		boolean ismd5=true;
		if(textMap.get(Constants.SHOWAPI_APPID)==null)throw new Exception( errorMsg(Constants.SHOWAPI_APPID+"不得为空!"));
		String signmethod=textMap.get(Constants.SHOWAPI_SIGN_METHOD);
		if(signmethod!=null&&!signmethod.equals("md5"))ismd5=false;
		if(signmethod!=null&&!signmethod.equals("md5")&&!signmethod.equals("hmac"))
			throw new Exception( errorMsg("showapi_sign_method参数只能是md5或hmac"));
				
		if(textMap.get(Constants.SHOWAPI_TIMESTAMP)==null){
			SimpleDateFormat df=new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
			String timestamp= df.format(new Date());
			params.put(Constants.SHOWAPI_TIMESTAMP,timestamp); //放时间
			textMap.put(Constants.SHOWAPI_TIMESTAMP,timestamp); //放时间
		}
		String sign;
		if (ismd5) {
			sign=ShowApiUtils.signRequest(textMap, appSecret, false);
		} else {
			sign=ShowApiUtils.signRequest(textMap, appSecret, true);
		}
		params.put(Constants.SHOWAPI_SIGN, sign);//放签名
	}
	
	public void post(  )   {
		try {
			addSign(); 
			client.setConnectTimeout(connectTimeout);
			client.setResponseTimeout(readTimeout);
			client.post( url, params,handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void get()   {
		try {
			addSign();
			client.setConnectTimeout(connectTimeout);
			client.setResponseTimeout(readTimeout);
			client.get( url, params,handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private   String errorMsg(String msg){
		String str="{showapi_res_code:-1,showapi_res_error:"+msg+",showapi_res_body:{}}";
		return str;
	}
	
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public AsyncHttpResponseHandler getHandler() {
		return handler;
	}
	public ShowApiRequest setResponseHandler(AsyncHttpResponseHandler handler) {
		this.handler = handler;
		return this;
	}
	
	
}

