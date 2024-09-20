package top.soft.class01servlet;


import jakarta.servlet.*;

import java.io.IOException;

public class ServletDemo1  implements Servlet {
    /**
     *
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Servlet 初始化");

    }

    /**
     * 获取servlet配置
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 执行service方法
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().write("ServletDemo1执行");
        System.out.println("service执行");
    }

    /**
     * 获取servlet信息
     * @return
     */
    @Override
    public String getServletInfo() {
        return "";
    }

    /**
     * 执行销毁方法
     */
    @Override
    public void destroy() {

    }
}
