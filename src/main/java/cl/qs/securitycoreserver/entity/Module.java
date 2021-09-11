package cl.qs.securitycoreserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "modules")
public class Module implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "level_id")
    private Long levelId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_for")
    private String createdFor;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "level_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Level level;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JoinColumn(name = "created_for", referencedColumnName = "created_for", insertable = false, updatable = false)
    private Application application;

    @Valid
    @JsonManagedReference
    @OneToMany(mappedBy = "modules", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Privilege> privileges;

}
