package com.my.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "SendImageServlet", urlPatterns = {"/image"})
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();

        try (InputStream is = sc.getResourceAsStream("/Image/image.jpg")) {


            OutputStream os = resp.getOutputStream();

            if (is == null) {

                resp.setContentType("text/plain");
                os.write("Failed to send image".getBytes());
            } else {

                byte[] buffer = new byte[1024];
                int bytesRead;

                resp.setContentType("image/png");

                while ((bytesRead = is.read(buffer)) != -1) {

                    os.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}
