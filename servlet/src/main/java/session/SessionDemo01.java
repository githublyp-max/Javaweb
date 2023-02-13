package session;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取session
        HttpSession session = req.getSession();
        //给session存值
        session.setAttribute("name", "狂神");
        //获取session的id
        String id = session.getId();
        if (session.isNew()) {
            resp.getWriter().write("session创建成功，id:" + id);
        } else {
            resp.getWriter().write("session已经在服务器中存在了，id:" + id);
        }
        resp.getWriter().flush();
        //session创建的时候做了什么,session创建时回话没有，而在下面中的cookie出现了，这就意味着执行了下面这段代码，
        // 只有火狐浏览器能看到
        Cookie cookie = new Cookie("JSESSIONID", id);
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
