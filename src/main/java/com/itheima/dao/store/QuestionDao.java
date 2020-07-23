package com.itheima.dao.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Question;

import java.util.List;

public interface QuestionDao {
    public abstract int save(Question question);

    public abstract int update(Question question);

    public abstract int delete(Question question);

    public abstract Question findById(String id);

    public abstract List<Question> findAll();

    public abstract PageInfo findAll(Integer page, Integer pagesize);
}
