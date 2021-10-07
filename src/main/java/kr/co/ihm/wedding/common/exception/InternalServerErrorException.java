package kr.co.ihm.wedding.common.exception;

/**
 * @author ihmoon
 * @desc Internal Server Error Exception 정의
 */
@SuppressWarnings("serial")
public class InternalServerErrorException extends RuntimeException {

    /**
     * 생성자
     * @param message
     */
    public InternalServerErrorException(String message) {
        super(message);
    }
}
