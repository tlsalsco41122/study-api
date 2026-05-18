package hs.dgsw.study_api.service;

import hs.dgsw.study_api.domain.Study;
import hs.dgsw.study_api.dto.req.CreateStudyReq;
import hs.dgsw.study_api.dto.req.UpdateStudyReq;

import java.util.List;

public interface StudyService {
    Study createStudy(CreateStudyReq req, Long userId);
    Study updateStudy(Long studyId, UpdateStudyReq req, Long userId);
    void deleteStudy(Long studyId, Long userId);
    Study findById(Long studyId);
    List<Study> findAll();
}
