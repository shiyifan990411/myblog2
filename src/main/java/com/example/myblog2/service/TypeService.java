package com.example.myblog2.service;

import com.example.myblog2.pojo.Type;
import com.example.myblog2.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 分類
 */
public interface TypeService {
    //zeng
    Type saveType(Type type);
    //cha
    Type getType(Long id);
    //分頁查詢
    Page<Type> listType(Pageable pageable);
    //gai
    Type updateType(Long id,Type type);
    //shan
    void deleteType(Long id);
    //通过名称查
    Type getTypeByName(String name);
    List<Type> listType();
    List<Type> listType(Integer size);
    List<Type> listTypeTop(Integer size);

}
