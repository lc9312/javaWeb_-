package com.itheima.service.store.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.QuestionItemDao;
import com.itheima.domain.store.QuestionItem;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.QuestionItemService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class QuestionItemServiceImpl implements QuestionItemService {
    @Override
    public void save(QuestionItem questionItem)
    {
        SqlSession sqlSession = null;
        try {
            // 1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao层的mapper对象
            QuestionItemDao mapper = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
            // 3.给QuestionItem添加主键ID值
            String questionItemId = UUID.randomUUID().toString();
            questionItem.setId(questionItemId);

            System.out.println(questionItem);

            // 3.执行Sql语句
            mapper.save(questionItem);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if(sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public void update(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao层的mapper对象
            QuestionItemDao mapper = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            // 3.执行sql语句
            mapper.update(questionItem);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if(sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException("修改失败!");
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public void delete(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao层的mapper对象
            QuestionItemDao mapper = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            // 3.执行sql语句
            mapper.delete(questionItem);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if( sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException("删除失败!");
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public QuestionItem findById(String id)
    {
        SqlSession sqlSession = null;
        try {
            // 1.用工厂类获取sqlsession对象
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao的mapper
            QuestionItemDao dao = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            // 3.调用mapper的Sql查询方法,并返回结果
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 5.关闭资源
            if( sqlSession!= null ){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public List<QuestionItem> findAll() {
        SqlSession sqlSession = null;
        try {
            // 1.用工厂类获取sqlsession对象
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao的mapper
            QuestionItemDao dao = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            // 3.调用mapper的Sql查询方法,并返回结果
            List<QuestionItem> all = dao.findAll();
            return dao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 5.关闭资源
            if( sqlSession!= null ){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public PageInfo findAll(String questionId, Integer page, Integer pagesize) {
        SqlSession sqlSession = null;
        PageInfo pageInfo = null;
        try {
            // 1.获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao层的mapper
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
            // 3. 调用mapper的sql查询方法,并返回结果
            PageHelper.startPage(page,pagesize);
            List<QuestionItem> all = questionItemDao.findByQuestionId(questionId);
            pageInfo = new PageInfo(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }
}
