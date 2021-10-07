package kr.co.ihm.wedding.api.wd.guest.service; /**
 * ihmoon
 * Copyright (c)2021, ihmoon 915 Inc.
 * All right reserved.
 */

import kr.co.ihm.wedding.api.wd.guest.mapper.GuestMapper;
import kr.co.ihm.wedding.api.wd.guest.model.GuestDTO;
import kr.co.ihm.wedding.api.wd.guest.model.GuestSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author ihmoon
 * @desc 카테고리 Service
 */
@Service
@Transactional
public class GuestService {

    /**
     * CommonCode Mapper
     */
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    GuestMapper guestMapper;

    public GuestDTO getTargetGuest(GuestSearchDTO vo) {
        return guestMapper.getTargetGuest(vo);
    }


//    public int postCtgImg(CtgDTO data) throws Exception {
//
//        int mainStatus = 0;
//        int subStatus = 0;
//
//        if (StringUtils.isBlank(data.getImgFileSrnoOri())) {
//            mainStatus = ServiceConstant.FileProcessType.CREATE;
//        } else {
//            mainStatus = ServiceConstant.FileProcessType.MODIFY;
//        }
//
//        if (StringUtils.isBlank(data.getSubImgFileSrnoOri())) {
//            subStatus = ServiceConstant.FileProcessType.CREATE;
//        } else {
//            subStatus = ServiceConstant.FileProcessType.MODIFY;
//        }
//
//        data.setMainYn(uploadService.processFileEvent(mainStatus, "pd", data.getOnImg()));
//
//        data.setOffYn(uploadService.processFileEvent(subStatus, "pd", data.getOffImg()));
//
//        return ctgMapper.postCtgImg(data);
//    }

}
