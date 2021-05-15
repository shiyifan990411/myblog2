package com.example.myblog2.controller.admin;

import com.example.myblog2.pojo.Tag;
import com.example.myblog2.pojo.Type;
import com.example.myblog2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TagController {
    @Autowired
    TagService tagService;
    @GetMapping("/tags")
    public String gotags(@PageableDefault(size =3,sort = {"id"},direction = Sort.Direction.DESC)
                                     Pageable pageable, Model model){
        model.addAttribute("tags",tagService.listTag(pageable));

        return "/admin/tags";
    }
    @GetMapping("/tags/input")
    public String goaddTag(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }
    @PostMapping("/tags")
    public String addTag(@Valid Tag tag,
                         BindingResult result,
                         RedirectAttributes attributes){
        if(tagService.getTagByName(tag.getName())!=null){
            result.rejectValue("name","nameError","该名称存在");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag tag1 = tagService.saveTag(tag);
        if(tag1==null){
            attributes.addFlashAttribute("message","添加失敗");

        }else{
            attributes.addFlashAttribute("message","添加成功");
        }

        return "redirect:/admin/tags";
    }
    //修改
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";

    }
    //修改
    @PostMapping("/tags/{id}")
    public String editTag(@Valid Tag tag,
                           BindingResult result,
                           @PathVariable Long id,
                           RedirectAttributes attributes
    ){
        if(tagService.getTagByName(tag.getName())!=null){
            result.rejectValue("name","nameError","该名称存在");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag tag1 = tagService.updataTag(id,tag);
        if(tag1==null){
            attributes.addFlashAttribute("message","修改失敗");

        }else{
            attributes.addFlashAttribute("message","修改成功");
        }

        return "redirect:/admin/tags";

    }
    //删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,
                         @RequestParam(value = "page",defaultValue = "1") Integer pn,
                         RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        attributes.addAttribute("page",pn);
        return "redirect:/admin/tags";
    }

}
