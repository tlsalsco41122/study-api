package hs.dgsw.study_api.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudyReq {
    private String title;
    private String description;
    private int maxMember;
}

