package Dao;

public class UserSession {
private static int userId;
	
	private static int sessionId;
	
	private static int role;

    public static void setUserId(int id) {
        userId = id;
    }

    public static int getUserId() {
        return userId;
    }
    
    public static void setSessionId(int id) {
    	sessionId = id;
    }

    public static int getSessionId() {
        return sessionId;
    }
    
    public static void setRole(int id) {
    	role = id;
    }

    public static int getRole() {
        return role;
    }

}
