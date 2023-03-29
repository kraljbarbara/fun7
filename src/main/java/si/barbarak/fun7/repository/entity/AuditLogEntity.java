package si.barbarak.fun7.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import si.barbarak.fun7.model.Action;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "AUDIT_LOG")
public class AuditLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ACTION")
    private Action action;

    @Basic
    @CreationTimestamp
    @Column(name = "CREATED")
    private Timestamp created;
    @Basic
    @Column(name = "USER_UUID")
    private String userUuid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditLogEntity that = (AuditLogEntity) o;
        return id == that.id && action == that.action && Objects.equals(userUuid, that.userUuid) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, userUuid, created);
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
