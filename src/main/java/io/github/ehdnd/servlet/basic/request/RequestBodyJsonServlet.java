package io.github.ehdnd.servlet.basic.request;

import io.github.ehdnd.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.util.StreamUtils;
import tools.jackson.databind.ObjectMapper;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ServletInputStream inputStream = req.getInputStream();
    String mesageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    System.out.println("mesageBody = " + mesageBody);

    HelloData helloData = objectMapper.readValue(mesageBody, HelloData.class);

    System.out.println("helloData.username = " + helloData.getUsername());
    System.out.println("helloData.age = " + helloData.getAge());

    resp.getWriter().write("ok");
  }
}
