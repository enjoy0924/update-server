package com.plum.update.dao.impl;

import com.plum.update.dao.IUpdateIntroDao;

import com.plum.update.entity.UpdateIntroEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by G_dragon on 2015/12/29.
 */
@Repository
public class UpdateIntroDaoImpl extends BaseDaoImpl implements IUpdateIntroDao {

    @Override
    public List<UpdateIntroEntity> findAll() {

        String hql = "from UpdateIntroEntity";

        return queryMulti(hql, new HashMap<>());

    }
}
