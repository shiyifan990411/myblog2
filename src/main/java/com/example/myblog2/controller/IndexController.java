package com.example.myblog2.controller;

import com.example.myblog2.pojo.Tag;
import com.example.myblog2.service.BlogService;
import com.example.myblog2.service.TagService;
import com.example.myblog2.service.TypeService;
import com.example.myblog2.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @GetMapping("/")
    public String index(@PageableDefault(size = 10000,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,  Model model){
       model.addAttribute("page",blogService.listBlog(pageable));
       model.addAttribute("types",typeService.listType(6));
       model.addAttribute("tags",tagService.listTagTop(10));
       model.addAttribute("Recommendblogs",blogService.RecommendlistBlogTop(10));
        return "index";
    }
    @GetMapping("/blog")
    public String blog(){

        return "blog";
    }
    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);
        return "search";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.RecommendlistBlogTop(3));
        return "_fragments :: newblogList";
    }

}
