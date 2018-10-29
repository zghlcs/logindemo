package com.lcs.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletRequest myreq = new MyRequest(req);
		chain.doFilter(myreq, response);
	}

	class MyRequest extends HttpServletRequestWrapper{
		
		private HttpServletRequest request;
		
		private boolean isEncoding = false;
		
		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		@Override
		public String getParameter(String name) {
			Map<String, String[]> map = this.getParameterMap();
			String[] vals = map.get(name);
			if (vals!=null) {
				return vals[0];
			}
			return null;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			String method = request.getMethod();
			if ("post".equalsIgnoreCase(method)) {
				try {
					request.setCharacterEncoding("utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return request.getParameterMap();
			} else if("get".equalsIgnoreCase(method)){
				Map<String, String[]> map = request.getParameterMap();
				if (!isEncoding) {
					for (Entry<String, String[]> entry : map.entrySet()) {
						String[] valArr = entry.getValue();
						if (valArr!=null) {
							for (int i = 0; i < valArr.length; i++) {
								try {
									valArr[i] = new String(valArr[i].getBytes("iso-8859-1"), "utf-8");
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
							}
						}
					}
					isEncoding = true;
				}
				return map;
			}
			return super.getParameterMap();
		}

		@Override
		public String[] getParameterValues(String name) {
			Map<String, String[]> map = this.getParameterMap();
			return map.get(name);
		}
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}