import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        String[] hobbies = req.getParameterValues("hobbies");

        System.out.println("======================");
        System.out.println(username);
        System.out.println(pwd);
        //后台接收中文乱码问题
        System.out.println(Arrays.toString(hobbies));
        System.out.println("======================");
        //通过请求转发
        // 这里的/代表当天的web应用
        req.getRequestDispatcher("success.jsp").forward(req, resp);

    }
}