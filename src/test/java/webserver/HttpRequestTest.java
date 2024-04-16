package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestTest {
    @Test
    void request_resttemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void path에_해당하는_file을_읽어온다() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/index.html", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void post요청시_사용자를_생성한다() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/user/create", "userId=1&password=password&name=Javajigi&email=javajigi%40slipp.net", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/index.html");
    }

    @Test
    @DisplayName("로그인이 되어있는 경우 로그인 페이지로 접근시 index 페이지로 이동한다.")
    void 로그인이_되어있는_경우_로그인_페이지로_접근시_index_페이지로_이동한다() {
        ResponseEntity<String> loginResponse = new RestTemplate().postForEntity("http://localhost:8080/user/login", "userId=admin&password=admin", String.class);
        String JSESSIONID = loginResponse.getHeaders().get("Set-Cookie").get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", JSESSIONID);
        HttpEntity request = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/user/login.html", org.springframework.http.HttpMethod.GET, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        ResponseEntity<String> indexResponse = restTemplate.exchange("http://localhost:8080/index.html", org.springframework.http.HttpMethod.GET, request, String.class);
        assertThat(response.getBody()).isEqualTo(indexResponse.getBody());
    }

    @Test
    @DisplayName("로그인이 되어있지 않은 경우 리스트 페이지는 로그인 페이지로 이동한다.")
    void 로그인이_되어있지_않은_경우_리스트_페이지는_로그인_페이지로_이동한다() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/user/list.html", String.class);
        ResponseEntity<String> loginResponse = restTemplate.getForEntity("http://localhost:8080/user/login.html", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(loginResponse.getBody());
    }
}
