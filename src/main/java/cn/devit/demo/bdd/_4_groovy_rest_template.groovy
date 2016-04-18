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

public class _4_groovy_rest_template {

    RestTemplate rest = new RestTemplate();

    StringMetric metric = StringMetrics.mongeElkan();

    String body = '''
<!DOCTYPE html>
<html ng-app="module.main">
<head lang="zh-CN">
    <base href="/">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>模客</title>
    <link rel="stylesheet" type="text/css" href="css/app.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/angular-growl.min.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.qtip.min.css">
    <link rel="stylesheet" type="text/css" href="css/codemirror.css">
</head>

<body>
    <div ui-view></div>
    <div growl></div>
    <script src="js/lib.js"></script>
    <script src="js/app.js"></script>
</body>
</html>
'''

    @Test
    public void login_page() throws Exception {

        ResponseEntity<String> response = rest.getForEntity("http://localhost:8080/", String.class);

        assert response.statusCode == HttpStatus.OK
//        assert response.body == body
        assert metric.compare(body, response.getBody()) >0.9
    }
}
