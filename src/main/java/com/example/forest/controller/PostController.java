package com.example.forest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.forest.dto.post.PostCreateDto;
import com.example.forest.dto.post.PostSearchDto;
import com.example.forest.dto.post.PostUpdateDto;
import com.example.forest.dto.post.PostWithLikesCount;
import com.example.forest.dto.post.PostWithLikesCount2;
import com.example.forest.model.Post;
import com.example.forest.service.BoardService;
import com.example.forest.service.LikesService;
import com.example.forest.model.Reply;
import com.example.forest.service.PostService;
import com.example.forest.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    
	private final BoardService boardService;
    private final PostService postService;
    private final LikesService likesService;
    private final ReplyService replyService; 
    
//    @GetMapping
//    public String post(Model model) {
//        log.info("post()");
//        
//        List<PostWithLikesCount> list = postService.findAllPostsWithLikesCount();
//        log.info("post(list={})", list);
//        
//        model.addAttribute("posts", list);
//        
//        return "/post/read";
//    }
    
    @GetMapping("/popular")
    public String popular(Model model) {
        log.info("popular()");
        
        List<PostWithLikesCount2> list = postService.findPostsByLikesDifference();
        log.info("popular(list={})", list);
        
        model.addAttribute("posts", list);
        
        return "/post/read-popular";
    }
    
//    @PreAuthorize("hasRole('USER')") // 페이지 접근 이전에 인증(권한, 로그인) 여부를 확인.
    @GetMapping("/create")
    public void create(@RequestParam("id") long id, Model model) {
        log.info("create() GET");
        
        long boardId = boardService.findById(id).getId();
        model.addAttribute("boardId", boardId);
    }
    
    @PostMapping("/create")
    public String createPost(PostCreateDto dto) {
        log.info("create(dto={}) POST", dto);
        
        // form에서 submit(제출)된 내용을 DB 테이블에 insert
       postService.create(dto);
        
        // DB 테이블 insert 후 포스트 목록 페이지로 redirect 이동.
        return "redirect:/board/" + dto.getBoardId();
    }
    
    
    // 채한별  추가:  댓글 개수 불러오기
    // "/post/details", "/post/modify" 요청 주소들을 처리하는 메서드.
    @GetMapping({"/details", "/modify"})
    public void read(Long id, Model model) {
        log.info("read(id={})", id);
        
        // id로 Posts 테이블에서 id에 해당하는 포스트를 검색.
        Post post = postService.read(id);
        long likesCount = likesService.countLikesByPostId(id);
        long dislikesCount = likesService.countDislikesByPostId(id);
        long viewCount = postService.increaseViewCount(id) - 1;
               
        // 결과를 model에 저장 -> 뷰로 전달됨.   
        model.addAttribute("post", post);
        model.addAttribute("likesCount", likesCount);
        model.addAttribute("dislikesCount", dislikesCount);
        model.addAttribute("viewCount", viewCount);
        
        // 채한별 추가 : 
        // REPLIES 테이브에서 해당 포스트에 달린 댓글 개수를 검색.
        long count = replyService.countByPost(post);
        model.addAttribute("replyCount", count);
    }
    
    @GetMapping("/modifyCheck")
    public void modifyCheck(Long id, Model model) {
        log.info("modifyCheck(id={})", id);
        
        Post post = postService.read(id);
        
        model.addAttribute("post", post);
    }
    
    @GetMapping("/modify")
    public void modify(Long id, Model model) {
        log.info("modify(id={})", id);
        
        Post post = postService.read(id);
               
        model.addAttribute("post", post);
    }
    
    @PostMapping("/modify")
    public String modify(PostUpdateDto dto) {
        log.info("modify(dto={}) POST", dto);
        
       postService.update(dto);
        
       return "redirect:/post";
    }
    
    @GetMapping("/deleteCheck")
    public void deleteCheck(Long id, Model model) {
        log.info("deleteCheck(id={})", id);
        
        Post post = postService.read(id);
        
        model.addAttribute("post", post);
    }
    
    @GetMapping("/search")
    public String search(PostSearchDto dto, Model model) {
        log.info("search(dto={})", dto);
        
        // postService의 검색 기능 호출:
        List<Post> list = postService.search(dto);
        
        // 검색 결과를 Model에 저장해서 뷰로 전달:
        model.addAttribute("posts", list);

        return "/post/read";
    }
    

}

