package io.github.ehdnd.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

  /**
   * 모델 생성을 frontController에서 해준다
   *
   * @param paramMap
   * @param model
   * @return viewName
   */
  String process(Map<String, String> paramMap, Map<String, Object> model);
}
