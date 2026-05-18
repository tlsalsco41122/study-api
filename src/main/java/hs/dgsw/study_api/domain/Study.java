package hs.dgsw.study_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "study")
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "max_member", nullable = false)
    private int maxMember;

    @Builder
    public Study(String title, String description, int maxMember) {
        this.title = title;
        this.description = description;
        this.maxMember = maxMember;
    }

    public void update(String title, String description, int maxMember) {
        this.title = title;
        this.description = description;
        this.maxMember = maxMember;
    }
}
