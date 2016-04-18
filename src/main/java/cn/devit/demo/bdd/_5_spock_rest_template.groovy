package cn.devit.demo.bdd;

import static org.junit.Assert.*
import static groovy.test.GroovyAssert.*
import groovy.json.JsonSlurper

import org.simmetrics.StringMetric
import org.simmetrics.metrics.StringMetrics
import org.springframework.boot.test.TestRestTemplate
import org.springframework.boot.test.TestRestTemplate.HttpClientOption
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.util.LinkedMultiValueMap

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
public class _5_spock_rest_template extends Specification {

    static TestRestTemplate rest = new TestRestTemplate(HttpClientOption.ENABLE_COOKIES,HttpClientOption.ENABLE_REDIRECTS);

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

    def "打开首页" () {

        when:'访问首页'
        ResponseEntity<String> response = rest.getForEntity("http://localhost:8080/", String);

        then:'返回HTML'

        response.statusCode == HttpStatus.OK
        //        response.getBody() == body spock while show 2 differences 99% similarity
        metric.compare(body, response.getBody()) >0.9
    }

    def cookie ;

    def 登录 () {
        when:"输入密码"
        println rest
        rest.messageConverters.add(new FormHttpMessageConverter());
        def map = new LinkedMultiValueMap();
        map.putAll([password:['admin'],username:['admin@admin.com']]);
        def response = rest.postForEntity(
                "http://localhost:8080/login",
                map,
                String);
        response.getHeaders().getFirst("Set-Cookie:");

        then:'登录成功'

        response.statusCode.value == 200;
    }

    def 模拟系统列表(){
        when:'登陆后，访问mocker'
        println rest
        def response = rest.getForEntity("http://localhost:8080/mockers", String)
        def json = new JsonSlurper().parseText(response.body);

        then:'JSON'
        response.statusCode.value == 200;
        json.size()>0
        json.each {
            it.id instanceof Number
            it.owner instanceof String
            it.name instanceof String
            it.desc instanceof String
            it.created instanceof Long
            it.updated instanceof Long
            it.ruleCount instanceof Integer
            it.type == 'Private'

        }
    }
    //[{"id":1,"owner":"admin@admin.com","name":"谷歌","desc":"谷歌首页","created":1455467364097,"updated":1456126538329,
    //"ruleCount":3,"type":"Private","watcherCount":0,"host":"www.google.cn"}]
}
