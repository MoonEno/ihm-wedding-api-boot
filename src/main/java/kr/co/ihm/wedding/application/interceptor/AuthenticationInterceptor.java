package kr.co.ihm.wedding.application.interceptor; /**
 * (주)오픈잇 | https://www.openit.co.kr
 * Copyright (c)2006-2021, openit Inc.
 * All right reserved.
 */

import com.nimbusds.jose.JOSEException;
import kr.co.ihm.wedding.api.wd.guest.service.GuestService;
import kr.co.ihm.wedding.common.ServiceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Enumeration;

/**
 * @author hdkim
 * @desc Authentication Interceptor
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Autowired
    GuestService guestService;

//    @Autowired
//    UserService userService;

//    @Value("${spring.profiles.active}")
//    private String PROFILE;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessUrl = request.getRequestURL().toString(); // 접근 URL
        String userAgent = request.getHeader("User-Agent"); // User-Agent

        // 리다이렉트 URL (로그인 화면)
        // String redirectUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/login";

        if (request.getQueryString() != null) {
            accessUrl += "?" + request.getQueryString();
        }

        logger.info("##################################################");
        logger.info("# Access URL: {}", accessUrl);
        logger.info("# User-Agent: {}", userAgent);

        // 토큰
        final String jwtToken = request.getHeader(ServiceConstant.Keys.AUTHORIZATION);

        //        if (PROFILE.equals("local") || PROFILE.equals("dev")) {
//            return true;
//        }

//        if (!isValidToken(jwtToken)) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {
    }

    /**
     * Ajax(API) 호출 여부
     * @param request
     * @return
     */
    @SuppressWarnings("unused")
    private boolean isRestApiRequest(HttpServletRequest request) {

        String header = request.getHeader(ServiceConstant.Keys.X_REQUESTED_WITH);

        for (Enumeration<String> e =request.getHeaderNames(); e.hasMoreElements();)
            logger.info("{}", e.nextElement());

        if (ServiceConstant.Keys.XML_HTTP_REQUEST.equals(header)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 토큰 검증
     * @param jwtToken
     * @param session
     * @return
     * @throws JOSEException
     * @throws ParseException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    private boolean isValidToken(String jwtToken) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {

//        if (StringUtil.isEmpty(jwtToken)) {             // 토큰 빈문자 검사
//            logger.error("$ {} -> Token is null.", ServiceConstant.ResponseMessage.ERR_AUTH_012);       // Does not exist token
//            return false;
//        } else if (!jwtToken.startsWith("Bearer ")) {   // Bearer Scheme 검사
//            logger.error("$ {} -> Authentication scheme is not Bearer.", ServiceConstant.ResponseMessage.ERR_AUTH_015); // Not Bearer scheme
//            return false;
//        } else {
//
//            jwtToken = jwtToken.replaceAll("Bearer ", "");
//
//            // 정상 토큰 검사
//            if (!Tokenizer.verify(jwtToken)) {
//                logger.error("$ {} -> Token is Invalid.", ServiceConstant.ResponseMessage.ERR_AUTH_013);
//                return false;
//            }
//
//            final String userId = (String)Tokenizer.get("userId", jwtToken);
//
//            // DB 토큰 비교
////            LoginDTO loginDto = LoginDTO.builder().userId(userId).tokenVal(jwtToken).build();
////            loginDto = loginService.selectUserByToken(loginDto);
////
////            if (loginDto == null || !userId.equals(loginDto.getUserId())) {
////                return false;
////            }
//        }
        return true;
    }
}
