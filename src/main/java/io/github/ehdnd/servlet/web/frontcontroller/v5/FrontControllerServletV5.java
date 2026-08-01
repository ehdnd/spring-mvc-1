package io.github.ehdnd.servlet.web.frontcontroller.v5;

import io.github.ehdnd.servlet.web.frontcontroller.ModelView;
import io.github.ehdnd.servlet.web.frontcontroller.MyView;
import io.github.ehdnd.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import io.github.ehdnd.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import io.github.ehdnd.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import io.github.ehdnd.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jspecify.annotations.NonNull;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

  // Object - 아무 컨트롤러나 다 들어갈 수 있어야 한다.
  private final Map<String, Object> handlerMappingMap = new HashMap<>();
  private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

  public FrontControllerServletV5() {
    initHandlerMappingMap();
    initHandlerAdapters();
  }

  private void initHandlerMappingMap() {
    handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
  }

  private void initHandlerAdapters() {
    handlerAdapters.add(new ControllerV3HandlerAdapter());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Object handler = getHandler(req);
    if (handler == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    MyHandlerAdapter adapter = getHandlerAdapter(handler);

    ModelView modelView = adapter.handle(req, resp, handler);

    String viewName = modelView.getViewName();
    MyView view = viewResolver(viewName);

    view.render(modelView.getModel(), req, resp);
  }

  private MyHandlerAdapter getHandlerAdapter(Object handler) {
    for (MyHandlerAdapter adapter : handlerAdapters) {
      if (adapter.supports(handler)) {
        return adapter;
      }
    }

    throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다. handler = " + handler);
  }

  private Object getHandler(HttpServletRequest req) {
    String requestURI = req.getRequestURI();
    return handlerMappingMap.get(requestURI);
  }

  private static @NonNull MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }
}
