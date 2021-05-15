package com.example.myblog2.controller.admin;

import com.example.myblog2.dao.TypeRepository;
import com.example.myblog2.pojo.Type;
import com.example.myblog2.service.TypeService;
import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("/types")
    public String types(@PageableDefault(size =3,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){

        model.addAttribute("pages",typeService.listType(pageable));
        return "admin/types";
    }
    //進入增加
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }
    //提交
    @PostMapping("/types")
    public String addType(@Valid Type type,
                          BindingResult result,
                          RedirectAttributes attributes
                          ){
        if(typeService.getTypeByName(type.getName())!=null){
            result.rejectValue("name","nameError","该名称存在");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = typeService.saveType(type);
        if(type1==null){
            attributes.addFlashAttribute("message","添加失敗");

        }else{
            attributes.addFlashAttribute("message","添加成功");
        }

        return "redirect:/admin/types";

    }
    //修改
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";

    }
    @PostMapping("/types/{id}")
    public String editType(@Valid Type type,
                           BindingResult result,
                           @PathVariable Long id,
                          RedirectAttributes attributes
                          ){
        if(typeService.getTypeByName(type.getName())!=null){
            result.rejectValue("name","nameError","该名称存在");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = typeService.updateType(id,type);
        if(type1==null){
            attributes.addFlashAttribute("message","修改失敗");

        }else{
            attributes.addFlashAttribute("message","修改成功");
        }

        return "redirect:/admin/types";

    }
    //删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,
                         @RequestParam(value = "page",defaultValue = "1") Integer pn,
                         RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        attributes.addAttribute("page",pn);
        return "redirect:/admin/types";
    }

}
