package com.example.myblog2.service.impl;

import com.example.myblog2.NotFoundException;
import com.example.myblog2.dao.TypeRepository;
import com.example.myblog2.pojo.Type;
import com.example.myblog2.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceimpl implements TypeService {
    @Autowired
    TypeRepository typeRepository;
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findById(id).orElse(null);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);

    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);

    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listType(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable =PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort =Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }
}
