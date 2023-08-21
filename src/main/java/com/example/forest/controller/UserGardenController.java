package com.example.forest.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.forest.model.Blog;
import com.example.forest.model.Board;
import com.example.forest.model.Post;
import com.example.forest.model.Reply;
import com.example.forest.model.User;
import com.example.forest.service.GardenService;
import com.example.forest.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/garden")
public class UserGardenController {

    private final UserService userService;
    private final GardenService gardenService;

    @PreAuthorize("hasRole('USER')") 
    @GetMapping("/gardenmain")
    public String gardenmainPage(@RequestParam String loginId,  Model model) {
        
        log.info("garden()GET");
        
        //닉네임 부르기
        Blog garden = gardenService.read(loginId);
        model.addAttribute("garden", garden);
        log.info("loginId={}", loginId);
        
     // 닉네임으로 사용자 ID 가져오기
        Long userId = userService.getUserId(loginId);
        
        if (userId != -1) {
            model.addAttribute("userId", userId);
            log.info("loginId={}, userId={}", loginId, userId);
        }
        
        log.info("userId={}",userId);
        
        List<Post> postlist = gardenService.findByuserIdPosts(userId);
        log.info("postlist={}", postlist);
        model.addAttribute("postlist", postlist);
       
        List<Board> boardlist = gardenService.findByuserIdBoards(userId);
        log.info("boaldist={}",boardlist);
        model.addAttribute("boardlist", boardlist);
        
        List<Reply> replylist = gardenService.findByuserIdReplys(userId);
        log.info("replylist={}", replylist);
        model.addAttribute("replylist",replylist);
        
        return "/garden/gardenmain";
        
    }
    
    @GetMapping("/gardenDiary")
    public void Diary() {
        log.info("방명록 페이지");
    }
    
}
