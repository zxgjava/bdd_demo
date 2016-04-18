package cn.devit.demo.bdd;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jayway.restassured.RestAssured;

public class _2RestAssured {

    RestTemplate rest = new RestTemplate();

    StringMetric metric = StringMetrics.mongeElkan();

    String body = "<!DOCTYPE html>\n" + "<html ng-app=\"module.main\">\n"
            + "<head lang=\"zh-CN\">\n" + "    <base href=\"/\">\n"
            + "    <meta charset=\"UTF-8\">\n"
            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">\n"
            + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n"
            + "    <title>模客</title>\n"
            + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/app.css\">\n"
            + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\">\n"
            + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/font-awesome.min.css\">\n"
            + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/angular-growl.min.css\">\n"
            + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/jquery.qtip.min.css\">\n"
            + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/codemirror.css\">\n"
            + "</head>\n" + "\n" + "<body>\n" + "    <div ui-view></div>\n"
            + "    <div growl></div>\n"
            + "    <script src=\"js/lib.js\"></script>\n"
            + "    <script src=\"js/app.js\"></script>\n" + "</body>\n"
            + "</html>";

    @Test
    public void login_page() throws Exception {

        RestAssured.get("http://mock-api.com/").then().assertThat()
                .statusCode(200)
                .body(like(body, 0.9f));

    }

    TypeSafeMatcher<String> like(String template, float match) {

        return new TypeSafeMatcher<String>() {

            @Override
            public void describeTo(Description description) {
                description.appendText(template);
            }

            @Override
            protected boolean matchesSafely(String item) {
                StringMetric metric = StringMetrics.mongeElkan();
                return metric.compare(item, template) >= match;
            }
        };
    }
}
