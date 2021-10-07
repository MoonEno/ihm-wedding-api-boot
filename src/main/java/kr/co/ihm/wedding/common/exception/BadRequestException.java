package kr.co.ihm.wedding.common.exception;
/**
 * @author ihmoon
 * @desc BadRequest Exception 정의
 */
@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

    /**
     * 생성자
     * @param message
     */
    public BadRequestException(String message) {
        super(message);
    }
}
