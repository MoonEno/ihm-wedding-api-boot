package kr.co.ihm.wedding.common.exception;

/**
 * @author ihmoon
 * @desc Unauthorized Exception 정의
 */
@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {

    /**
     * 생성자
     * @param message
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}
