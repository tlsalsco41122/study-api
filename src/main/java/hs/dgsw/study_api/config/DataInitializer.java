package hs.dgsw.study_api.config;

import hs.dgsw.study_api.domain.RoleType;
import hs.dgsw.study_api.domain.StatusType;
import hs.dgsw.study_api.domain.Study;
import hs.dgsw.study_api.domain.StudyMember;
import hs.dgsw.study_api.domain.User;
import hs.dgsw.study_api.repository.StudyMemberRepository;
import hs.dgsw.study_api.repository.StudyRepository;
import hs.dgsw.study_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        User owner = userRepository.save(User.builder()
                .username("owner")
                .password(passwordEncoder.encode("owner1234"))
                .build());

        User member = userRepository.save(User.builder()
                .username("member")
                .password(passwordEncoder.encode("member1234"))
                .build());

        User pending = userRepository.save(User.builder()
                .username("pending")
                .password(passwordEncoder.encode("pending1234"))
                .build());

        Study study = studyRepository.save(Study.builder()
                .title("Spring Study")
                .description("Spring Boot basics and API practice")
                .maxMember(5)
                .build());

        Study extraStudy = studyRepository.save(Study.builder()
                .title("Algorithm Study")
                .description("Weekly algorithm problem solving")
                .maxMember(3)
                .build());

        studyMemberRepository.save(StudyMember.builder()
                .studyId(study.getId())
                .userId(owner.getId())
                .role(RoleType.OWNER)
                .status(StatusType.APPROVED)
                .build());

        studyMemberRepository.save(StudyMember.builder()
                .studyId(study.getId())
                .userId(member.getId())
                .role(RoleType.MEMBER)
                .status(StatusType.APPROVED)
                .build());

        studyMemberRepository.save(StudyMember.builder()
                .studyId(study.getId())
                .userId(pending.getId())
                .role(RoleType.MEMBER)
                .status(StatusType.PENDING)
                .build());

        studyMemberRepository.save(StudyMember.builder()
                .studyId(extraStudy.getId())
                .userId(member.getId())
                .role(RoleType.OWNER)
                .status(StatusType.APPROVED)
                .build());
    }
}

