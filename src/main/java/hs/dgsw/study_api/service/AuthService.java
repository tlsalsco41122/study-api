package hs.dgsw.study_api.service;

import hs.dgsw.study_api.domain.User;
import hs.dgsw.study_api.dto.req.SignInReq;
import hs.dgsw.study_api.dto.req.SignUpReq;
import hs.dgsw.study_api.dto.res.SignInRes;

public interface AuthService {
    User signUp(SignUpReq req);
    SignInRes signIn(SignInReq req);

}
