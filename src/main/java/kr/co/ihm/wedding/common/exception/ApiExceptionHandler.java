package kr.co.ihm.wedding.common.exception;
import kr.co.ihm.wedding.common.ResponseObject;
import kr.co.ihm.wedding.common.ServiceConstant;
import kr.co.ihm.wedding.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ihmoon
 * @desc REST api 에 대한 Exception 처리
 */
@ControllerAdvice
@Controller
public class ApiExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
    
    /**
     * 지정하지 않은 Exception 발생 시 500
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleException(Exception ex) {

        final String message = ex.getMessage();
        logger.error("Exception: {}", message);

        ResponseObject errorObject = ResponseObject.builder(ServiceConstant.HttpStatus.INTERNAL_SERVER_ERROR, null, filterMessage(message)).build();
        
        return new ResponseEntity<ResponseObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Bad Request Exception 발생 시 400
     * @param ex
     * @return
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseObject> handleBadRequestException(BadRequestException ex) {

        final String message = ex.getMessage();
        logger.error("BadRequestException: {}", message);

        ResponseObject errorObject = ResponseObject.builder(ServiceConstant.HttpStatus.BAD_REQUEST, null, filterMessage(message)).build();
        
        return new ResponseEntity<ResponseObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    /**
     * Unauthorized Exception 발생 시 401
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex) {
        
        final String message = ex.getMessage();
        logger.error("UnauthorizedException: {}", message);

        ResponseObject errorObject = ResponseObject.builder(ServiceConstant.HttpStatus.UNAUTHORIZED, null, filterMessage(message)).build();
        
        return new ResponseEntity<ResponseObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Forbidden Exception 발생 시 403
     * @param ex
     * @return
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(ForbiddenException ex) {
        
        final String message = ex.getMessage();
        logger.error("ForbiddenException: {}", message);

        ResponseObject errorObject = ResponseObject.builder(ServiceConstant.HttpStatus.FORBIDDEN, null, filterMessage(message)).build();
        
        return new ResponseEntity<ResponseObject>(errorObject, HttpStatus.FORBIDDEN);
    }

    /**
     * Not Found Exception 발생 시 404
     * @param ex
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        
        final String message = ex.getMessage();
        logger.error("NotFoundException: {}", message);

        ResponseObject errorObject = ResponseObject.builder(ServiceConstant.HttpStatus.NOT_FOUND, null, filterMessage(message)).build();
        
        return new ResponseEntity<ResponseObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    /**
     * Conflict 발생 시 409
     * @param ex
     * @return
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(ConflictException ex) {
        
        final String message = ex.getMessage();
        logger.error("ConflictException: {}", message);

        ResponseObject errorObject = ResponseObject.builder(ServiceConstant.HttpStatus.CONFLICT, null, filterMessage(message)).build();
        
        return new ResponseEntity<ResponseObject>(errorObject, HttpStatus.CONFLICT);
    }

    /**
     * 외부 API 호출 오류 발생 시 Internal Server Error로 처리 500
     * @param ex
     * @return
     */
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<?> handleInternalServerErrorException(InternalServerErrorException ex) {
        
        final String message = ex.getMessage();
        logger.error("InternalServerErrorException: {}", message);

        ResponseObject errorObject = ResponseObject.builder(ServiceConstant.HttpStatus.INTERNAL_SERVER_ERROR, null, filterMessage(message)).build();
        
        return new ResponseEntity<ResponseObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * ServiceUnavailable 발생 시 503
     * @param ex
     * @return
     */
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<?> handleServiceUnavailableException(ServiceUnavailableException ex) {
        
        final String message = ex.getMessage();
        logger.error("ServiceUnavailableException: {}", message);

        ResponseObject errorObject = ResponseObject.builder(ServiceConstant.HttpStatus.SERVICE_UNAVAILABLE, null, filterMessage(message)).build();
        
        return new ResponseEntity<ResponseObject>(errorObject, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * message size 25 이상일 경우 message filter 처리
     * @param message
     * @return
     */
    private String filterMessage(String message) {

        if (!StringUtil.isEmpty(message) && 25 <= message.length()) {
            message = null;
        }

        return message;
    }
}
