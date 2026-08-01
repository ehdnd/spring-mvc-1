package io.github.ehdnd.servlet.web.frontcontroller.v3;

import io.github.ehdnd.servlet.web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {

  // 요청 파라미터 정보를 Map으로 넘기자 -> 파라미터 사용한 작업 진행해라
  ModelView process(Map<String, String> paramMap);

}
