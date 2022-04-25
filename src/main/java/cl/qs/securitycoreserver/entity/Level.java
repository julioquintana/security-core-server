package cl.qs.securitycoreserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "levels")
public class Level implements Serializable {

  @Serial
  private static final long serialVersionUID = 1;

  @Id
  @Column(name = "id", updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "created_for", updatable = false)
  private String createdFor;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @Valid
  @JsonManagedReference
  @OneToMany(mappedBy = "level", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<User> users;

  @Valid

  public Set<User> addListVariant(Set<User> variants) {
    Set<User> variantList = new HashSet<>();
    for (User variant : variants) {
      variantList.add(addUser(variant));

    }
    return variantList;
  }

  public User addUser(User user) {
    if (Objects.isNull(getUsers())) {
      setUsers(new HashSet<>());
    }
    getUsers().add(user);
    user.setLevel(this);
    return user;
  }


}
