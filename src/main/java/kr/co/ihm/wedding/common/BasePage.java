/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2021, openit Inc.
 * All right reserved.
 */
package kr.co.ihm.wedding.common;

import lombok.Data;

/**
 * @author hdkim
 * @desc Paging을 위한 DTO/VO 부모 클래스
 */
@Data
public class BasePage {

    int rnum;
    int page = 1;
    int recordsPerPage = 10;
    int startIndex;
    int endIndex;

    String keyword;
    String order;
}
