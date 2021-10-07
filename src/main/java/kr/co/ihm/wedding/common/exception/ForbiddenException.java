package kr.co.ihm.wedding.common.exception; /**

/**
 * @author ihmoon
 * @desc Forbidden Exception 정의
 */
@SuppressWarnings("serial")
public class ForbiddenException extends RuntimeException {

    /**
     * 생성자
     * @param message
     */
    public ForbiddenException(String message) {
        super(message);
    }
}
