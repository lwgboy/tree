package com.dcssn.tree.repository;

import com.dcssn.tree.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 分类
 *
 * @author lihy
 * @version 2018/10/16
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    /**
     * 根据层级查找
     * JpaRepository<Category, Long>,
     *
     * @param level 层级
     * @return java.util.List<com.dcssn.weian.cms.entity.Category>
     * @author lihy
     */
    List<Category> findByLevel(Integer level);

    /**
     * 获取parent_id
     * @author lihy
     * @param id 主键
     * @return java.lang.Long
     */
    @Query(value = "select parent_id from category where id=:id", nativeQuery = true)
    Long getParentId(@Param("id")Long id);

    /**
     * 获取最新的id
     *
     * @return java.lang.Long
     * @author lihy
     */
    @Query(value = "select case when isnull(id) then 1 else id+1 end id from category order by id desc limit 1", nativeQuery = true)
    Long getLatestId();

    /**
     * 删除不在给定集合内的数据
     *
     * @param ids 主键集合
     * @author lihy
     */
    @Transactional(rollbackFor = {Exception.class})
    @Modifying
    void deleteByIdNotIn(Set<Long> ids);

    /**
     * 有则更新没有则插入
     *
     * @param parentId 父id
     * @param category record
     * @author lihy
     */
    @Transactional(rollbackFor = {Exception.class})
    @Modifying
    @Query(value = "INSERT INTO category (id,description,keywords,level,name,sequence,parent_id)" +
            "VALUES" + "(:#{#category.id},:#{#category.description},:#{#category.keywords},:#{#category.level},:#{#category.name},:#{#category.sequence},:parentId) " +
            "ON DUPLICATE KEY UPDATE " +
            "description=:#{#category.description},keywords=:#{#category.keywords},level=:#{#category.level},name=:#{#category.name},sequence=:#{#category.sequence},parent_id=:parentId", nativeQuery = true)
    void existsUpdateThenInsert(@Param("parentId") Long parentId, @Param("category") Category category);

}
