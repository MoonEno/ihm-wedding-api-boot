package kr.co.ihm.wedding.common;

public class ServiceConstant {

    /**
     * HttpStatus 별 응답값
     */
    public class HttpStatus {

        public static final int OK                      = 200;
        public static final int BAD_REQUEST             = 400;
        public static final int UNAUTHORIZED            = 401;
        public static final int FORBIDDEN               = 403;
        public static final int NOT_FOUND               = 404;
        public static final int CONFLICT                = 409;
        public static final int INTERNAL_SERVER_ERROR   = 500;
        public static final int SERVICE_UNAVAILABLE     = 503;

    }

    /**
     * 응답 메시지
     */
    public class ResponseMessage {
        
        public static final String FAIL                     = "FAIL";
        public static final String SUCCESS                  = "SUCCESS";

        // CRUD 관련
        public static final String ERR_DBMS_001             = "ERR_DBMS_001";       // INSERT ERROR
        public static final String ERR_DBMS_002             = "ERR_DBMS_002";       // SELECT ERROR
        public static final String ERR_DBMS_003             = "ERR_DBMS_003";       // UPDATE ERROR
        public static final String ERR_DBMS_004             = "ERR_DBMS_004";       // DELETE ERROR

        // AUTH(인증) 관련
        public static final String ERR_AUTH_001             = "ERR_AUTH_001";       // Does not exist User (사용자 없음)
        public static final String ERR_AUTH_002             = "ERR_AUTH_002";       // Unauthorized User (비인가 사용자)
        public static final String ERR_AUTH_003             = "ERR_AUTH_003";       // No UserId (사용자ID 누락)
        public static final String ERR_AUTH_011             = "ERR_AUTH_011";       // Does not issue token (토큰발행 오류)
        public static final String ERR_AUTH_012             = "ERR_AUTH_012";       // Does not exist token (토큰없음)
        public static final String ERR_AUTH_013             = "ERR_AUTH_013";       // Invalid token (토큰 유효성 오류)
        public static final String ERR_AUTH_014             = "ERR_AUTH_014";       // Expired token (토큰 만료 오류)
        public static final String ERR_AUTH_015             = "ERR_AUTH_015";       // Not Bearer scheme
        public static final String ERR_AUTH_021             = "ERR_AUTH_021";       // Unavailable Legacy
        public static final String ERR_AUTH_022             = "ERR_AUTH_022";       // Unavailable Legacy X-Auth Value
        public static final String ERR_AUTH_023             = "ERR_AUTH_023";       // 비밀번호 불일치 (비밀번호 변경)
        public static final String ERR_AUTH_024             = "ERR_AUTH_024";       // 비밀번호 이전과 동일 (비밀번호 변경)
        public static final String ERR_AUTH_025             = "ERR_AUTH_025";       // 이전 비밀번호 오류 (비밀번호 변경)

        // 파일 관련
        public static final String ERR_FILE_001             = "ERR_FILE_001";       // NOT_ALLOWED_FILE_EXT
        public static final String ERR_FILE_002             = "ERR_FILE_002";       // DOES_NOT_EXIST_FILE
        
        // 배송지 관련
        public static final String ERR_DLVR_001             = "ERR_DLVR_001";       // Does not exist repEst
        public static final String ERR_DLVR_002             = "ERR_DLVR_002";       // 배송업체 정보 없음(송장번호 혹은 배송업체코드)
        
        // 주문상품 관련
        public static final String ERR_ORDR_PROD_001        = "ERR_ORDR_PROD_001";  // Sold out
        public static final String ERR_ORDR_PROD_002        = "ERR_ORDR_PROD_002";  // No stock
    }

    /**
     * 응답 키
     */
    public class ResponseKey {
        
        public static final String TOT_CNT                  = "totalCount";
        public static final String DATA                     = "data";
        public static final String RSLT_CD                  = "resultCode";
        public static final String RSLT_DT                  = "resultData";
        public static final String RSLT_MS                  = "resultMessage";
    }

    /**
     * 서비스 키
     */
    public class Keys {

        public static final String USER_INFO                = "UserInfo";               // User Info
        public static final String AUTHORIZATION            = "Authorization";          // Authorization Key
        public static final String X_REQUESTED_WITH         = "X-Requested-With";       // X-Requested-With Key
        public static final String XML_HTTP_REQUEST         = "XMLHttpRequest";         // XMLHttpRequest
        public static final String X_AUTH                   = "X-Auth";                 // X-Auth
    }

    /**
     * 마스킹 타입
     */
    public enum MaskType {        
        ID(3),
        NCK_NM(2);
    	
    	private int limit;
    	
    	MaskType(int limit) {
    		this.limit = limit;
    	}
    	
    	public int getLimit() {
    		return this.limit;
    	}
    }
    /**
     * File Process Type
     */
    public class FileProcessType {
        
        public static final int CREATE                     = 1;     // 파일 처리 타입 생성
        public static final int MODIFY                     = 2;     // 파일 처리 타입 수정
        public static final int REMOVE                     = 3;     // 파일 처리 타입 삭제
    }
    
    /**
     * 정렬유형
     *
     */
    public class SortTyp {
    	public static final String RCNT                     = "001";  // 최근순
    	public static final String RVEW_UPR                 = "002";  // 평점 높은순
    	public static final String RVEW_LOW                 = "003";  // 평점 낮은순
    }

    /**
     * 공통코드
     */
    public class ComCd {

        // 사용자구분코드
    	public static final String USER_DIV_GRP_CD                 = "0001";           // 사용자구분그룹코드
        public static final String USER_DIV_ADMIN                  = "00010001";       // 시스템 Admin
        public static final String USER_DIV_PTNID                  = "00010002";       // 파트너 ID
        public static final String USER_DIV_PTNSID                 = "00010003";       // 파트너 서브 ID
        public static final String USER_DIV_NORMAL                 = "00010004";       // 일반 사용자
        
        // 파트너상태구분코드
        public static final String PTN_STAT_DIV_GRP_CD             = "0003";           // 파트너상태구분그룹코드
        public static final String PTN_STAT_DIV_SCRB_RQS_WAIT      = "00030001";       // 가입신청대기
        public static final String PTN_STAT_DIV_SRVC               = "00030002";       // 서비스중
        public static final String PTN_STAT_DIV_CTRT_EXPRN         = "00030003";       // 계약만료
        public static final String PTN_STAT_DIV_CTRT_END           = "00030004";       // 계약파기
        
        // 게시판구분코드
        public static final String BBRD_DIV_GRP_CD                 = "0004";           // 게시판구분그룹코드
        public static final String BBRD_DIV_OPM_ANCE               = "00040001";       // 오픈몰공지사항
        public static final String BBRD_DIV_PTN_ANCE               = "00040002";       // 파트너공지사항
        public static final String BBRD_DIV_MNM_ANCE               = "00040003";       // 미니샵공지사항
        public static final String BBRD_DIV_STY                    = "00040004";       // 스토리
        public static final String BBRD_DIV_FAQ                    = "00040005";       // FAQ
        public static final String BBRD_DIV_PLN                    = "00040006";       // 기획전

        // 파일유형코드
        public static final String FILE_TYP_GRP_CD                 = "1002";           // 파일유형그룹코드
        public static final String FILE_TYP_IMG                    = "10020001";       // 이미지
        public static final String FILE_TYP_DOC                    = "10020002";       // 문서
        public static final String FILE_TYP_ETC                    = "10020003";       // 기타
        
        // 부가세구분코드
        public static final String VAT_DIV_GRP_CD                  = "1003";           // 부가세구분그룹코드
        public static final String VAT_DIV_TAX                     = "10030001";       // 과세
        public static final String VAT_DIV_TAX_FREE                = "10030002";       // 비과세
        
        // 카테고리구분코드
        public static final String CTG_DIV_GRP_CD                  = "1004";           // 카테고리구분그룹코드
        public static final String CTG_DIV_BSC                     = "10040001";       // 기본
        public static final String CTG_DIV_BRD                     = "10040002";       // 브랜드
        public static final String CTG_DIV_THM                     = "10040003";       // 테마
        
        // 사업자구분코드
        public static final String BIZR_DIV_GRP_CD                 = "1005";           // 사업자구분그룹코드
        public static final String BIZR_DIV_CORP_BIZR              = "10050001";       // 법인사업자
        public static final String BIZR_DIV_INDV_BIZR              = "10050001";       // 개인사업자
        
        // 배송구분코드
        public static final String DLVR_DIV_GRP_CD                 = "1006";           // 배송구분그룹코드
        
        // 상품이미지구분코드
        public static final String PROD_IMG_DIV_GRP_CD             = "1007";           // 상품이미지구분그룹코드
        public static final String PROD_IMG_DIV_REP                = "10070001";       // 대표이미지
        public static final String PROD_IMG_DIV_ADD                = "10070002";       // 추가이미지
        
        // KC인증구분코드
        public static final String KC_AUTH_DIV_GRP_CD              = "1008";           // KC인증구분그룹코드
        
        // 상품등록상태구분코드
        public static final String PROD_REG_STAT_DIV_GRP_CD        = "1009";           // 상품등록상태구분그룹코드
        public static final String PROD_REG_STAT_DIV_SALE          = "10090003";       // 판매중
        public static final String PROD_REG_STAT_DIV_SOLD_OUT      = "10090004";       // 품절
        public static final String PROD_REG_STAT_DIV_SALE_END      = "10090005";       // 판매종료
        
        // 판매방법구분코드
        public static final String SALE_MTHD_DIV_GRP_CD            = "1010";           // 판매방법구분그룹코드
        public static final String SALE_MTHD_DIV_GNRL              = "10100001";       // 일반
        public static final String SALE_MTHD_DIV_PRE_RSV           = "10100002";       // 사전예약

        // 상품주문상태구분코드
        public static final String PROD_ORDR_STAT_DIV_GRP_CD       = "1011";           // 상품주문상태구분그룹코드
        public static final String PROD_ORDR_STAT_DIV_TMP          = "10110001";       // 주문임시저장
        public static final String PROD_ORDR_STAT_DIV_FNSH         = "10110002";       // 주문완료
        public static final String PROD_ORDR_STAT_DIV_CNCL         = "10110003";       // 주문취소
        public static final String PROD_ORDR_STAT_DIV_CNCL_OPING   = "10110004";       // 주문취소처리중
        public static final String PROD_ORDR_STAT_DIV_CNCL_FNSH    = "10110005";       // 주문취소완료
        public static final String PROD_ORDR_STAT_DIV_REFUND_REQ   = "10110006";       // 환불요청
        public static final String PROD_ORDR_STAT_DIV_REFUND_OPING = "10110007";       // 환불진행중
        public static final String PROD_ORDR_STAT_DIV_REFUND_FNSH  = "10110008";       // 환불완료
        public static final String PROD_ORDR_STAT_DIV_EXCH_REQ     = "10110009";       // 교환요청
        public static final String PROD_ORDR_STAT_DIV_EXCH_OPING   = "10110010";       // 교환진행중
        public static final String PROD_ORDR_STAT_DIV_EXCH_FNSH    = "10110011";       // 교환완료
        public static final String PROD_ORDR_STAT_DIV_FIX          = "10110012";       // 구매확정
        
        // 결제상태구분코드
        public static final String PAY_STAT_DIV_GRP_CD             = "1012";            // 결제상태구분그룹코드
        public static final String PAY_STAT_DIV_WAIT               = "10120001";        // 결제대기
        public static final String PAY_STAT_DIV_FNSH               = "10120002";        // 결제완료
        public static final String PAY_STAT_DIV_APRV_BF            = "10120003";        // 결제승인 이전
        public static final String PAY_STAT_DIV_APRV_OPING         = "10120004";        // 결제승인 진행중
        public static final String PAY_STAT_DIV_CNCL               = "10120005";        // 결제취소
        public static final String PAY_STAT_DIV_CNCL_FAIL          = "10120006";        // 결제취소 실패
        public static final String PAY_STAT_DIV_CNCL_OPING         = "10120007";        // 결제취소 진행중
        public static final String PAY_STAT_DIV_ERR                = "10120008";        // 오류로 인한 실패
        public static final String PAY_STAT_DIV_APRV_FAIL          = "10120009";        // 결제승인 실패


        // 배송상태구분코드
        public static final String DLVR_STAT_DIV_GRP_CD             = "1013";            // 배송상태구분그룹코드
        public static final String DLVR_STAT_DIV_FNSH               = "10130001";        // 결제완료
        public static final String DLVR_STAT_DIV_PRDT_RD            = "10130002";        // 상품준비중
        public static final String DLVR_STAT_DIV_DLVR_BF            = "10130003";        // 배송준비중
        public static final String DLVR_STAT_DIV_DLVR_LV1           = "10130004";        // 집화완료
        public static final String DLVR_STAT_DIV_DLVR_LV2           = "10130005";        // 배송중
        public static final String DLVR_STAT_DIV_DLVR_LV3           = "10130006";        // 지점도착
        public static final String DLVR_STAT_DIV_DLVR_LV4           = "10130007";        // 배송출발
        public static final String DLVR_STAT_DIV_FIX                = "10130008";        // 배송완료

        // 주문처리구분코드
        public static final String ORDR_PROC_DIV_GRP_CD            = "1014";           // 주문처리구분그룹코드
        public static final String ORDR_PROC_DIV_REQ               = "10140001";       // 결제요청
        public static final String ORDR_PROC_DIV_FNSH              = "10140002";       // 결제완료
        public static final String ORDR_PROC_DIV_ERR               = "10140003";       // 결제오류

        // 게시글상태구분코드
        public static final String BBM_STAT_DIV_GRP_CD             = "1018";           // 게시글상태구분그룹코드
        public static final String BBM_STAT_DIV_WAIT               = "10180001";       // 게시대기
        public static final String BBM_STAT_DIV_ING                = "10180002";       // 게시중
        public static final String BBM_STAT_DIV_STOP               = "10180003";       // 게시중지
        
        // 문의유형코드
        public static final String PROD_INQ_TYP_GRP_CD             = "1019";           // 문의유형그룹코드
        public static final String PROD_INQ_TYP_PROD               = "10190001";       // 상품
        public static final String PROD_INQ_TYP_DLVR               = "10190002";       // 배송
        public static final String PROD_INQ_TYP_RETURN             = "10190003";       // 반품
        public static final String PROD_INQ_TYP_EXCH               = "10190004";       // 교환
        public static final String PROD_INQ_TYP_CNCL               = "10190005";       // 취소
        public static final String PROD_INQ_TYP_ECT                = "10190006";       // 기타
                
        // 문의사용자구분코드
        public static final String INQ_USER_DIV_GRP_CD             = "1021";           // 문의사용자구분그룹코드
        public static final String INQ_USER_DIV_BUYER              = "10210001";       // 구매자
        public static final String INQ_USER_DIV_SALER              = "10210002";       // 판매자
        public static final String INQ_USER_DIV_ADMIN              = "10210003";       // Admin
    }
}
