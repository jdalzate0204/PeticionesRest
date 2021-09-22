package co.edu.unicundi.peticionesrest.controller.filter;

import com.wordnik.swagger.jaxrs.JaxrsApiReader;
 
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author James Daniel Alzate Rios
 * @author Paula Alejandra Guzman Cruz
 */
public class SwaggerFilter implements javax.servlet.Filter {
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        JaxrsApiReader.setFormatString("");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
 
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type");
 
        chain.doFilter(request, response);
 
    }
 
    @Override
    public void destroy() {
    }
        
}