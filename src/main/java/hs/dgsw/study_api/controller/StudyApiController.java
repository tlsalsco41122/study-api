package hs.dgsw.study_api.controller;

import hs.dgsw.study_api.domain.Study;
import hs.dgsw.study_api.domain.StudyMember;
import hs.dgsw.study_api.dto.req.ApproveStudyMemberReq;
import hs.dgsw.study_api.dto.req.CreateStudyReq;
import hs.dgsw.study_api.dto.req.UpdateStudyReq;
import hs.dgsw.study_api.dto.res.StudyDetailRes;
import hs.dgsw.study_api.dto.res.StudyMemberRes;
import hs.dgsw.study_api.dto.res.StudyRes;
import hs.dgsw.study_api.security.CustomUserDetails;
import hs.dgsw.study_api.service.StudyMemberService;
import hs.dgsw.study_api.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study")
public class StudyApiController {
    private final StudyService studyService;
    private final StudyMemberService studyMemberService;

    @PostMapping("/create")
    public ResponseEntity<StudyRes> createStudy(@RequestBody CreateStudyReq req,
                                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        Study study = studyService.createStudy(req, userDetails.getUser().getId());
        StudyRes studyRes = new StudyRes(study.getId(), study.getTitle(), study.getDescription(), study.getMaxMember());

        return ResponseEntity.ok(studyRes);
    }

    @PutMapping("/{studyId}")
    public ResponseEntity<StudyRes> updateStudy(@PathVariable Long studyId,
                                                @RequestBody UpdateStudyReq req,
                                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        Study study = studyService.updateStudy(studyId, req, userDetails.getUser().getId());
        StudyRes studyRes = new StudyRes(study.getId(), study.getTitle(), study.getDescription(), study.getMaxMember());

        return ResponseEntity.ok(studyRes);
    }

    @DeleteMapping("/{studyId}")
    public ResponseEntity<Void> deleteStudy(@PathVariable Long studyId,
                                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        studyService.deleteStudy(studyId, userDetails.getUser().getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<StudyRes>> getAllStudies() {
        List<StudyRes> studies = studyService.findAll().stream()
                .map(study -> new StudyRes(study.getId(), study.getTitle(), study.getDescription(), study.getMaxMember()))
                .toList();
        return ResponseEntity.ok(studies);
    }

    @GetMapping("/{studyId}")
    public ResponseEntity<StudyDetailRes> getStudy(@PathVariable Long studyId) {
        Study study = studyService.findById(studyId);
        List<StudyMemberRes> members = studyMemberService.getApprovedMembers(studyId).stream()
                .map(member -> new StudyMemberRes(member.getId(), member.getUserId(), member.getStudyId(), member.getRole(), member.getStatus()))
                .toList();
        StudyDetailRes studyRes = new StudyDetailRes(
                study.getId(),
                study.getTitle(),
                study.getDescription(),
                study.getMaxMember(),
                members
        );
        return ResponseEntity.ok(studyRes);
    }

    // 스터디 가입 신청
    @PostMapping("/{studyId}/apply")
    public ResponseEntity<StudyMemberRes> applyStudy(@PathVariable Long studyId,
                                                     @AuthenticationPrincipal CustomUserDetails userDetails) {
        StudyMember member = studyMemberService.apply(studyId, userDetails.getUser().getId());
        StudyMemberRes res = new StudyMemberRes(member.getId(), member.getUserId(), member.getStudyId(), member.getRole(), member.getStatus());
        return ResponseEntity.ok(res);
    }

    // 스터디 가입 승인/거절
    @PostMapping("/{studyId}/approve")
    public ResponseEntity<StudyMemberRes> approveStudyMember(@PathVariable Long studyId,
                                                             @RequestBody ApproveStudyMemberReq req,
                                                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        StudyMember member = studyMemberService.approve(studyId, userDetails.getUser().getId(), req.getUserId(), req.isApproved());
        StudyMemberRes res = new StudyMemberRes(member.getId(), member.getUserId(), member.getStudyId(), member.getRole(), member.getStatus());
        return ResponseEntity.ok(res);
    }

    // 스터디 탈퇴
    @PostMapping("/{studyId}/leave")
    public ResponseEntity<Void> leaveStudy(@PathVariable Long studyId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        studyMemberService.leave(studyId, userDetails.getUser().getId());
        return ResponseEntity.ok().build();
    }
}
