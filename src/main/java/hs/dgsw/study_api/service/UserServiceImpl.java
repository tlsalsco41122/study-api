package hs.dgsw.study_api.service;

import hs.dgsw.study_api.domain.User;
import hs.dgsw.study_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("사용자를 찾을 수 없습니다.")));
    }
}
