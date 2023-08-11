package com.example.forest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forest.dto.reply.ReplyCreateDto;
import com.example.forest.model.Post;
import com.example.forest.model.ReReply;
import com.example.forest.model.Reply;
import com.example.forest.repository.PostRepository;
import com.example.forest.repository.ReReplyRepository;
import com.example.forest.repository.ReplyRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReReplyRepository reReplyRepository;
    private final PostRepository postRepository;
    
   
    
    // 회원 댓글 삭제
    public void delete(Long id) {
        log.info("delete(id= {})", id);
        
        Reply reply = replyRepository.findById(id).orElseThrow();
       
        reReplyRepository.deleteByReply(reply);
        replyRepository.deleteById(id);
    }
    
    // 비회원 댓글 삭제
    public void delete(Long id, String replyPassword) {
        log.info("delete(id= {}, replyPassword={})", id, replyPassword);
        
     // 댓글 ID와 비밀번호로 댓글 조회
        Reply reply = replyRepository.findByIdAndReplyPassword(id, replyPassword);
        
        if (reply.getUserId() == 0) {
            // 비밀번호가 일치하면 댓글 삭제
            Reply reply2 = replyRepository.findById(id).orElseThrow();
            
            reReplyRepository.deleteByReply(reply2);
            replyRepository.deleteById(id);
        } else {
            // 비밀번호가 일치하지 않으면 예외 처리
            throw new IllegalArgumentException("댓글을 삭제할 수 없습니다. 비밀번호가 일치하지 않습니다.");
        }
    }
    
        
        // 댓글 목록 보기
        @Transactional(readOnly = true)
        public List<Reply> read(Long postId) {
            log.info("read(postId={})", postId);
            
            // 1. postId로 Post를 검색.
            Post post = postRepository.findById(postId).orElseThrow();
            
            // 2. 찾은 Post에 달려 있는 댓글 목록을 검색.
            List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);
            return list;
        }

    // 댓글 읽어오기
    @Transactional(readOnly = true)
    public List<Reply> read(Post post) {
        log.info("read(post={})", post);
    
        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);
        
        return list;
    }
    
    // 댓글 개수
    public Long countByPost(Post post) {
        log.info("countByPost(post={})", post);
        
        return replyRepository.countByPost(post);
    }
    
    
    
    
    public Long countByPostId(Long postId) {
        log.info("countByPostId(postId={})", postId);
        
        return replyRepository.countByPostId(postId);
    }

    // 댓글 생성
    public Reply create(ReplyCreateDto dto) {
        log.info("create(dto={})", dto);

        
        // 1. Post 엔터티검색
        Post post = postRepository.findById(dto.getPostId()).orElseThrow();
        
        // 2. ReplyCreateDto 객체를 Reply 엔터티 객체로 변환.
        Reply entity = Reply.builder()
                .post(post)
                .userId(dto.getUserId())
                .replyIp(dto.getReplyIp())
                .replyText(dto.getReplyText())
                .replyNickname(dto.getReplyNickname())
                .replyPassword(dto.getReplyPassword())
                .build();
        
        // 3. DB replies 테이블에 insert
        replyRepository.save(entity);
        
        return entity;
    }
    

//    
    // 댓글 목록을 정렬하여 가져오기
    @Transactional(readOnly = true)
    public List<Reply> readSortedReplies(Long postId, String sortValue) {
        log.info("readSortedReplies(postId={}, sortValue={})", postId, sortValue);

        Post post = postRepository.findById(postId).orElseThrow();
        List<Reply> replies = new ArrayList<>();
        
        switch(sortValue) {
        case "D":
            replies = replyRepository.findByPostOrderByIdAsc(post);
            break;
        case "N":
            replies = replyRepository.findByPostOrderByIdDesc(post);
            break;
        }

        return replies;
    }
    
    
}
