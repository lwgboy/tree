package com.dcssn.tree.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 *
 * @author lihy
 * @version 2018/10/15
 */
@Entity
public class Category implements Serializable {
    @Id
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 描述
     */
    private String description;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sequence;

    /**
     * 父类
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    /**
     * 子类
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    @OrderBy("sequence asc")
    private List<Category> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        if (this.children.size() == 0) {
            return null;
        }
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
