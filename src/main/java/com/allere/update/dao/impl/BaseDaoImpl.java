package com.allere.update.dao.impl;

import com.allere.update.dao.IBaseDao;
import com.allere.update.entity.AbstractEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * Created by G_dragon on 2015/12/29.
 */
public abstract class BaseDaoImpl implements IBaseDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public BaseDaoImpl() {
    }

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    private Session getNewSession() {
        return this.sessionFactory.openSession();
    }

    public <T extends AbstractEntity> T get(Class<T> clazz, Serializable id) {
        Session session = this.getCurrentSession();

        try {
            return (T) session.get(clazz, id);
        } catch (Throwable var5) {
            throw var5;
        }
    }

    public <T extends AbstractEntity> T load(Class<T> clazz, Serializable id) {
        Session session = this.getCurrentSession();

        try {
            return (T) session.load(clazz, id);
        } catch (Throwable var5) {
//            Log.error("load数据库数据失败. class = " + clazz.getSimpleName() + " id = " + id, var5);
            throw var5;
        }
    }

    public <T extends AbstractEntity> void create(T t) {
        Session session = this.getCurrentSession();

        try {
            session.save(t);
            session.flush();
        } catch (Throwable var4) {
//            Log.error("save报错. Bean = " + t, var4);
            throw var4;
        }
    }

    public <T extends AbstractEntity> void update(T t) {
        Session session = this.getCurrentSession();

        try {
//            t.updateSync();
            session.update(t);
            session.flush();
        } catch (Throwable var4) {
//            Log.error("update报错. Bean = " + t, var4);
            throw var4;
        }
    }

    public <T extends AbstractEntity> void delete(T t) {
        Session session = this.getCurrentSession();

        try {
            session.delete(t);
            session.flush();
        } catch (Throwable var4) {
//            Log.error("delete报错. Bean = " + t, var4);
            throw var4;
        }
    }

    public <T extends AbstractEntity> void batchSave(List<T> ts) {
        Session session = this.getCurrentSession();

        try {
            int e = 0;
            Iterator var4 = ts.iterator();

            while(var4.hasNext()) {
                AbstractEntity t = (AbstractEntity)var4.next();
                session.save(t);
                ++e;
                if(e % 100 == 0) {
                    session.flush();
                }
            }

            session.flush();
        } catch (Throwable var6) {
//            Log.error("batchSave报错", var6);
            throw var6;
        }
    }

    public <T extends AbstractEntity> void batchUpdate(List<T> ts) {
        Session session = this.getCurrentSession();

        try {
            int e = 0;
            Iterator var4 = ts.iterator();

            while(var4.hasNext()) {
                AbstractEntity t = (AbstractEntity)var4.next();
//                t.updateSync();
                session.update(t);
                ++e;
                if(e % 100 == 0) {
                    session.flush();
                }
            }

            session.flush();
        } catch (Throwable var6) {
//            Log.error("batchUpdate报错", var6);
            throw var6;
        }
    }

    public <T extends AbstractEntity> void batchDelete(List<T> ts) {
        Session session = this.getCurrentSession();

        try {
            Iterator e = ts.iterator();

            while(e.hasNext()) {
                AbstractEntity t = (AbstractEntity)e.next();
                session.delete(t);
            }

            session.flush();
        } catch (Throwable var5) {
//            Log.error("batchDelete报错", var5);
            throw var5;
        }
    }

    protected <T extends AbstractEntity> T querySingle(String hSql, Map<String, Object> paras) {
        List<T> list = this.query(hSql, paras, false);
        return list != null && list.size() > 0?(list.get(0) == null?null:list.get(0)):null;
    }

    protected <T extends AbstractEntity> T querySingle(String hSql, Map<String, Object> paras, boolean cache) {
        List<T> list = this.query(hSql, paras, cache);
        return list != null && list.size() > 0?(list.get(0) == null?null:list.get(0)):null;
    }

    protected <T extends AbstractEntity> List<T> queryMulti(String hSql, Map<String, Object> paras) {
        return this.query(hSql, paras, false);
    }

    protected <T extends AbstractEntity> List<T> queryMulti(String hSql, Map<String, Object> paras, boolean cache) {
        return this.query(hSql, paras, cache);
    }

    private <T extends AbstractEntity> List<T> query(String hSql, Map<String, Object> paras, boolean cache) {
        Query query = this.getQuery(hSql, paras, cache);

        try {
            return query.list();
        } catch (Throwable var6) {
//            Log.error("查询错误. sql = " + hSql, var6);
            throw var6;
        }
    }

    protected <T extends AbstractEntity> List<T> queryMultiBySql(String sql, Map<String, Object> paras, Class<T> t, boolean cache) {
        SQLQuery query = this.getSQLQuery(sql, paras, cache, t);

        try {
            return query.setResultTransformer(Transformers.aliasToBean(t)).list();
        } catch (Throwable var7) {
//            Log.error("查询数据库失败 sql = " + sql, var7);
            throw var7;
        }
    }

//    public <T extends AbstractEntity> List<T> pageQueryMulti(String hSql, Map<String, Object> paras, PageDto pageDto, boolean cache) {
//        long total = this.queryCount(hSql, paras);
//        pageDto.setTotal(total);
//        Query query = this.getQuery(hSql, paras, cache, pageDto);
//
//        try {
//            return query.list();
//        } catch (Throwable var9) {
//            var9.printStackTrace();
//            throw var9;
//        }
//    }

    protected long queryCount(String hql, Map<String, Object> paras) {
        hql = "select count(0) " + hql;
        Query query = this.getQuery(hql, paras);

        try {
            List e = query.list();
            return ((Long)e.get(0)).longValue();
        } catch (Throwable var5) {
            throw var5;
        }
    }

    protected void deleteByHSql(String hSql, Map<String, Object> paras, boolean cache) {
        Query query = this.getQuery(hSql, paras, cache);

        try {
            query.executeUpdate();
        } catch (Throwable var6) {
            throw var6;
        }
    }

    protected void updateByHQL(String updateHQL, Map<String, Object> params) {
        Session session = this.getCurrentSession();

        try {
            Query e = session.createQuery(updateHQL);
            this.setParameters(e, params);
            e.executeUpdate();
        } catch (Throwable var5) {
            throw var5;
        }
    }

    private <T extends AbstractEntity> SQLQuery getSQLQuery(String sql, Map<String, Object> paras, boolean cache, Class<T> tClass) {
        Session session = this.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.setCacheable(cache);
        query.addEntity(tClass);
        this.setParameters(query, paras);
        return query;
    }

//    private Query getQuery(String hql, Map<String, Object> paras, boolean cache, PageDto pageDto) {
//        Query query = this.getQuery(hql + this.getOrderBySql(pageDto), paras, cache);
//        query.setFirstResult(pageDto.getFirstIndex());
//        query.setMaxResults(pageDto.getPerPageSize());
//        return query;
//    }
//
//    private String getOrderBySql(PageDto pageDto) {
//        String order = " order by " + pageDto.getOrder();
//        String sort = pageDto.getSort();
//        return sort != null && !sort.isEmpty()?order + " " + sort:order;
//    }

    private Query getQuery(String hql, Map<String, Object> paras) {
        return this.getQuery(hql, paras, false);
    }

    private Query getQuery(String hql, Map<String, Object> paras, boolean cache) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setCacheable(cache);
        this.setParameters(query, paras);
        return query;
    }

    private void setParameters(Query query, Map<String, Object> map) {
        if(map != null && !map.isEmpty()) {
            Iterator var3 = map.keySet().iterator();

            while(var3.hasNext()) {
                String key = (String)var3.next();
                Object value = map.get(key);
                if(value instanceof Collection) {
                    query.setParameterList(key, (Collection)value);
                } else if(value instanceof String) {
                    query.setString(key, String.valueOf(value));
                } else if(value instanceof Date) {
                    query.setTimestamp(key, (Date)value);
                } else if(value instanceof Boolean) {
                    query.setBoolean(key, ((Boolean)value).booleanValue());
                } else {
                    query.setString(key, String.valueOf(value));
                }
            }

        }
    }
}
