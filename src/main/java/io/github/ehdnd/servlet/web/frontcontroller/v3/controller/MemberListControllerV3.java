package io.github.ehdnd.servlet.web.frontcontroller.v3.controller;

import io.github.ehdnd.servlet.domain.member.Member;
import io.github.ehdnd.servlet.domain.member.MemberRepository;
import io.github.ehdnd.servlet.web.frontcontroller.ModelView;
import io.github.ehdnd.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public ModelView process(Map<String, String> paramMap) {
    List<Member> members = memberRepository.findAll();
    ModelView modelView = new ModelView("members");
    modelView.getModel().put("members", members);

    return modelView;
  }
}
