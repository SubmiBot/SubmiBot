package p2.tests.resources;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import p2.submibot.resources.UserInfo;

class UserInfoTest {

	UserInfo aUserInfo, anotherUserInfo;
	
	@BeforeEach
	void initUserInfo() {
		aUserInfo = new UserInfo("User", "Info", "X~JKDSA16DSAD1461T35T5");
	}
	
	@Test
	void testUserInfo() {
		assertTrue(anotherUserInfo == null);
		anotherUserInfo = new UserInfo("not", "null", "X~T35TT0K3N");
		assertFalse(anotherUserInfo == null);
	}

	@Test
	void testGetName() {
		assertEquals("User", aUserInfo.getName());
	}

	@Test
	void testGetSurname() {
		assertEquals("Info", aUserInfo.getSurname());
	}

	@Test
	void testGetToken() {
		assertEquals("X~JKDSA16DSAD1461T35T5", aUserInfo.getToken());
	}

	@Test
	void testToString() {
		assertEquals("User - Info", aUserInfo.toString());
	}

}
