package com.example.forest.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.forest.dto.post.LikeDislikeResponse;
import com.example.forest.dto.post.LikeRequest;
import com.example.forest.dto.post.ModifyPasswordCheckDto;
import com.example.forest.dto.post.PostLikesDto;
import com.example.forest.dto.post.PostWithLikesCount;
import com.example.forest.model.Post;
import com.example.forest.service.LikesService;
import com.example.forest.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostRestController {
    
	private final PostService postService;
	private final LikesService likesService;
	
	@PostMapping("/check-password")
    public boolean checkPassword(@RequestBody ModifyPasswordCheckDto dto) {
		log.info("checkPassword(dto={})", dto);
		
        String inputPassword = dto.getPassword();
        Long postId = dto.getPostId();

        Post post = postService.read(postId);
        log.info("post={}", post);

        return inputPassword.equals(post.getPostPassword());
    }
	
	@Transactional
	@DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId) {
        log.info("deletePost(id={})", postId);
        
        likesService.deleteByPost_Id(postId);
        
        postService.delete(postId);
        
        return ResponseEntity.ok("Likes for postId " + postId + " has been deleted.");
    }
	
	// 조회수
	@PostMapping("/increaseViewCount")
    public int increaseViewCount(@RequestParam Long postId) {
    	log.info("increaseViewCount(postId={})", postId);
    	
        return postService.increaseViewCount(postId);
    }
	
	// 총 좋아요 개수
	@PostMapping("/like")
    public long createLike(@RequestBody PostLikesDto dto) {
    	log.info("createLike(postId={}, userId={})", dto.getPostId(), dto.getUserId());
    	
        likesService.saveLikeDislikeForPost(1, dto.getPostId(), dto.getUserId());
        
        return likesService.countLikesByPostId(dto.getPostId());
    }
	
	// 총 싫어요 개수
	@PostMapping("/dislike")
    public long createDislike(@RequestBody PostLikesDto dto) {
    	log.info("createDislike(postId={}, userId={})", dto.getPostId(), dto.getUserId());
    	
    	likesService.saveLikeDislikeForPost(0, dto.getPostId(), dto.getUserId());
        
        return likesService.countDislikesByPostId(dto.getPostId());
    }
	
	// 좋아요, 싫어요 하루에 한 번
	@PostMapping("/likeLimit")
    public ResponseEntity<?> likePost(@RequestBody LikeRequest request) {
        boolean success = likesService.likeOrDislike(request.getUserId(), request.getPostId(), request.getLikeDislike());
        return success ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("하루에 한 번만 가능합니다.");
    }
	
	// 해당 게시물에 계정이 좋아요, 싫어요 한 이력이 있는지 체크
	@GetMapping("/checkLikes/{postId}/{userId}")
	public ResponseEntity<LikeDislikeResponse> checkLikes(@PathVariable Long postId, @PathVariable Long userId) {
	    log.info("(postId={}, userId={})", postId, userId);
	    
	    boolean exists = likesService.isLikeDislikeExists(postId, userId);
	    log.info("exists", exists);
	    
	    return ResponseEntity.ok(new LikeDislikeResponse(exists));
	}
	
	// 카테고리 별 분류 - 일반
	@GetMapping("/category/{boardId}")
    public ResponseEntity<String> category(@PathVariable Long boardId, @PageableDefault(page = 0, size = 3) Pageable pageable) {
        log.info("(boardId={})", boardId);
        
        Page<PostWithLikesCount> list = postService.findAllPostsWithLikesCountWhenNormal(boardId, pageable);
        log.info("list = {}", list);
        
        
        
        return ResponseEntity.ok("성공");
    }
    
}
