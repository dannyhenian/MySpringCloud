package com.danny.cloud;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreZuulFilter extends ZuulFilter {
  private static final Logger LOGGER = LoggerFactory.getLogger(PreZuulFilter.class);

  //返回为true，则run()会被执行；返回为false，则run()不会被执行
  @Override
  public boolean shouldFilter() {
    return true;
  }

  //fiter具体的逻辑
  @Override
  public Object run() {
    HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
    String host = request.getRemoteHost();
    PreZuulFilter.LOGGER.info("请求的host:{}", host);
    return null;
  }

  //fiter类型,包括pre、routing、post、error
  @Override
  public String filterType() {
    return "pre";
  }

  //执行的顺序，数字越大越靠后
  @Override
  public int filterOrder() {
    return 1;
  }

}
