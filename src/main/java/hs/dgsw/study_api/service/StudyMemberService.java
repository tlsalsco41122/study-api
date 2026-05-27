package hs.dgsw.study_api.service;

import hs.dgsw.study_api.domain.StudyMember;

import java.util.List;

public interface StudyMemberService {
    StudyMember apply(Long studyId, Long userId);
    StudyMember approve(Long studyId, Long ownerId, Long userId, boolean approved);
    void leave(Long studyId, Long userId);
    List<StudyMember> getApprovedMembers(Long studyId);
    List<StudyMember> getAllMembers(Long studyId);
    void deleteByStudyId(Long studyId);
    boolean isOwner(Long studyId, Long userId);
}
