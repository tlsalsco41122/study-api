package hs.dgsw.study_api.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudyDetailRes {
    private Long id;
    private String title;
    private String description;
    private int maxMember;
    private List<StudyMemberRes> members;
}

