package webserver;

import controller.Controller;
import controller.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webserver.request.Method;
import webserver.request.Request;
import webserver.response.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RouteTest {

    static class MockController implements Controller {
        @Override
        public Response doGet(Request request) {
            return Response.of("OK".getBytes(), "text/html");
        }

        @Override
        public Response doPost(Request request) {
            throw new UnsupportedOperationException("HTTP method not supported");
        }
    }

    private Route route;

    @BeforeEach
    void setUp() {
        route = new Route("/home", new MockController(), Method.of("GET"));
    }

    @Test
    void testConstructorInitialization() {
        assertNotNull(route);
    }

    @Test
    void testMatchesSuccess() {
        assertTrue(route.matches("/home", Method.of("GET")));
    }

    @Test
    void testMatchesFailurePath() {
        assertFalse(route.matches("/about", Method.of("GET")));
    }

    @Test
    void testMatchesFailureMethod() {
        assertFalse(route.matches("/home", Method.of("POST")));
    }

    @Test
    void testHandleUnsupportedMethod() {
        Route postRoute = new Route("/home", new MockController(), Method.of("POST"));
        Request mockRequest = mock(Request.class);
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> postRoute.handle(mockRequest));

        assertEquals("HTTP method not supported", exception.getMessage());
    }
}

