package cl.qs.securitycoreserver.entity;

import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_privileges")
public class UserPrivileges {
    @Column(name = "user_client_access_id")
    private Long userClientAccessId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "privileges_id")
    private Long privilegesId;

    @Column(name = "module_id")
    private String moduleId;

    @Id
    @Column(name = "id")
    private Long id;

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
}
