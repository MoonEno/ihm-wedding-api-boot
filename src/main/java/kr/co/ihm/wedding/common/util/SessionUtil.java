package kr.co.ihm.wedding.common.util;

import kr.co.ihm.wedding.common.ServiceConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * @author ihmoon
 * @desc Session 관련 Common Utils
 */
public class SessionUtil {
    
    /**
     * 사용자 세션 사용자ID
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getUserId(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session != null) {
            Map<String, Object> userMap = (Map<String, Object>)session.getAttribute(ServiceConstant.Keys.USER_INFO);

            if (userMap != null) {
                return userMap.get("userId").toString();
            } else {
                return "MD001";
            }
        }

        return null;
    }

    /**
     * 사용자 세션 정보
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getUserInfo(HttpServletRequest request) {
        
        HttpSession session = request.getSession();

        if (session != null) {
            return (Map<String, Object>)session.getAttribute(ServiceConstant.Keys.USER_INFO);
        }

        return null;
    }
}
