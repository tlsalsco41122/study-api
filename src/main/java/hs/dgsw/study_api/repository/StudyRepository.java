package hs.dgsw.study_api.repository;

import hs.dgsw.study_api.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
