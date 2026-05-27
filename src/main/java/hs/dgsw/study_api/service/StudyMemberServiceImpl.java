package hs.dgsw.study_api.service;

import hs.dgsw.study_api.domain.RoleType;
import hs.dgsw.study_api.domain.StatusType;
import hs.dgsw.study_api.domain.Study;
import hs.dgsw.study_api.domain.StudyMember;
import hs.dgsw.study_api.repository.StudyMemberRepository;
import hs.dgsw.study_api.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyMemberServiceImpl implements StudyMemberService {
    private final StudyMemberRepository studyMemberRepository;
    private final StudyRepository studyRepository;

    @Override
    @Transactional
    public StudyMember apply(Long studyId, Long userId) {
        if (!studyRepository.existsById(studyId)) {
            throw new IllegalArgumentException("스터디를 찾을 수 없습니다.");
        }
        if (studyMemberRepository.existsByUserIdAndStudyId(userId, studyId)) {
            throw new IllegalArgumentException("이미 신청한 스터디입니다.");
        }

        StudyMember studyMember = StudyMember.builder()
                .studyId(studyId)
                .userId(userId)
                .role(RoleType.MEMBER)
                .status(StatusType.PENDING)
                .build();

        return studyMemberRepository.save(studyMember);
    }

    @Override
    @Transactional
    public StudyMember approve(Long studyId, Long ownerId, Long userId, boolean approved) {
        if (!isOwner(studyId, ownerId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        StudyMember target = studyMemberRepository.findByUserIdAndStudyIdAndStatus(userId, studyId, StatusType.PENDING)
                .orElseThrow(() -> new IllegalArgumentException("승인 대기 중인 멤버가 아닙니다."));

        if (approved) {
            long approvedCount = studyMemberRepository.countByStudyIdAndStatus(studyId, StatusType.APPROVED);
            Study study = studyRepository.findById(studyId)
                    .orElseThrow(() -> new IllegalArgumentException("스터디를 찾을 수 없습니다."));
            if (approvedCount >= study.getMaxMember()) {
                throw new IllegalArgumentException("스터디 정원이 가득 찼습니다.");
            }
            target.updateStatus(StatusType.APPROVED);
        } else {
            target.updateStatus(StatusType.REJECTED);
        }

        return studyMemberRepository.save(target);
    }

    @Override
    @Transactional
    public void leave(Long studyId, Long userId) {
        StudyMember member = studyMemberRepository.findByUserIdAndStudyId(userId, studyId)
                .orElseThrow(() -> new IllegalArgumentException("스터디 멤버가 아닙니다."));

        if (member.getRole() == RoleType.OWNER) {
            throw new IllegalArgumentException("스터디장은 스터디를 탈퇴할 수 없습니다.");
        }
        studyMemberRepository.delete(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudyMember> getApprovedMembers(Long studyId) {
        return studyMemberRepository.findAllByStudyIdAndStatus(studyId, StatusType.APPROVED);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudyMember> getAllMembers(Long studyId) {
        return studyMemberRepository.findAllByStudyId(studyId);
    }

    @Override
    @Transactional
    public void deleteByStudyId(Long studyId) {
        studyMemberRepository.deleteAllByStudyId(studyId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isOwner(Long studyId, Long userId) {
        return studyMemberRepository.existsByUserIdAndStudyIdAndRoleAndStatus(userId, studyId, RoleType.OWNER, StatusType.APPROVED);
    }
}
