package common;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {
	protected Long createdAt;
	protected Long updatedAt;

	// Entity가 DB에 Insert되기 전에 호출됨
	@PrePersist
	public void prePersist() {
		long currentTimeMillis = System.currentTimeMillis();
		this.createdAt = currentTimeMillis;
		this.updatedAt = currentTimeMillis;
	}

	// Entity가 DB에 Update되기 전에 호출됨
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = System.currentTimeMillis();
	}
}


