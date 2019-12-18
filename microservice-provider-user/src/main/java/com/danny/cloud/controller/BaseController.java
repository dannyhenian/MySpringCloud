package com.danny.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.danny.cloud.utils.HttpTools;
import com.danny.cloud.utils.JsonObject;
import com.ssx.logging.utils.LogUtils;
import org.apache.http.Header;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Collection;

/**
 * @author danny
 * @create 2019-12-17 14:31
 */

@RestController
public class BaseController {

	public JSONObject parseMsg(HttpServletRequest req) {
		JSONObject msg = null;
		try{
			String body = readReqMsg(req);
			if(body.length() < 1024) {
				req.getSession().setAttribute("requestbody", body);
			}
			else{
				req.getSession().setAttribute("requestbody", "body too large");
			}
			msg = JSONObject.parseObject(body);
		} catch (Exception e) {
			LogUtils.sys_error(BaseController.class, e.getMessage(), "ParseMsg");
		}		
		return msg;
	}
	 
	public String readReqMsg(HttpServletRequest request) {
		StringBuffer reqMsg = new StringBuffer();
		BufferedReader reader;
		try {
			reader = request.getReader();
			String str = "";
			while ((str = reader.readLine()) != null) {
				reqMsg.append(str);
			}

			return reqMsg.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

	/**
	 * 服务请求转发附带(header= null)
	 * @param service
	 * @param url
	 * @param param
	 * @return
	 */
	public JsonObject requestService(String service, String url, String param) {
		return requestService(service, url, param,null);
	}

	/**
	 * 服务请求转发附带header
	 * @param service
	 * @param url
	 * @param param
	 * @param headers
	 * @return
	 */
	public JsonObject requestService(String service, String url, String param, Collection<Header> headers) {

		return HttpTools.postRequest(service, url, param, 10000, headers);
	}

}
