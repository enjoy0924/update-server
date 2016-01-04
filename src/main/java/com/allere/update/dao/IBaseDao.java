package com.allere.update.dao;

import com.allere.update.entity.AbstractEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by G_dragon on 2015/12/29.
 */
public interface IBaseDao {

    <T extends AbstractEntity> AbstractEntity get(Class<T> var1, Serializable var2);

    <T extends AbstractEntity> T load(Class<T> var1, Serializable var2);

    <T extends AbstractEntity> void create(T var1);

    <T extends AbstractEntity> void update(T var1);

    <T extends AbstractEntity> void delete(T var1);

    <T extends AbstractEntity> void batchSave(List<T> var1);

    <T extends AbstractEntity> void batchUpdate(List<T> var1);

    <T extends AbstractEntity> void batchDelete(List<T> var1);

    <T extends AbstractEntity> List<T> findAll();
}
