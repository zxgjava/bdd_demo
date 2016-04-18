package cn.devit.demo.bdd

import groovyx.net.http.HttpResponseDecorator;

import static org.junit.Assert.*
import static groovy.test.GroovyAssert.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient

import org.simmetrics.StringMetric
import org.simmetrics.metrics.StringMetrics
import org.springframework.boot.test.TestRestTemplate
import org.springframework.boot.test.TestRestTemplate.HttpClientOption
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.util.LinkedMultiValueMap

import spock.lang.Shared;
import spock.lang.Specification
import spock.lang.Stepwise
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

@Stepwise
//保证按照声明顺序执行，前面的异常会终止后面的执行
public class _6_spock_rest_client extends Specification {

    //因为有Session cookie 所以 @shared 机制可以保证 里面的case共享这个对象。
    @Shared
    RESTClient rest = new RESTClient('http://localhost:8080');

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

    def "打开首页"() {

        when: '访问首页'
        HttpResponseDecorator response = rest.get(path: '/')

        then: '返回HTML'
        with(response) {
            status == 200
            data.HEAD.TITLE == '模客'
        }
    }

    def cookie;

    def 登录() {
        when: "输入密码"
        HttpResponseDecorator response = rest.post(
                path: '/login',
                requestContentType: URLENC,
                body: [password: 'admin', username: 'admin@admin.com']);

        then: '登录成功'
        response.status == 200;
        response.data == null;

    }

    def 模拟系统列表() {
        when: '登陆后，访问mocker'
        HttpResponseDecorator response = rest.get('path': '/mockers')

        then: 'JSON'
        response.status == 200;
        response.data.size() >0;
        response.data.each {it->
            it.id instanceof Number
            it.owner instanceof String
            it.name instanceof String
            it.desc instanceof String
            it.created instanceof Long
            it.updated instanceof Long
            it.ruleCount instanceof Integer
            it.type == 'Private'
        }
        //[{"id":1,"owner":"admin@admin.com","name":"谷歌","desc":"谷歌首页","created":1455467364097,"updated":1456126538329,
        //"ruleCount":3,"type":"Private","watcherCount":0,"host":"www.google.cn"}]
    }
}
