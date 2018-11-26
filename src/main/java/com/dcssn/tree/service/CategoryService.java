package com.dcssn.tree.service;


import com.dcssn.tree.entity.Category;

import java.util.LinkedList;
import java.util.List;

/**
 * 产品分类
 *
 * @author lihy
 */
public interface CategoryService {


    /**
     * 查找所有一级分类
     *
     * @return java.util.List<com.dcssn.weian.cms.entity.Category>
     * @author lihy
     */
    List<Category> findAllFirstLevel();

    /**
     * 获取最新的id
     *
     * @return java.lang.Long
     * @author lihy
     */
    Long getLatestId();

    /**
     * 保存所有分类
     *
     * @param categories 新分类
     * @author lihy
     */
    void saveAll(List<Category> categories);

    /**
     * 查找分类的父级直到根
     *
     * @param id 主键
     * @return java.util.LinkedList
     * @author lihy
     */
    LinkedList findParentIdArray(Long id);

    /**
     * 根据分类id查询
     *
     * @param id 主键
     * @return com.dcssn.weian.cms.entity.Category
     * @author lihy
     */
    Category findById(Long id);

}
