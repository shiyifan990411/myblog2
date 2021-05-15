package com.example.myblog2.service;

import com.example.myblog2.pojo.Blog;
import com.example.myblog2.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);
    Page<Blog> ListBlog(Pageable pageable, BlogQuery blog);
    Blog saveBlog(Blog blog);
    Blog updateBlog(Long id,Blog blog);
    void deleteBlog(Long id);
    Page<Blog> listBlog(Pageable pageable);
    List<Blog> RecommendlistBlogTop(Integer size);
    Page<Blog> listBlog(String query,Pageable pageable);
    Blog getAndConvert(Long id);
    Page<Blog> listBlog(Long tagId,Pageable pageable);


}
