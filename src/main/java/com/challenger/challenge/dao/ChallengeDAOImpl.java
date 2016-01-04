package com.challenger.challenge.dao;

import com.challenger.challenge.entities.ChallengeEntity;
import com.challenger.dao.GenericDaoImpl;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
@Repository
public class ChallengeDAOImpl extends GenericDaoImpl<ChallengeEntity, Long> implements ChallengeDAO {

    private static Logger log = LoggerFactory.getLogger(ChallengeDAOImpl.class);


    public ChallengeDAOImpl(){
        super(ChallengeEntity.class);
    }

    @Override
    public Long createChallenge(ChallengeEntity challenge) {
        Long id = create(challenge);
        log.debug("Challenge entity created: {}", id);
        return id;
    }

    @Override
    public void removeChallenge(ChallengeEntity challenge) {
        log.debug("Removing challenge entity: {}", challenge);
        delete(challenge);
    }

    @Override
    public void removeChallenge(long id) {
        Query query = getSession().createQuery("delete from " + getType().getSimpleName() + " where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        if (result > 0){
            log.debug("Challenge with id {} removed successfully");
        }
    }

    @Override
    public ChallengeEntity findChallenge(long id) {
        return read(id);
    }

    @Override
    public List<ChallengeEntity> findAll() {
        return getSession().createCriteria(getType()).list();
    }

    @Override
    public void updateChallenge(ChallengeEntity challengeEntity) {
        update(challengeEntity);
    }
}
