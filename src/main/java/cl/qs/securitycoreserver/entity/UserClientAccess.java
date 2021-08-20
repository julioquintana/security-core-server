
import javax.persistence.*;

@Entity
@Table(name = "user_client_access")
public class UserClientAccess {
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "created_for")
    private String createdFor;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "create_at")
    private null createAt;

    @Column(name = "update_at")
    private null updateAt;

    public Long getClientId() {
        return this.clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getCreatedFor() {
        return this.createdFor;
    }

    public void setCreatedFor(String createdFor) {
        this.createdFor = createdFor;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public null getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(null createAt) {
        this.createAt = createAt;
    }

    public null getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(null updateAt) {
        this.updateAt = updateAt;
    }
}
