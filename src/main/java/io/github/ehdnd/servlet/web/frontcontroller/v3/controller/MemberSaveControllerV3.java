package io.github.ehdnd.servlet.web.frontcontroller.v3.controller;

import io.github.ehdnd.servlet.domain.member.Member;
import io.github.ehdnd.servlet.domain.member.MemberRepository;
import io.github.ehdnd.servlet.web.frontcontroller.ModelView;
import io.github.ehdnd.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public ModelView process(Map<String, String> paramMap) {
    String username = paramMap.get("username");
    int age = Integer.parseInt(paramMap.get("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

    // 파라미터 사용해 작업 한 뒤 view 에서 필요한 정보 ModelView 객체로 전달
    ModelView modelView = new ModelView("save-result");
    modelView.getModel().put("member", member);
    return modelView;
  }
}
