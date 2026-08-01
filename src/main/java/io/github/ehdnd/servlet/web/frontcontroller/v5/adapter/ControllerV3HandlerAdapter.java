package io.github.ehdnd.servlet.web.frontcontroller.v5.adapter;

import io.github.ehdnd.servlet.web.frontcontroller.ModelView;
import io.github.ehdnd.servlet.web.frontcontroller.v3.ControllerV3;
import io.github.ehdnd.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.NonNull;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    return (handler instanceof ControllerV3);
  }

  @Override
  public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler)
      throws ServletException, IOException {

    // 실제 구동
    ControllerV3 controller = (ControllerV3) handler;

    Map<String, String> paramMap = createParamMap(req);

    return controller.process(paramMap);
  }


  private static @NonNull Map<String, String> createParamMap(HttpServletRequest req) {
    Map<String, String> paramMap = new HashMap<>();
    req.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
    return paramMap;
  }
}
