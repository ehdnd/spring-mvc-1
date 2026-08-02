package io.github.ehdnd.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 1. 스프링이 자동으로 스프링 빈으로 등록 (@Component)
// 2. 애노테이션 기반 컨트롤러로 인식 -> RequestMappingHandlerMapping 에서 인식해서 사용 가능
@Controller
public class SpringMemberFormControllerV1 {

  @RequestMapping("/springmvc/v1/members/new-form")
  public ModelAndView process() {
    System.out.println("SpringMemberFormControllerV1.process");
    return new ModelAndView("new-form");
  }
}
