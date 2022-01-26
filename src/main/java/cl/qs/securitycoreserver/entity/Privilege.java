package cl.qs.securitycoreserver.entity;

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
@Table(name = "privileges")
public class Privilege implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "module_id")
    private String moduleId;

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

    @Valid
    @JsonManagedReference
    @OneToMany(mappedBy = "privileges", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserAuthorization> userAuthorizations;

    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id", referencedColumnName = "application_id", insertable = false, updatable = false)
    @JoinColumn(name = "module_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JoinColumn(name = "created_for", referencedColumnName = "created_for", insertable = false, updatable = false)
    private Module modules;

}
