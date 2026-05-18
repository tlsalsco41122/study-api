package hs.dgsw.study_api.service;

import hs.dgsw.study_api.domain.User;

public interface UserService {
    User findById(Long id);
}
