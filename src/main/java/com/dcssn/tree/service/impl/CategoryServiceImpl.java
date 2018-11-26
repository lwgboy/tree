package com.dcssn.tree.service.impl;

import com.dcssn.tree.entity.Category;
import com.dcssn.tree.repository.CategoryRepository;
import com.dcssn.tree.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author lihy
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllFirstLevel() {
        return categoryRepository.findByLevel(1);
    }

    @Override
    public Long getLatestId() {
        Long id = categoryRepository.getLatestId();
        return id == null ? 1 : id;
    }

    @Override
    public void saveAll(List<Category> categories) {
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < categories.size(); i++) {
            recursion(categories.get(i), null, 1, i, ids);
        }
        // TODO: 设置不在新分类中的产品的分类id为空（清除关联）

        // 删除不在分类中的id
        categoryRepository.deleteByIdNotIn(ids);
    }

    @Override
    public LinkedList findParentIdArray(Long id) {
        LinkedList<Long> ids = new LinkedList<Long>();
        findParentId(id, ids);
        return ids;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    private void findParentId(Long id, LinkedList<Long> ids) {
        ids.addFirst(id);
        Long parentId = categoryRepository.getParentId(id);
        if (parentId != null) {
            findParentId(parentId, ids);
        }
    }

    /**
     * 递归
     *
     * @param category 分类
     * @param parentId 分id
     * @param level    层级
     * @param sequence 同级排序
     * @author lihy
     */
    private void recursion(Category category, Long parentId, int level, int sequence, Set<Long> ids) {
        category.setLevel(level);
        category.setSequence(sequence);
        ids.add(category.getId());
        categoryRepository.existsUpdateThenInsert(parentId, category);
        Category categoryTemp;
        if (category.getChildren() != null) {
            for (int i = 0; i < category.getChildren().size(); i++) {
                categoryTemp = category.getChildren().get(i);
                recursion(categoryTemp, category.getId(), level + 1, i, ids);
            }
        }
    }
}