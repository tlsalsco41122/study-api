package hs.dgsw.study_api.service;

import hs.dgsw.study_api.domain.RoleType;
import hs.dgsw.study_api.domain.StatusType;
import hs.dgsw.study_api.domain.Study;
import hs.dgsw.study_api.domain.StudyMember;
import hs.dgsw.study_api.dto.req.CreateStudyReq;
import hs.dgsw.study_api.dto.req.UpdateStudyReq;
import hs.dgsw.study_api.repository.StudyMemberRepository;
import hs.dgsw.study_api.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {
    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyMemberService studyMemberService;

    @Override
    @Transactional
    public Study createStudy(CreateStudyReq req, Long userId) {
        Study study = Study.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .maxMember(req.getMaxMember())
                .build();

        Study savedStudy = studyRepository.save(study);

        StudyMember owner = StudyMember.builder()
                .studyId(savedStudy.getId())
                .userId(userId)
                .role(RoleType.OWNER)
                .status(StatusType.APPROVED)
                .build();

        studyMemberRepository.save(owner);
        return savedStudy;
    }

    @Override
    @Transactional
    public Study updateStudy(Long studyId, UpdateStudyReq req, Long userId) {
        if (!studyMemberService.isOwner(studyId, userId)) {
            throw new IllegalArgumentException("스터디장만 수정할 수 있습니다.");
        }
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("스터디를 찾을 수 없습니다."));

        study.update(req.getTitle(), req.getDescription(), req.getMaxMember());
        return studyRepository.save(study);
    }

    @Override
    @Transactional
    public void deleteStudy(Long studyId, Long userId) {
        if (!studyMemberService.isOwner(studyId, userId)) {
            throw new IllegalArgumentException("스터디장만 삭제할 수 있습니다.");
        }
        if (!studyRepository.existsById(studyId)) {
            throw new IllegalArgumentException("스터디를 찾을 수 없습니다.");
        }
        studyMemberService.deleteByStudyId(studyId);
        studyRepository.deleteById(studyId);
    }

    @Override
    public Study findById(Long studyId) {
        return studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("스터디를 찾을 수 없습니다."));
    }

    @Override
    public List<Study> findAll() {
        return studyRepository.findAll();
    }
}
