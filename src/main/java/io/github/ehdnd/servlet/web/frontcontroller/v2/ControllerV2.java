package io.github.ehdnd.servlet.web.frontcontroller.v2;

import io.github.ehdnd.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

  // V1과 다르게 MyView를 리턴
  MyView process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException;
}
