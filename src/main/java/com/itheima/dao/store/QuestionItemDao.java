package com.itheima.dao.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemDao {
    public abstract int save(QuestionItem questionItem);

    public abstract int update(QuestionItem questionItem);

    public abstract int delete(QuestionItem questionItem);

    public abstract QuestionItem findById(String id);

    public abstract List<QuestionItem> findAll();

    public abstract PageInfo findAll(Integer page, Integer pagesize);

    List<QuestionItem> findByQuestionId(String questionId);
}
