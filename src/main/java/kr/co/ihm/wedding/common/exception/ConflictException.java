package kr.co.ihm.wedding.common.exception;

/**
 * @author ihmoon
 * @desc Conflict Exception 정의
 */
@SuppressWarnings("serial")
public class ConflictException extends RuntimeException {

    /**
     * 생성자
     * @param message
     */
    public ConflictException(String message) {
        super(message);
    }
}
