package com.example.myblog2.service;

import com.example.myblog2.pojo.Tag;
import com.example.myblog2.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    //cha
    Tag getTag(Long id);
    //分頁查詢
    Page<Tag> listTag(Pageable pageable);
    //增
    Tag saveTag(Tag tag);
    //通过名称查
    Tag getTagByName(String name);
    //gai
    Tag updataTag(Long id,Tag tag);
    //shan
    void deleteTag(Long id);
    List<Tag> listTag();
    List<Tag> listTag(String ids);
    List<Tag> listTagTop(Integer size);
}
