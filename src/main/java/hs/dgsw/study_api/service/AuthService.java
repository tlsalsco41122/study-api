package hs.dgsw.study_api.service;

import hs.dgsw.study_api.dto.req.SignInReq;
import hs.dgsw.study_api.dto.req.SignUpReq;
import hs.dgsw.study_api.dto.res.SignInRes;
import hs.dgsw.study_api.dto.res.UserRes;

public interface AuthService {
    UserRes signUp(SignUpReq req);
    SignInRes signIn(SignInReq req);
}
