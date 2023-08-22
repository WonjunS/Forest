package com.example.forest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

import com.example.forest.dto.post.PostWithLikesCount;
import com.example.forest.dto.post.PostWithLikesCount2;
import com.example.forest.dto.stats.PostUserDto;
import com.example.forest.model.Board;
import com.example.forest.model.Post;
import com.example.forest.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * id 내림차순 정렬:
     * select * from POSTS order by ID desc
     * @return
     */
    List<Post> findByOrderByIdDesc();
    
    /**
     * 제목으로 검색:
     * select * from posts p
     * where lower(p.title) like lower('%' || ? || '%')
     * order by p.id desc
     * @param title
     * @param boardId
     * @param pageable
     * @return
     */
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l WHERE l.post = p AND l.likeDislike = 1), "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) + (SELECT COUNT(rr.id) FROM ReReply rr JOIN rr.reply r where r.post = p) as replyCount, "
            + " u.id, u.loginId) " // User의 ID와 loginId만 가져옴
            + " FROM Post p "
            + " LEFT JOIN Likes l ON p = l.post "
            + " LEFT JOIN p.user u " // User와의 LEFT JOIN 추가
            + " WHERE lower(p.postTitle) like lower(concat('%', :title, '%'))"
            + " AND p.board.id = :boardId"
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findByPostTitleContainsIgnoreCaseOrderByIdDesc(@Param("title") String title, @Param("boardId") Long boardId, Pageable pageable);

    /**
     * 내용으로 검색:
     * select * from posts p
     * where lower(p.content) like lower('%' || ? || '%')
     * order by p.id desc
     * @param content
     * @param boardId
     * @param pageable
     * @return
     */
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l WHERE l.post = p AND l.likeDislike = 1), "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) + (SELECT COUNT(rr.id) FROM ReReply rr JOIN rr.reply r where r.post = p) as replyCount, "
            + " u.id, u.loginId) " // User의 ID와 loginId만 가져옴
            + " FROM Post p "
            + " LEFT JOIN Likes l ON p = l.post "
            + " LEFT JOIN p.user u " // User와의 LEFT JOIN 추가
            + " WHERE lower(p.postContent) like lower(concat('%', :content, '%'))"
            + " AND p.board.id = :boardId"
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findByPostContentContainsIgnoreCaseOrderByIdDesc(@Param("content") String content, @Param("boardId") Long boardId, Pageable pageable);

    /**
     * 작성자로 검색:
     * select * from posts p
     * where lower(p.nickname) like lower('%' || ? || '%')
     * order by p.id desc
     * @param nickname
     * @param boardId
     * @param pageable
     * @return
     */
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l WHERE l.post = p AND l.likeDislike = 1), "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) + (SELECT COUNT(rr.id) FROM ReReply rr JOIN rr.reply r where r.post = p) as replyCount, "
            + " u.id, u.loginId) " // User의 ID와 loginId만 가져옴
            + " FROM Post p "
            + " LEFT JOIN Likes l ON p = l.post "
            + " LEFT JOIN p.user u " // User와의 LEFT JOIN 추가
            + " WHERE lower(p.postNickname) like lower(concat('%', :nickname, '%'))"
            + " AND p.board.id = :boardId"
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findByPostNicknameContainsIgnoreCaseOrderByIdDesc(@Param("nickname") String nickname, @Param("boardId") Long boardId, Pageable pageable);

    /**
     * 제목 또는 내용으로 검색:
     * select * from posts p
     * where lower(p.title) like lower('%' || ? || '%')
     *    or lower(p.content) like lower('%' || ? || '%')
     * order by p.id desc
     * @param title
     * @param content
     * @param boardId
     * @param pageable
     * @return
     */
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l WHERE l.post = p AND l.likeDislike = 1), "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) + (SELECT COUNT(rr.id) FROM ReReply rr JOIN rr.reply r where r.post = p) as replyCount, "
            + " u.id, u.loginId) " // User의 ID와 loginId만 가져옴
            + " FROM Post p "
            + " LEFT JOIN Likes l ON p = l.post "
            + " LEFT JOIN p.user u " // User와의 LEFT JOIN 추가
            + " WHERE (lower(p.postTitle) like lower(concat('%', :title, '%')) AND p.board.id = :boardId"
            + " OR lower(p.postContent) like lower(concat('%', :content, '%')) AND p.board.id = :boardId)"
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findByTitleContainsIgnoreCaseOrContentContainsIgnoreCaseOrderByIdDesc(@Param("title") String title, @Param("content") String content, @Param("boardId") Long boardId, Pageable pageable);

    @Query(
        "select p from Post p " +
        " where lower(p.postTitle) like lower('%' || :keyword || '%') " + 
        " or lower(p.postContent) like lower('%' || :keyword || '%') " +
        " order by p.id desc"
    )
    List<Post> searchByKeyword(@Param("keyword") String keyword);

    /**
     * POST + 좋아요 수 + 댓글 수 조회 (전체글)
     * @param boardId
     * @param pageable
     * @return
     */
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l where l.post = p and l.likeDislike = 1) as likesCount, "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) + (SELECT COUNT(rr.id) FROM ReReply rr JOIN rr.reply r where r.post = p) as replyCount, "
            + " u.id, u.loginId) " // User의 ID와 loginId만 가져옴
            + " FROM Post p "
            + " LEFT JOIN Likes l ON p = l.post "
            + " LEFT JOIN p.user u " // User와의 LEFT JOIN 추가
            + " WHERE p.board.id = :boardId "
            + " GROUP BY p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, u.id, u.loginId " // User의 ID와 loginId 추가
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findAllPostsWithLikesCount(@Param("boardId") Long boardId, Pageable pageable);



    
    /**
     * 인기글 조회(POST + 좋아요 수  + 댓글 수)
     * @param boardId
     * @param pageable
     * @return
     */
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount2(p.id as id, p.postType as postType, p.postTitle as postTitle, p.postNickname as postNickname, p.createdTime as createdTime, p.postViews as postViews, p.postIp as postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l WHERE l.post = p AND l.likeDislike = 1) as likesCount, "
            + " (SELECT COUNT(l.id) FROM Likes l WHERE l.post = p AND l.likeDislike = 1) - "
            + " (SELECT COUNT(l.id) FROM Likes l WHERE l.post = p AND l.likeDislike = 0) as likesDifference, "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) + (SELECT COUNT(rr.id) FROM ReReply rr JOIN rr.reply r where r.post = p) as replyCount, "
            + " p.user.id as userId, p.user.loginId as userLoginId) " // User의 ID와 loginId 추가
            + " FROM Post p LEFT JOIN p.user u " // User 엔터티에 대한 LEFT JOIN 추가
            + " WHERE p.board.id = :boardId " // board.id가 boardId와 일치
            + " GROUP BY p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, p.user.id, p.user.loginId " // User의 ID와 loginId 추가
            + " HAVING (SELECT COUNT(l.id) FROM Likes l WHERE l.post = p AND l.likeDislike = 1) - "
            + " (SELECT COUNT(l.id) FROM Likes l WHERE l.post = p AND l.likeDislike = 0) >= 5 "
            + " ORDER BY p.id DESC")
    Page<PostWithLikesCount2> findAllPostsWithLikesDifference(@Param("boardId") Long boardId, Pageable pageable);

    
    /**
     * 공지글 조회(POST + 좋아요 수  + 댓글 수)
     * @param boardId
     * @param pageable
     * @return
     */
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l where l.post = p and l.likeDislike = 1) as likesCount, "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) + (SELECT COUNT(rr.id) FROM ReReply rr JOIN rr.reply r where r.post = p) as replyCount, "
            + " u.id, u.loginId) " // User의 ID와 loginId만 가져옴
            + " FROM Post p "
            + " LEFT JOIN Likes l ON p = l.post "
            + " LEFT JOIN p.user u " // User와의 LEFT JOIN 추가
            + " WHERE p.board.id = :boardId "
            + " AND p.postType = '공지' "
            + " GROUP BY p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, u.id, u.loginId " // User의 ID와 loginId 추가
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findAllPostsWithLikesCountWhenNotice(@Param("boardId") Long boardId, Pageable pageable);
    
    // 일반글 조회
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l where l.post = p and l.likeDislike = 1) as likesCount, "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) as replyCount) "
            + " FROM Post p LEFT JOIN Likes l "
            + " ON p = l.post "
            + " WHERE p.board.id = :boardId " // 해당 board.id
            + " AND p.postType = '일반' "
            + " GROUP BY p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp "
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findAllPostsWithLikesCountWhenNormal(@Param("boardId") Long boardId, Pageable pageable);
    
    // 뉴스글 조회
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l where l.post = p and l.likeDislike = 1) as likesCount, "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) as replyCount) "
            + " FROM Post p LEFT JOIN Likes l "
            + " ON p = l.post "
            + " WHERE p.board.id = :boardId " // 해당 board.id
            + " AND p.postType = '뉴스' "
            + " GROUP BY p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp "
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findAllPostsWithLikesCountWhenNews(@Param("boardId") Long boardId, Pageable pageable);
    
    // SNS글 조회
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l where l.post = p and l.likeDislike = 1) as likesCount, "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) as replyCount) "
            + " FROM Post p LEFT JOIN Likes l "
            + " ON p = l.post "
            + " WHERE p.board.id = :boardId " // 해당 board.id
            + " AND p.postType = 'SNS' "
            + " GROUP BY p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp "
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findAllPostsWithLikesCountWhenSns(@Param("boardId") Long boardId, Pageable pageable);
    
    // 사진글 조회
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l where l.post = p and l.likeDislike = 1) as likesCount, "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) as replyCount) "
            + " FROM Post p LEFT JOIN Likes l "
            + " ON p = l.post "
            + " WHERE p.board.id = :boardId " // 해당 board.id
            + " AND p.postType = '사진/움짤' "
            + " GROUP BY p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp "
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findAllPostsWithLikesCountWhenPicture(@Param("boardId") Long boardId, Pageable pageable);
    
    // 이벤트글 조회
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l where l.post = p and l.likeDislike = 1) as likesCount, "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) as replyCount) "
            + " FROM Post p LEFT JOIN Likes l "
            + " ON p = l.post "
            + " WHERE p.board.id = :boardId " // 해당 board.id
            + " AND p.postType = '이벤트' "
            + " GROUP BY p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp "
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findAllPostsWithLikesCountWhenEvent(@Param("boardId") Long boardId, Pageable pageable);
    
    /**
     * 게시글 말머리 별 필터 조회
     * 말머리 별 조회(POST + 좋아요 수  + 댓글 수)
     * @param boardId
     * @param postType
     * @param pageable
     * @return
     */
    @Query("SELECT new com.example.forest.dto.post.PostWithLikesCount(p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, "
            + " (SELECT COUNT(l.id) FROM Likes l where l.post = p and l.likeDislike = 1) as likesCount, "
            + " (SELECT COUNT(r.id) FROM Reply r where r.post = p) + (SELECT COUNT(rr.id) FROM ReReply rr JOIN rr.reply r where r.post = p) as replyCount, "
            + " p.user.id, p.user.loginId) " // User의 ID와 loginId 추가
            + " FROM Post p LEFT JOIN p.user u " // User 엔터티에 대한 LEFT JOIN 추가
            + " LEFT JOIN Likes l ON p = l.post "
            + " WHERE p.board.id = :boardId " // 해당 board.id
            + " AND p.postType = :postType "
            + " GROUP BY p.id, p.postType, p.postTitle, p.postNickname, p.createdTime, p.postViews, p.postIp, p.user.id, p.user.loginId " // User의 ID와 loginId 추가
            + " ORDER BY p.id desc")
    Page<PostWithLikesCount> findAllPostsWithLikesCountByType(@Param("boardId") Long boardId, @Param("postType") String postType, Pageable pageable);

    
    /**
     * postId로 board.id를 구하기 위함
     * @param postId
     * @return
     */
    @Query("SELECT p.board.id FROM Post p WHERE p.id = :postId")
    Long findBoardIdByPostId(@Param("postId") Long postId);


    /**
     * 서원준
     * 게시판 삭제시 작성된 모든 게시물 삭제
     * @param board
     */
    @Transactional
	@Modifying
    void deleteByBoard(@Param("board") Board board);
    
    @Query("select new com.example.forest.dto.stats.PostUserDto ( "
    		+ " CASE "
    		+ "		WHEN p.user.id IS NULL THEN '익명 유저' "
    		+ "		ELSE '회원' "
    		+ " END AS userType, COUNT(p.id) AS count) "
    		+ " from Post p"
    		+ " group by "
    		+ " CASE "
    		+ " 	WHEN p.user.id IS NULL THEN '익명 유저' "
    		+ " 	ELSE '회원' "
    		+ " END")
    List<PostUserDto> getPostWriterStats();

    /**
     * 김선아
     * gallog 게시물 리스트
     */
    @Transactional
    @Query("select p from Post p "
            + " where p.user = :user "
            + " order by p.id desc")
    List<Post> findAllPostByUserOrderByIdDesc(@Param("user") User user);

    
    @Transactional
    @Query( "select p from Post p "
            + " where p.user = :user "
            + " order by p.id desc ")
    List<Post> findByOrderByuserIdDesc(@Param("user") User user);

    
    
}