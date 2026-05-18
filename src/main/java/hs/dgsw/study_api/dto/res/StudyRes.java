package hs.dgsw.study_api.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudyRes {
    private Long id;
    private String title;
    private String description;
    private int maxMember;
}
