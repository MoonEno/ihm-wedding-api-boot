package kr.co.ihm.wedding.common.exception;

/**
 * @author ihmoon
 * @desc ServiceUnavailable Exception 정의
 */
@SuppressWarnings("serial")
public class ServiceUnavailableException extends RuntimeException {

    /**
     * 생성자
     * @param message
     */
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
