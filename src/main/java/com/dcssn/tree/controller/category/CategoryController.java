package com.dcssn.tree.controller.category;

import com.dcssn.tree.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 产品分类 页面跳转
 *
 * @author lihy
 */
@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("id", categoryService.getLatestId());
        return "index";
    }


}
