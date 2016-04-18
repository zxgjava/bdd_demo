package cn.devit.demo.bdd;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class _1RestTemplate {

    RestTemplate rest = new RestTemplate();

    StringMetric metric = StringMetrics.mongeElkan();

    String body = "<!DOCTYPE html>\n" +
            "<html ng-app=\"module.main\">\n" +
            "<head lang=\"zh-CN\">\n" +
            "    <base href=\"/\">\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n" +
            "    <title>模客</title>\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/app.css\">\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\">\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/font-awesome.min.css\">\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/angular-growl.min.css\">\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/jquery.qtip.min.css\">\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/codemirror.css\">\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div ui-view></div>\n" +
            "    <div growl></div>\n" +
            "    <script src=\"js/lib.js\"></script>\n" +
            "    <script src=\"js/app.js\"></script>\n" +
            "</body>\n" +
            "</html>";

    @Test
    public void login_page() throws Exception {
        ResponseEntity<String> response = rest.getForEntity("http://mock-api.com/", String.class);

        assertThat(response.getStatusCode(),is(HttpStatus.OK));

        assertThat(metric.compare(body, response.getBody()),greaterThan(0.9F));
    }
}
