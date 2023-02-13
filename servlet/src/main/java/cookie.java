import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html; charset = utf-8");

        PrintWriter writer = resp.getWriter();

        //cookie，服务器从客户端中获取
        Cookie[] cookies = req.getCookies();//这里返回的是数组，说明Cookie可能存在多个
        if (cookies == null) {
            writer.print("这是您第一次访问本站！");
        } else {
            writer.print("您上一次访问本站的时间是：" );
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if ("lastLoginTime".equals(cookie.getName())) {
                    long lastLoginTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastLoginTime);
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    writer.print(sd.format(date));
                }
            }
        }
        //添加 or 更新
        Cookie cookie = new Cookie("lastLoginTime", "" + System.currentTimeMillis());

        //设置cookie为1天
//        cookie.setMaxAge(24*60*60);
        //设置有效期为0
//        cookie.setMaxAge(0);
        //防止网络传输中文乱码问题
//        Cookie cookie = new Cookie("name", URLEncoder.encode("狂神", "UTF-8"));
//        writer.print(URLDecoder.decode(cookie.getValue(), "UTF-8"));
        resp.addCookie(cookie);
        writer.flush();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}