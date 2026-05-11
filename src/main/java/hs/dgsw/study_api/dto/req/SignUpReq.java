package hs.dgsw.study_api.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpReq {
    private String username;
    private String password;
}
