package kr.co.ihm.wedding.common.exception; /**

/**
 * @author ihmoon
 * @desc NotFound Exception 정의
 */
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

    /**
     * 생성자
     * @param message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
