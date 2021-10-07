package kr.co.ihm.wedding.common;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderMethodName = "responseObjectBuilder")
public class ResponseObject {
    
    private int resultCode;
    private Object resultData;
    private String resultMessage;
    
    public static ResponseObjectBuilder builder(int resultCode) {
    	
    	if (resultCode == 0) {
    		throw new IllegalArgumentException("resultCode Missing");
    	}
    	return responseObjectBuilder().resultCode(resultCode);
    }
    
    public static ResponseObjectBuilder builder(int resultCode, Object resultData, String resultMessage) {
    	
    	if (resultCode == 0) {
    		throw new IllegalArgumentException("resultCode Missing");
    	}
    	return responseObjectBuilder().resultCode(resultCode).resultData(resultData).resultMessage(resultMessage);
    }
    
}
