package io.github.ehdnd.servlet.web.frontcontroller.v5.adapter;

import io.github.ehdnd.servlet.web.frontcontroller.ModelView;
import io.github.ehdnd.servlet.web.frontcontroller.v4.ControllerV4;
import io.github.ehdnd.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    return (handler instanceof ControllerV4);
  }

  @Override
  public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler)
      throws ServletException, IOException {
    ControllerV4 controller = (ControllerV4) handler;

    // V4에서는 FrontController 에서 수행했다
    Map<String, String> paramMap = createParamMap(req);
    Map<String, Object> model = new HashMap<>();

    String viewName = controller.process(paramMap, model); // 이걸 바로 리턴할 수 없다

    // 어댑터 메인 역할 수행; ModelView 를 대신 생성해준다.
    ModelView modelView = new ModelView(viewName);
    modelView.setModel(model);

    return modelView;
  }

  private static Map<String, String> createParamMap(HttpServletRequest req) {
    Map<String, String> paramMap = new HashMap<>();
    req.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
    return paramMap;
  }
}
