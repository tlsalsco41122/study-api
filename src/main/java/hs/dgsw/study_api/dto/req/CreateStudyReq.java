package hs.dgsw.study_api.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudyReq {
    private String title;
    private String description;
    private int maxMember;
}
