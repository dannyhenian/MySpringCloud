package com.danny.cloud.utils;

import com.alibaba.fastjson.JSONObject;
import com.ssx.logging.utils.LogUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class HttpTools {

	private static final String HTTP_PREFIX = "http://";
	
	/**
	 * post提交请求
	 * @param server_url
	 * @param path
	 * @param json
	 * @param timeout
	 * @return
	 */
	public static JsonObject postRequest(String server_url, String path, String json, int timeout){
		return postRequest(server_url, path, json, timeout,null);
	}
	
	/**
	 * post 提交请求附带Headers
	 * @param server_url
	 * @param path
	 * @param json
	 * @param timeout
	 * @param headers
	 * @return
	 */
	public static JsonObject postRequest(String server_url, String path, String json, int timeout, Collection<Header> headers) {
		JsonObject jsonObject = new JsonObject();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringBuffer s = new StringBuffer();
		s.append(HTTP_PREFIX).append(server_url).append(path);

		String url = s.toString();
		HttpPost httpPost= new HttpPost(url);
		if (json.length() < 1024) {
			LogUtils.sys_info(HttpTools.class, "request:"+url+"-"+json, "postRequest");

		} else {
			LogUtils.sys_info(HttpTools.class, "request:"+url+"- too large content", "postRequest");
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(2000).build();
		httpPost.setConfig(requestConfig);

		try {
			StringEntity se = new StringEntity(json,"UTF-8");
			se.setContentType("application/json;charset=UTF-8");
			se.setContentEncoding("UTF-8");
			httpPost.setEntity(se);

			//设置请求的Header参数
			if (null !=  headers && !headers.isEmpty()) {
				httpPost.setHeaders((Header[])headers.toArray(new Header[headers.size()]));
			}

			CloseableHttpResponse response1 = httpclient.execute(httpPost);
			System.out.println(response1.getStatusLine());
			if (response1.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

//				result = response1.getStatusLine().toString();
				HttpEntity entity = response1.getEntity();
				String jsonStr = EntityUtils.toString(entity,"UTF-8");
				EntityUtils.consume(entity);
//				result = jsonStr;
				System.out.println("============" + jsonStr);
				jsonObject = JSONObject.parseObject(jsonStr, JsonObject.class);
			}
			return jsonObject;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}
	
	public static String postRequestWithStreamOut(String server_url, String path, String json, int timeout, HttpServletResponse resp) {
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringBuffer s = new StringBuffer();
		s.append(HTTP_PREFIX).append(server_url).append(path);

		String url = s.toString();
		HttpPost httpPost = new HttpPost(url);
		if (json.length() < 1024) {
			LogUtils.sys_info(HttpTools.class, "request:"+url+" - "+json, "postRequestWithStreamOut");

		} else {
			LogUtils.sys_info(HttpTools.class, "request:" + url + "- too large content", "postRequestWithStreamOut");
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(2000).build();
		httpPost.setConfig(requestConfig);

		try {
			StringEntity se = new StringEntity(json,"UTF-8");
			se.setContentType("application/json;charset=UTF-8");
			se.setContentEncoding("UTF-8");
			se.setChunked(false);
			httpPost.setEntity(se);
			CloseableHttpResponse response1 = httpclient.execute(httpPost);
			System.out.println(response1.getStatusLine());
			if (response1.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = response1.getStatusLine().toString();

				HttpEntity entity = response1.getEntity();
				Header[] headers = response1.getAllHeaders();
				for (Header h:headers) {
					resp.setHeader(h.getName(), h.getValue());
				}
				ServletOutputStream os = resp.getOutputStream();
				InputStream instream = entity.getContent();
				int l;
				byte[] tmp = new byte[2048];
				while ((l = instream.read(tmp)) != -1) {
					os.write(tmp, 0, l);
				}
				EntityUtils.consume(entity);
				//os.flush();
				os.close();
				instream.close();
				return "";
			} else {
				return "";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 获取请求
	 * @param server_url
	 * @param path
	 * @param timeout
	 * @return
	 */
	public static String getRequest(String server_url, String path, int timeout, Collection<Header> headers) {
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringBuffer s = new StringBuffer();
		s.append(HTTP_PREFIX).append(server_url).append(path);

		String url = s.toString();
		System.out.println("request:" + url + "\n");
		HttpGet httpGet = new HttpGet(url);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(2000).build();
		httpGet.setConfig(requestConfig);

		//设置请求的Header参数
		if (null !=  headers && !headers.isEmpty()) {
			httpGet.setHeaders((Header[])headers.toArray(new Header[headers.size()]));
		}

		try {
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			System.out.println(response1.getStatusLine());
			if (response1.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response1.getEntity();
				String jsonStr = EntityUtils.toString(entity,"UTF-8");
				EntityUtils.consume(entity);
				System.out.println(jsonStr);
				return jsonStr;
			}
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String postOARequest(String server_url, String path, String json, int timeout, Collection<Header> headers) {
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringBuffer s = new StringBuffer();
		s.append(HTTP_PREFIX).append(server_url).append(path);
		
		String url = s.toString();
		HttpPost httpPost= new HttpPost(url);
		System.out.println("request:" + url + "\n" + json);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        
        try {
            StringEntity se = new StringEntity(json,"UTF-8");
            se.setContentType("application/json");
            se.setContentEncoding("UTF-8");
            httpPost.setEntity(se);
            
            //设置请求的Header参数
			if (null !=  headers && !headers.isEmpty()) {
				httpPost.setHeaders((Header[])headers.toArray(new Header[headers.size()]));
			}

			CloseableHttpResponse response1 = httpclient.execute(httpPost);
			System.out.println(response1.getStatusLine());
			if (response1.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = response1.getStatusLine().toString();
				HttpEntity entity = response1.getEntity();
				String jsonStr = EntityUtils.toString(entity,"UTF-8");
				EntityUtils.consume(entity);
				result = jsonStr;
			}
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}