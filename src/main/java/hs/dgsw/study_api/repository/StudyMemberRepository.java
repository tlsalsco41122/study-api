package hs.dgsw.study_api.repository;

import hs.dgsw.study_api.domain.RoleType;
import hs.dgsw.study_api.domain.StatusType;
import hs.dgsw.study_api.domain.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {
    boolean existsByUserIdAndStudyId(Long userId, Long studyId);
    boolean existsByUserIdAndStudyIdAndRoleAndStatus(Long userId, Long studyId, RoleType role, StatusType status);
    long countByStudyIdAndStatus(Long studyId, StatusType status);
    Optional<StudyMember> findByUserIdAndStudyId(Long userId, Long studyId);
    Optional<StudyMember> findByUserIdAndStudyIdAndStatus(Long userId, Long studyId, StatusType status);
    List<StudyMember> findAllByStudyIdAndStatus(Long studyId, StatusType status);
    List<StudyMember> findAllByStudyId(Long studyId);
    void deleteAllByStudyId(Long studyId);
}
