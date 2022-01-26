package cl.qs.securitycoreserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_privileges")
public class UserAuthorization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_access_id")
    private Long userAccessId;

    @Column(name = "privileges_id")
    private Long privilegesId;

    @Column(name = "module_id")
    private String moduleId;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "created_for")
    private String createdFor;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_access_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JoinColumn(name = "created_for", referencedColumnName = "created_for", insertable = false, updatable = false)
    private UserApplicationAccess userApplicationAccess;

    @ManyToOne
    @JoinColumn(name = "user_access_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JoinColumn(name = "created_for", referencedColumnName = "created_for", insertable = false, updatable = false)
    private ClientApplicationAccess clientApplicationAccess;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "application_id", insertable = false, updatable = false)
    @JoinColumn(name = "module_id", referencedColumnName = "module_id", insertable = false, updatable = false)
    @JoinColumn(name = "privileges_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JoinColumn(name = "created_for", referencedColumnName = "created_for", insertable = false, updatable = false)
    private Privilege privileges;
}
