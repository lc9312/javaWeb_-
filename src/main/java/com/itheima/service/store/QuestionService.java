package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Question;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface QuestionService {
    /**
     *  添加
     * @param quetion
     * @return
     */
    public abstract void save(Question quetion,Boolean flag);

    /**
     * 修改
     * @param quetion
     * @return
     */
    public abstract void update(Question quetion,Boolean flag);

    /**
     * 删除
     * @param quetion
     * @return
     */
    public abstract void delete(Question quetion);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public abstract Question findById(String id);

    /**
     * 查询所有
     * @return
     */
    public abstract List<Question> findAll();

    /**
     * 分页查询
     * @param page
     * @param pagesize
     * @return
     */
    public abstract PageInfo findAll(Integer page, Integer pagesize);

    ByteArrayOutputStream getReport() throws IOException;
}
