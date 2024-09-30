package top.soft.class03response.response;

/**
 * @author Administrator
 * @description: TODO
 * @date 2024/9/28 14:55
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/res")
public class ResponseTypeDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        switch (type) {
            case "img":
                getImage(req, resp);
                break;
                case "":
                    break;
            default:
                // 这里可以添加其他类型的处理
                break;
        }
    }


    private void getImage(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        String realPath = request.getServletContext().getRealPath("/image/photo.png");
        File file = new File(realPath);
        InputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        int read;
        while ((read = in.read()) != -1) {
            out.write(read);
        }
        out.flush();
        out.close();
    }

}