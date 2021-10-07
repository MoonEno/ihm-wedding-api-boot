package kr.co.ihm.wedding.api.wd.guest.mapper;

import kr.co.ihm.wedding.api.wd.guest.model.GuestDTO;
import kr.co.ihm.wedding.api.wd.guest.model.GuestSearchDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @class GuestDao
 * @brief [M] { 청구서 Dao 구현 클래스 }
 * @author CBP-CG (1.0.0)
 */
@Repository
public class GuestMapper {

    @Autowired
    SqlSession sqlSession;

    public GuestDTO getTargetGuest(GuestSearchDTO vo) {
        return sqlSession.selectOne("ihm.wd.wedding.guest.selectTargetGuest", vo);
    }



}