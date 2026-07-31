package io.github.ehdnd.servlet.basic.response;

import io.github.ehdnd.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import tools.jackson.databind.ObjectMapper;


@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("utf-8");

    HelloData helloData = new HelloData();
    helloData.setUsername("kim");
    helloData.setAge(10);

    String result = objectMapper.writeValueAsString(helloData);
    resp.getWriter().write(result);
  }
}
