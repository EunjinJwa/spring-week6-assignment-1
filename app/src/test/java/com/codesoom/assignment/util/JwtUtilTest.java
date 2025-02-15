package com.codesoom.assignment.util;

import com.codesoom.assignment.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtUtilTest {

	final String SECRET = "12345678901234567890123456789010";
	final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.neCsyNLzy3lQ4o2yliotWT06FwSGZagaHpKdAkjnGGw";
	final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.neCsyNLzy3lQ4o2yliotWT06FwSGZagaHpKdAkjn000";

	@Test
	public void createToke() {
		User user = User.builder().id(1L).build();
		String token = JwtUtil.createToken(user, SECRET);

		assertThat(token).isEqualTo(VALID_TOKEN);
	}

	@Test
	public void decodeWithValidToken() {
		Claims claims = JwtUtil.decode(VALID_TOKEN, SECRET);

		assertThat(claims.get("userId", Long.class)).isEqualTo(1L);
	}

	@Test
	public void decodeWithInvalidToken() {
		assertThatThrownBy(() -> JwtUtil.decode(INVALID_TOKEN, SECRET)).isInstanceOf(
			SignatureException.class);
	}

}
