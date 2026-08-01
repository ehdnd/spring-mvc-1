package io.github.ehdnd.servlet.web.frontcontroller.v5;

import io.github.ehdnd.servlet.web.frontcontroller.ModelView;
import io.github.ehdnd.servlet.web.frontcontroller.MyView;
import io.github.ehdnd.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import io.github.ehdnd.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import io.github.ehdnd.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import io.github.ehdnd.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import io.github.ehdnd.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import io.github.ehdnd.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import io.github.ehdnd.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import io.github.ehdnd.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
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
    // V3
    handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    // V4
    handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
    handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
    handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
  }

  private void initHandlerAdapters() {
    handlerAdapters.add(new ControllerV3HandlerAdapter());
    handlerAdapters.add(new ControllerV4HandlerAdapter());

  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // 이제 frontController 코드를 변경할 필요가 없다. OCP.

    Object handler = getHandler(req);
    if (handler == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    MyHandlerAdapter adapter = getHandlerAdapter(handler);

    // 시작 - (중간) - 끝; ModelView 반환을 원한다.
    // adapter 가 컨트롤러의 다른 로직/리턴값을 원하는 대로 맞춰준다.
    // -> 아래 view 처리 코드는 컨트롤러 종류와 무관하게 동일하다

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

  private static MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }
}
