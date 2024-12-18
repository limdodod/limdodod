package hongik.project.service;

import hongik.project.domain.Item;
import hongik.project.domain.User;
import hongik.project.repository.ItemRepository;
import hongik.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //회원가입
    @Transactional //변경
    public Long join(User user) {
        validateDuplicateMember(user); //중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(User user) {
        List<User> findUser =
                userRepository.findByName(user.getName());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 로그인 처리
    public User login(String username, String password) {
        List<User> users = userRepository.findByName(username);
        if (!users.isEmpty()) {
            User user = users.get(0); // 첫 번째 사용자만 가져옴
            if (user.getPassword().equals(password)) {
                return user;  // 로그인 성공
            }
        }
        return null;  // 로그인 실패
    }

    //회원 전체 조회
    @Transactional
    public List<User> findUser() {
        return userRepository.findAll();
    }

    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }
}