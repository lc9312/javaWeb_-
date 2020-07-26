package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemService {
    /**
     *  添加
     * @param questionItem
     * @return
     */
    public abstract void save(QuestionItem questionItem);

    /**
     * 修改
     * @param questionItem
     * @return
     */
    public abstract void update(QuestionItem questionItem);

    /**
     * 删除
     * @param questionItem
     * @return
     */
    public abstract void delete(QuestionItem questionItem);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public abstract QuestionItem findById(String id);

    /**
     * 查询所有
     * @return
     */
    public abstract List<QuestionItem> findAll();

    /**
     * 分页查询
     * @param page
     * @param pagesize
     * @return
     */
    public abstract PageInfo findAll(String questionId, Integer page, Integer pagesize);
}
