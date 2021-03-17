package com.springboot.pjt1.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pjt1.member.service.AuthService;
import com.springboot.pjt1.member.service.CookieUtil;
import com.springboot.pjt1.member.service.JwtUtil;
import com.springboot.pjt1.member.service.RedisUtil;
import com.springboot.pjt1.repository.jpa.dto.Member;
import com.springboot.pjt1.repository.jpa.dto.Response;
import com.springboot.pjt1.repository.jpa.dto.request.RequestChangePassword1;
import com.springboot.pjt1.repository.jpa.dto.request.RequestChangePassword2;
import com.springboot.pjt1.repository.jpa.dto.request.RequestLoginUser;
import com.springboot.pjt1.repository.jpa.dto.request.RequestVerifyEmail;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = { "*" }, allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthService authService;
    @Autowired
    private CookieUtil cookieUtil;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "회원 가입을 한다.", response = Response.class)
    @PostMapping("/signup")
    public Response signUpUser(@RequestBody Member member) {
        try {
            authService.signUpUser(member);
            return new Response("success", "회원가입을 성공적으로 완료했습니다.", null);
        } catch (Exception e) {
            return new Response("error", "회원가입을 하는 도중 오류가 발생했습니다.", null);
        }
    }
    
    @ApiOperation(value = "네이버 로그인을 한다.", response = Response.class)
    @PostMapping("/login/naver")
    public Response naverLogin(@RequestBody Member member,
                          HttpServletRequest req,
                          HttpServletResponse res) {
        try {
            Member isMember = authService.socialFindByMemberId(member.getMemberId());
            if(isMember == null) {
            	authService.signUpUser(member);
            	member = authService.findByMemberId(member.getMemberId());
            }
            final String token = jwtUtil.generateToken(member);
            final String refreshJwt = jwtUtil.generateRefreshToken(member);
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
            redisUtil.setDataExpire(refreshJwt, member.getMemberId(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token", token);
            map.put("member", member);
            res.addCookie(accessToken);
            res.addCookie(refreshToken);
            return new Response("success", "로그인에 성공했습니다.", map);
        } catch (Exception e) {
            return new Response("error", "로그인에 실패했습니다.", e.getMessage());
        }
    }
    
    
    @ApiOperation(value = "일반 로그인을 한다.", response = Response.class)
    @PostMapping("/login")
    public Response login(@RequestBody RequestLoginUser user,
                          HttpServletRequest req,
                          HttpServletResponse res) {
        try {
            final Member member = authService.loginUser(user.getMemberId(), user.getMemberPassword());
            final String token = jwtUtil.generateToken(member);
            final String refreshJwt = jwtUtil.generateRefreshToken(member);
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
            redisUtil.setDataExpire(refreshJwt, member.getMemberId(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
            res.addCookie(accessToken);
            res.addCookie(refreshToken);
            return new Response("success", "로그인에 성공했습니다.", token);
        } catch (Exception e) {
            return new Response("error", "로그인에 실패했습니다.", e.getMessage());
        }
    }
    
    @ApiOperation(value = "로그아웃을 한다.", response = Response.class)
    @GetMapping("/logout")
    public void logout(HttpServletRequest req,
                          HttpServletResponse res) {
            final String token = "";
            final String refreshJwt = "";
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
            res.addCookie(accessToken);
            res.addCookie(refreshToken);
    }
    
    @GetMapping("/size")
    public Response size() {
        Response response;
        try {
        	return new Response("success", "멤버사이즈를 불러오는데 성공했습니다.", authService.size());
        	
        } catch (Exception e) {
        	return new Response("error", "멤버 사이즈를 불러오는데 실패했습니다.", e.getMessage());
        }
    }

    @ApiOperation(value = "로그인 인증메일을 보낸다.", response = Response.class)
    @PostMapping("/verify")
    public Response verify(@RequestBody RequestVerifyEmail requestVerifyEmail, HttpServletRequest req, HttpServletResponse res) {
        Response response;
        try {
            Member member = authService.findByMemberId(requestVerifyEmail.getMemberId());
            authService.sendVerificationMail(member);
            response = new Response("success", "성공적으로 인증메일을 보냈습니다.", null);
        } catch (Exception exception) {
            response = new Response("error", "인증메일을 보내는데 문제가 발생했습니다.", exception);
        }
        return response;
    }

    @ApiOperation(value = "로그인 인증메일을 확인한다.", response = Response.class)
    @GetMapping("/verify/{key}")
    public Response getVerify(@PathVariable String key) {
        Response response;
        try {
            authService.verifyEmail(key);
            response = new Response("success", "성공적으로 인증메일을 확인했습니다.", null);

        } catch (Exception e) {
            response = new Response("error", "인증메일을 확인하는데 실패했습니다.", null);
        }
        return response;
    }

    @GetMapping("/password/{key}")
    public Response isPasswordUUIdValidate(@PathVariable String key) {
        Response response;
        try {
            if (authService.isPasswordUuidValidate(key))
                response = new Response("success", "정상적인 접근입니다.", null);
            else
                response = new Response("error", "유효하지 않은 Key값입니다.", null);
        } catch (Exception e) {
            response = new Response("error", "유효하지 않은 key값입니다.", null);
        }
        return response;
    }

    @PostMapping("/password")
    public Response requestChangePassword(@RequestBody RequestChangePassword1 requestChangePassword1) {
        Response response;
        try {
            Member member = authService.findByMemberId(requestChangePassword1.getMemberId());
            if (!member.getMemberId().equals(requestChangePassword1.getMemberId())) throw new NoSuchFieldException("");
            authService.requestChangePassword(member);
            response = new Response("success", "성공적으로 사용자의 비밀번호 변경요청을 수행했습니다.", null);
        } catch (NoSuchFieldException e) {
            response = new Response("error", "사용자 정보를 조회할 수 없습니다.", null);
        } catch (Exception e) {
            response = new Response("error", "비밀번호 변경 요청을 할 수 없습니다.", null);
        }
        return response;
    }

    @PutMapping("/password")
    public Response changePassword(@RequestBody RequestChangePassword2 requestChangePassword2) {
        Response response;
        try{
            Member member = authService.findByMemberId(requestChangePassword2.getMemberId());
            authService.changePassword(member,requestChangePassword2.getMemberPassword());
            response = new Response("success","성공적으로 사용자의 비밀번호를 변경했습니다.",null);
        }catch(Exception e){
            response = new Response("error","사용자의 비밀번호를 변경할 수 없었습니다.",null);
        }
        return response;

    }

}
