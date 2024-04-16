package webserver.session;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

public class SessionManager {
    private static final Map<String, Session> SESSIONS = new HashMap<>();

    public static Session createSession() {
        String sessionId = UUID.randomUUID().toString();
        Session session = new Session(sessionId);
        SESSIONS.put(sessionId, session);
        return session;
    }

    public static void add(final Session session) {
        SESSIONS.put(session.getId(), session);
    }

    public static Session findSession(final String id) {
        return SESSIONS.get(id);
    }

    public static void remove(final String id) {
        SESSIONS.remove(id);
    }
}
