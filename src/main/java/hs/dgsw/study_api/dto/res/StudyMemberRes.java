package hs.dgsw.study_api.dto.res;

import hs.dgsw.study_api.domain.RoleType;
import hs.dgsw.study_api.domain.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudyMemberRes {
    private Long id;
    private Long userId;
    private Long studyId;
    private RoleType role;
    private StatusType status;
}

