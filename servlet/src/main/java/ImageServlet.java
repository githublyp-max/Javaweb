import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如何让浏览器三秒自动刷新一次
        resp.setHeader("refresh", "3");
        //在内存中创建一个图片
        BufferedImage bufferedImage = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        //得到图片
        Graphics2D bi = (Graphics2D) bufferedImage.getGraphics();//笔
        //设置背景颜色为白色
        bi.setColor(Color.WHITE);
        bi.fillRect(0, 0, 80, 20);
        //给图片写数据
        bi.setColor(Color.BLUE);
        bi.setFont(new Font(null, Font.BOLD, 20));
        bi.drawString(makeNum(), 0, 20);
        //告诉浏览器用图片的方式打开
        resp.setContentType("image/jpeg");
        //网站存在缓存，不让浏览器缓存
        resp.setDateHeader("Expires",0);
        resp.addHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

        ImageIO.write(bufferedImage, "jpeg", resp.getOutputStream());

    }

    /**
     * 生成随机数
     *
     * @return
     */
    private String makeNum() {
        Random random = new Random();
        String num = String.valueOf(random.nextInt(999999));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6 - num.length(); i++) {
            sb.append("0");
        }
        return sb.toString() + num;

    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
