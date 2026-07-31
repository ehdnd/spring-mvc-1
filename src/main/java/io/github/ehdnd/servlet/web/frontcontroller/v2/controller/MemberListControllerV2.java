package io.github.ehdnd.servlet.web.frontcontroller.v2.controller;

import io.github.ehdnd.servlet.domain.member.Member;
import io.github.ehdnd.servlet.domain.member.MemberRepository;
import io.github.ehdnd.servlet.web.frontcontroller.MyView;
import io.github.ehdnd.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public MyView process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Member> members = memberRepository.findAll();
    req.setAttribute("members", members);
    
    return new MyView("/WEB-INF/views/members.jsp");
  }
}
