package com.example.forest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.forest.dto.board.BlackListDto;
import com.example.forest.model.BlackList;
import com.example.forest.model.Role;
import com.example.forest.model.User;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
	
	/**
	 * 특정 게시판에 등록되어 있는 사용자 블랙 리스트를 불러옴
	 * @param boardId
	 * @return
	 */
	@Query("select new com.example.forest.dto.board.BlackListDto(b.id, b.boardId, b.userId, b.ipAddress, u.nickname as nickname) "
			+ " from BlackList b, User u "
			+ " where b.userId = u.id "
			+ " and b.boardId = :boardId")
	List<BlackListDto> findAllByBoardId(@Param("boardId") long boardId);
	
//	@Query("select new com.example.forest.dto.board.BlackListDto(b.id, b.boardId, b.userId, b.ipAddress, u.nickname as nickname) "
//			+ " from BlackList b, User u "
//			+ " where b.userId = u.id "
//			+ " and b.boardId = :boardId "
//			+ " and lower(u.nickname) LIKE lower('%' || :keyword || '%')")
//	List<BlackListDto> findAllByBoardId(@Param("boardId") long boardId, @Param("keyword") String keyword);
	
	/**
	 * 블랙 리스트에 포함되어 있지 않은 사용자 리스트를 불러옴
	 * 관리자와 게시판 사용자 본인은 제외됨
	 * @param boardId
	 * @param userId
	 * @param role
	 * @return
	 */
	@Query("select u "
			+ " from User u "
			+ " where u.id not in (select b.userId from BlackList b where b.boardId = :boardId)"
			+ " and u.role != :role"
			+ " and u.id != :userId"
			+ " order by u.nickname")
	List<User> findAllUserNotInList(@Param("boardId") long boardId, @Param("userId") long userId, @Param("role") Role role);
	
	@Query("select u "
			+ " from User u "
			+ " where u.id not in (select b.userId from BlackList b where b.boardId = :boardId) "
			+ " and u.role != :role "
			+ " and u.id != :userId "
			+ " and lower(u.nickname) LIKE lower('%' || :keyword || '%')"
			+ " order by u.nickname")
	List<User> findAllUserNotInList(@Param("boardId") long boardId, @Param("userId") long userId, 
			@Param("role") Role role, @Param("keyword") String keyword);
	
	/**
	 * 해당 게시판에 특정 유저가 블랙 리스트에 등록 되어 있는지 확인
	 * @param boardId
	 * @param userId
	 * @return
	 */
	@Query("select b "
			+ " from BlackList b "
			+ " where b.userId = :userId "
			+ " and b.boardId = :boardId")
	BlackList findByBoardIdAndUserId(@Param("boardId") long boardId, @Param("userId") long userId);
	
	/**
	 * 특정 게시판의 블랙 리스트를 전부 삭제
	 * @param boardId
	 * @return
	 */
	@Transactional
	@Modifying
	void deleteByBoardId(@Param("boardId") long boardId);

}
