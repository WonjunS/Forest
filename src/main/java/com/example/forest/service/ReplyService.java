package com.example.forest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forest.dto.reply.ReplyCreateDto;
import com.example.forest.model.Post;
import com.example.forest.model.Reply;
import com.example.forest.repository.PostRepository;
import com.example.forest.repository.ReplyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    
    
    public void delete(long id) {
        log.info("delete(id= {})", id);
        
        // DB replies 테이블에서 ID(고유키)로 엔터티 삭제하기     
        replyRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Reply> read(Long postId) {
        log.info("read(postId={})", postId);
        
        // 1. postId로 Post를 검색.
        Post post = postRepository.findById(postId).orElseThrow();
        
        // 2. 찾은 Post에 달려 있는 댓글 목록을 검색.
        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);
        return list;
    }
    
    @Transactional(readOnly = true)
    public List<Reply> read(Post post) {
        log.info("read(post={})", post);
    
        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);
        
        return list;
    }
    
    public Long countByPost(Post post) {
        log.info("countByPost(post={})", post);
        
        return replyRepository.countByPost(post);
    }

    public Reply create(ReplyCreateDto dto) {
        log.info("create(dto={})", dto);
        
        // 1. Post 엔터티검색
        Post post = postRepository.findById(dto.getPostId()).orElseThrow();
        
        // 2. ReplyCreateDto 객체를 Reply 엔터티 객체로 변환.
        Reply entity = Reply.builder()
                .post(post)
                .replyText(dto.getReplyText())
                .replyNickname(dto.getReplyNickname())
                .replyPassword(dto.getReplyPassword())
                .build();
        
        // 3. DB replies 테이블에 insert
        replyRepository.save(entity);
        
        return entity;
    }
    
    
}
