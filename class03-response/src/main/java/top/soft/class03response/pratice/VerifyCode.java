package top.soft.class03response.pratice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

@WebServlet("/verifycode")
public class VerifyCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 160;
        int height = 45;

        // 创建验证码图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 创建画笔对象
        Graphics g = image.getGraphics();
        // 设置抗锯齿属性
        g.setFont(new Font("Fixedsys", Font.BOLD, 20));
        g.setColor(new Color(235, 249, 254)); // 设置背景色
        g.fillRect(0, 0, width, height);

        String str = "234567809ADSGKHxbyfz"; // 预定义的字符集
        Random random = new Random();

        // 添加噪点
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = random.nextInt(255);
            image.setRGB(x, y, (rgb << 24) | (255 << 16) | (255 << 8) | 255);
        }

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(str.length());
            // 获取随机字符
            char c = str.charAt(index);
            // 设置字体颜色
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            // 绘制验证码
            g.drawString(String.valueOf(c), width / 5 * i + 3, height / 2 - 5);
        }

        // 生成干扰线
        for (int i = 0; i <= 5; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g.drawLine(x1, y1, x2, y2);
        }

        // 应用扭曲效果
        for (int i = 0; i < height; i++) {
            int x = random.nextInt(3);
            int y = (int) (Math.sin(i * Math.PI / 18) * 3);
            image.setRGB(x, i, (new Color(255, 255, 255)).getRGB());
            image.setRGB(width - x - 1, i, (new Color(255, 255, 255)).getRGB());
        }

        // 将验证码图片展现到页面上
        resp.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", resp.getOutputStream());
        resp.getOutputStream().close();
    }
}