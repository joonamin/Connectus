package social.connectus.userservice.domain.port.outbound.repository;



import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import social.connectus.userservice.domain.model.entity.QUser;
import social.connectus.userservice.domain.model.entity.User;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;
	private final PasswordEncoder passwordEncoder;

}
