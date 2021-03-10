package com.admon.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admon.model.util.SessionVar;

/**
 * Servlet Filter implementation class DWRSessionFilter
 */
@WebFilter("/dwr/call/*")
public class DWRSessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DWRSessionFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
		SessionVar sessionVar = (SessionVar) req.getSession().getAttribute("SESSION_PORTAL");
		Boolean sessionValid = Boolean.TRUE;
        if (sessionVar == null) {
        	sessionValid = false;
        } else {
            if (sessionVar.getUsuarioId() == null) {
            	sessionValid = false;
            }
        }
        if(url.contains("LoginDWR")) 
        	sessionValid = Boolean.TRUE;
        if(sessionValid){
        	chain.doFilter(request, response);
        }else{
        	res.setContentType("text/javascript");
        	String out = "//#DWR-REPLY\ndocument.location.href='"+req.getContextPath()+"/jsp/seguridad/login.jsp';";
        	res.getOutputStream().write(out.getBytes());
        	res.getOutputStream().flush();
        	res.getOutputStream().close();
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
