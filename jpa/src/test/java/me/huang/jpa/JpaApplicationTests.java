package me.huang.jpa;

import lombok.extern.slf4j.Slf4j;
import me.huang.jpa.entity.po.Clan;
import me.huang.jpa.entity.po.Person;
import me.huang.jpa.entity.po.User;
import me.huang.jpa.entity.po.UserPo_;
import me.huang.jpa.entity.projection.UsernameAndCity;
import me.huang.jpa.entity.vo.UserVo;
import me.huang.jpa.repository.ClanRepository;
import me.huang.jpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class JpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClanRepository clanRepository;

	@BeforeEach
	void before() {	}

	@Test
	void testVersion() {
		User user = new User();
		userRepository.save(user);

		log.info("first version {}", user);

		user.setUsername("lily");
		userRepository.save(user);

		log.info("after update {}", user.getVersion());

		user = userRepository.findByUsername("lily");

		log.info("second version {}", user);
	}

	@Test
	void testEmbedded() {
		log.info("query embedded null {}", userRepository.findByUsername("wu").getAddress());
		log.info("query embedded partial null {}", userRepository.findByUsername("zhang").getAddress());

		User user = userRepository.findByAddressCity("guangzhou");

		assertThat(user.getUsername()).isEqualTo("huang");
	}

	@Test
	void testPaging() {
		Page<User> userPos = userRepository.findByAddressCity("xian", PageRequest.of(0, 1));

		log.info("total {}", userPos.getTotalElements());
		log.info("page count {}", userPos.getTotalPages());
		assertThat(userPos.getSize()).isEqualTo(1);
	}

	@Test
	void testSorting() {
		List<User> users = userRepository.findByAddressCity("xian",
				Sort.sort(User.class).by(User::getUsername));

		users.forEach(user -> log.info("{}", user));
	}

	@Test
	void testStreamable() {
		log.info("query streamable {}", userRepository.findByAddressProvince("shanxi").usernames());
	}

	@Test
	void testModifying() {
		log.info("before named updating {}", userRepository.findByUsername("huang"));
		log.info("{}", userRepository.updatePasswordByUsername("newPassword", "huang"));
		log.info("after named updating {}", userRepository.findByUsername("huang"));
	}

	@Test
	void testNamedQuery() {
		userRepository.findByAddressProvince("shanxi",
				Sort.by("username_len")).forEach(objects -> log.info("named sorted query {} {}", objects[0], objects[1]));
	}

	@Test
	void testSpecification() {
		log.info("specification query {}", userRepository.count(new Specification() {
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
				LocalDateTime date = LocalDateTime.now().minusYears(2);
				return criteriaBuilder.lessThan(root.get(UserPo_.CREATED_DATE), date);
			}
		}));
	}

	@Test
	void testQueryByExample() {
		User user = new User();
		user.setUsername("huang");
		log.info("query example {}", userRepository.count(Example.of(user)));

		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("username", match -> match.endsWith());
		user.setUsername("ng");
		log.info("query example matcher {}", userRepository.count(Example.of(user, exampleMatcher)));
	}

	@Test
	void testProjection() {
		UsernameAndCity usernameAndCity = userRepository.findByPassword("password3");
		log.info("closed projection {} {}", usernameAndCity.getUsername(), usernameAndCity.getAddress().getCity());

		log.info("open projection {}", userRepository.findByPasswordAndPhone("password4", "18177777777"));

		log.info("dynamic projection no.1 {}", userRepository.findByPassword("password3", UsernameAndCity.class));
		log.info("dynamic projection no.2 {}", userRepository.findByPassword("password3", User.class));
		log.info("dynamic projection no.3 {}", userRepository.findByPassword("password3", UserVo.class));
	}

	@Test
	@Transactional
	void testOneToMany() {
		Clan clan = clanRepository.findById(1L).orElse(new Clan());

		log.info("{}", clan.getPersons().size());
	}
}
