package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 登录过滤器
 *　　拥有Session是否失效和用户是否登录2个条件判断
 *　　如果是ajax请求则设置session超时
 * @author Merlin.Ma
 *
 */
public class LoginFilter implements Filter{
  private String redirectUrl = "/login.html";
  private String sessionKey = "username";
  @Override
  public void destroy() {
  }
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse rep = (HttpServletResponse) response;
    HttpSession session = req.getSession();
    if( session == null || session.getAttribute(sessionKey) == null){
      //如果判断是 AJAX 请求,直接设置为session超时
      if( req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equals("XMLHttpRequest") ) {
        rep.setHeader("sessionstatus", "timeout"); 
      } else {
        rep.sendRedirect( req.getContextPath() + redirectUrl);
      }
    }else {
      chain.doFilter(request, response);
    }   
  }
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    String url = filterConfig.getInitParameter("redirectUrl");
    String key = filterConfig.getInitParameter("sessionKey");
    redirectUrl = url == null? redirectUrl:url;
    sessionKey = key == null ? sessionKey : key ;
  }
}