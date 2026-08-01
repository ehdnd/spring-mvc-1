package io.github.ehdnd.servlet.web.frontcontroller.v4.controller;

import io.github.ehdnd.servlet.domain.member.Member;
import io.github.ehdnd.servlet.domain.member.MemberRepository;
import io.github.ehdnd.servlet.web.frontcontroller.v4.ControllerV4;
import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public String process(Map<String, String> paramMap, Map<String, Object> model) {
    List<Member> members = memberRepository.findAll();

    // 이미 frontController 에서 만들어준 model 에 넣기만 하자
    model.put("members", members);

    return "members";
  }
}
