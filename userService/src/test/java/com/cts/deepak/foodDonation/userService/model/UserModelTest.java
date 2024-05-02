package com.cts.deepak.foodDonation.userService.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class UserModelTest {

    @Test
    public void testUserConstructor() {
        User user1 = new User(1L, "deepak@gmail.com", "Dy@12345", "7071017854", "DONOR");
        User user2 = new User(2L, "mudit@gmail.com", "Ms@12345", "9839206589", "NGO");

        assertNotNull(user1);
        assertEquals(1L, user1.getUserId());
        assertEquals("deepak@gmail.com", user1.getUsername());
        assertEquals("Dy@12345", user1.getPassword());
        assertEquals("7071017854", user1.getMobileNumber());
        assertEquals("DONOR", user1.getUserType());
        
        assertNotNull(user2);
        assertEquals(2L, user2.getUserId());
        assertEquals("mudit@gmail.com", user2.getUsername());
        assertEquals("Ms@12345", user2.getPassword());
        assertEquals("9839206589", user2.getMobileNumber());
        assertEquals("NGO", user2.getUserType());
    }

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();

        user.setUserId(1L);
        user.setUsername("deepak@gmail.com");
        user.setPassword("Dy@12345");
        user.setMobileNumber("7071017854");
        user.setUserType("DONOR");

        assertEquals(1L, user.getUserId());
        assertEquals("deepak@gmail.com", user.getUsername());
        assertEquals("Dy@12345", user.getPassword());
        assertEquals("7071017854", user.getMobileNumber());
        assertEquals("DONOR", user.getUserType());
        
//        user.setUserId(1L);
//        user.setUsername("mudit@gmail.com");
//        user.setPassword("Ms@12345");
//        user.setMobileNumber("9839206589");
//        user.setUserType("NGO");
//        
//        assertNotNull(user2);
//        assertEquals(1L, user2.getUserId());
//        assertEquals("mudit@gmail.com", user2.getUsername());
//        assertEquals("Ms@12345", user2.getPassword());
//        assertEquals("9839206589", user2.getMobileNumber());
//        assertEquals("NGO", user2.getUserType());
    }

    @Test
    public void testUserToString() {
        User user = new User(1L, "deepak@gmail.com", "Dy@12345", "7071017854", "DONOR");
        User user2 = new User(2L, "mudit@gmail.com", "Ms@12345", "9839206589", "NGO");
        
        String expectedString = "User(userId=1, username=deepak@gmail.com, password=Dy@12345, mobileNumber=7071017854, userType=DONOR)";
        String expectedString2 = "User(userId=2, username=mudit@gmail.com, password=Ms@12345, mobileNumber=9839206589, userType=NGO)";

        assertEquals(expectedString, user.toString());
        assertEquals(expectedString2, user2.toString());
        		
    }

    @Test
    public void testUserEqualsAndHashCode() {
        User user1 = new User(1L, "deepak@gmail.com", "Dy@12345", "7071017854", "DONOR");
        User user2 = new User(1L, "deepak@gmail.com", "Dy@12345", "7071017854", "DONOR");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
