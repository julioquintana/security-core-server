package cl.qs.securitycoreserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_privileges")
public class UserPrivilege {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_client_access_id")
    private Long userClientAccessId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "privileges_id")
    private Long privilegesId;

    @Column(name = "module_id")
    private String moduleId;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "created_for")
    private String createdFor;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "user_client_access_id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    })
    private UserClientAccess userClientAccess;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "client_id", referencedColumnName = "client_id", insertable = false, updatable = false),
            @JoinColumn(name = "module_id", referencedColumnName = "module_id", insertable = false, updatable = false),
            @JoinColumn(name = "privileges_id", referencedColumnName = "id", insertable = false, updatable = false)
    })
    private Privilege privileges;
}
