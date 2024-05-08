package social.connectus.userservice.domain.port.outbound.repository;

import static social.connectus.userservice.domain.model.entity.QUser.*;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.domain.model.entity.QUser;
import social.connectus.userservice.domain.model.entity.User;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<User> findByEmailAndRawPassword(String email, String rawPassword) {
		User user = jpaQueryFactory.selectFrom(QUser.user)
			.where(QUser.user.email.eq(email))
			.where(QUser.user.password.eq(passwordEncoder.encode(rawPassword)))
			.fetchOne();
		return Optional.ofNullable(user);
	}

}
