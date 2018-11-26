package com.dcssn.tree.controller.category;

import com.alibaba.fastjson.JSONObject;
import com.dcssn.tree.entity.Category;
import com.dcssn.tree.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * category api
 *
 * @author lihy
 */
@RestController
public class CategoryApiController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getCategoryList() {
        return categoryService.findAllFirstLevel();
    }

    @PostMapping("/category")
    public String saveCategory(@RequestBody String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        List<Category> categories = JSONObject.parseArray(jsonObject.getString("data"), Category.class);
        categoryService.saveAll(categories);
        return "success";
    }


}
