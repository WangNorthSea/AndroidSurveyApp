import javax.servlet.*;
import java.io.*;

public class MyServlet implements Servlet {
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("Servlet inited!!!!\n");
	}
	
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}
	
	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
		System.out.println("service called\n");
		servletResponse.getWriter().write("hello world!");
	}
	
	@Override
	public String getServletInfo() {
		return null;
	}
	
	@Override
	public void destroy() {
		System.out.println("Servlet destroyed!!!!\n");
	}
}
