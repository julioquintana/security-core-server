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
@Table(name = "client_application_access")
public class ClientApplicationAccess implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "description")
    private String description;

    @Column(name = "apikey")
    private String apikey;

    @Column(name = "status")
    private boolean status;

    @Column(name = "created_for")
    private String createdFor;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;


    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JoinColumn(name = "created_for", referencedColumnName = "created_for", insertable = false, updatable = false)
    private Client client;

    @Valid
    @JsonManagedReference
    @OneToMany(mappedBy = "clientApplicationAccess", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserAuthorization> userAuthorizations;
}
