package hs.dgsw.study_api.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveStudyMemberReq {
    private Long userId;
    private boolean approved;
}

