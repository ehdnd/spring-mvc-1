package io.github.ehdnd.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 다형성을 활용하기 위해서
public interface ControllerV1 {

  void process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException;
}
