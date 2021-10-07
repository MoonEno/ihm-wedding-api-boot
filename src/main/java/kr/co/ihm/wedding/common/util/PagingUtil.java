package kr.co.ihm.wedding.common.util; /**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2021, openit Inc.
 * All right reserved.
 */


import kr.co.ihm.wedding.common.BasePage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ihmoon
 * @desc 페이징이 필요한 Paging Util
 */
public class PagingUtil {
    
    public static Map<String, Object> doPaging(BasePage pageData) {

        int currentPage = pageData.getPage();
        int recordsPerPage = pageData.getRecordsPerPage();
        int startIndex = ((currentPage - 1) * recordsPerPage);
        int endIndex = currentPage * recordsPerPage;

        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("startIndex", startIndex);
        pageMap.put("endIndex", endIndex);
        pageMap.put("recordsPerPage", recordsPerPage);

        pageData.setStartIndex(startIndex);
        pageData.setEndIndex(endIndex);
        return pageMap;
    }
}
