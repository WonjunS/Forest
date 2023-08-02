package com.example.forest.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.forest.dto.user.UserSignUpDto;
import com.example.forest.model.Role;
import com.example.forest.model.User;
import com.example.forest.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    public Long registerUser(UserSignUpDto dto) { //유저 회원가입
        log.info("registerUser(dto={})", dto);
        
        User entity = User.builder()
                .loginId(dto.getLoginId())
                .imageFile(dto.getImageId())
                .nickname(dto.getNickname())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build();
                    
        
        log.info("save 전: entity={}", entity);
        
        userRepository.save(entity); // 디비 집어넣기
        log.info("save후 : entity={}", entity);
        
        return entity.getId();
    }
    
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		
	    log.info("loadUserByUserLoginId(loginId)",loginId);
	    
	    UserDetails user = userRepository.findByLoginId(loginId);
	    if (user != null) {
	        return user;
	    }
	    throw new UsernameNotFoundException(loginId + " - not found");
	}

    public int validateLoginId(String loginId) {
        User user = userRepository.selectUserByLoginId(loginId);
        
        if(user == null) {
            return 1;
        }
        return 0;
    }
    
    public int validateLoginNickname(String nickname) {
        User user = userRepository.selectUserByNickname(nickname);
        if(user == null) {
            return 1;
            
        }
        return 0;
    }

    public int validateLoginEmail(String email) {
       User user = userRepository.selectUserByEmail(email);
       if(user == null) {
           return 1;
       }
        return 0;
    }

   

	
}