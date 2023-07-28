package com.example.forest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.forest.dto.post.PostCreateDto;
import com.example.forest.model.Post;
import com.example.forest.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;
    
    @GetMapping
    public String post(Model model) {
        log.info("post()");
        
        // 포스트 목록 검색
        List<Post> list = postService.read();
        
        // Model 검색 결과를 세팅:
        model.addAttribute("posts", list);
        
        return "/post/read";
    }
    
//    @PreAuthorize("hasRole('USER')") // 페이지 접근 이전에 인증(권한, 로그인) 여부를 확인.
    @GetMapping("/create")
    public void create() {
        log.info("create() GET");
    }
    
    @PostMapping("/create")
    public String createPost(PostCreateDto dto) {
        log.info("create(dto={}) POST", dto);
        
        // form에서 submit(제출)된 내용을 DB 테이블에 insert
       postService.create(dto);
        
        // DB 테이블 insert 후 포스트 목록 페이지로 redirect 이동.
        return "redirect:/post";
    }
    
    @GetMapping("/details")
    public void read(Long id, Model model) {
        log.info("read(id={})", id);
        
        // id로 Posts 테이블에서 id에 해당하는 포스트를 검색.
        Post post = postService.read(id);
               
        // 결과를 model에 저장 -> 뷰로 전달됨.   
        model.addAttribute("post", post);

    }
    

}

