@echo off
SET scriptdir=%~dp0
SET classpath="%scriptdir%\lib\dom4j-1.6.1.jar;%scriptdir%\lib\mysql-connector-java-5.1.26-bin.jar;%scriptdir%\lib\ojdbc14.jar;%scriptdir%\lib\poi-3.10-FINAL-20140208.jar;%scriptdir%\lib\poi-ooxml-3.10-FINAL-20140208.jar;%scriptdir%\lib\poi-ooxml-schemas-3.10-FINAL-20140208.jar;%scriptdir%\lib\postgresql-9.4.1209.jre7.jar;%scriptdir%\lib\xmlbeans-2.3.0.jar;%scriptdir%\lib\org.scala-lang.scala-library-2.10.0.jar;%scriptdir%\lib\play.play_2.10-2.1.3.jar;%scriptdir%\lib\play.sbt-link-2.1.3.jar;%scriptdir%\lib\org.javassist.javassist-3.16.1-GA.jar;%scriptdir%\lib\play.play-exceptions-2.1.3.jar;%scriptdir%\lib\play.templates_2.10-2.1.3.jar;%scriptdir%\lib\com.github.scala-incubator.io.scala-io-file_2.10-0.4.2.jar;%scriptdir%\lib\com.github.scala-incubator.io.scala-io-core_2.10-0.4.2.jar;%scriptdir%\lib\com.jsuereth.scala-arm_2.10-1.3.jar;%scriptdir%\lib\play.play-iteratees_2.10-2.1.3.jar;%scriptdir%\lib\org.scala-stm.scala-stm_2.10.0-0.6.jar;%scriptdir%\lib\com.typesafe.config-1.0.0.jar;%scriptdir%\lib\io.netty.netty-3.6.3.Final.jar;%scriptdir%\lib\com.typesafe.netty.netty-http-pipelining-1.1.1.jar;%scriptdir%\lib\org.slf4j.jul-to-slf4j-1.6.6.jar;%scriptdir%\lib\org.slf4j.jcl-over-slf4j-1.6.6.jar;%scriptdir%\lib\ch.qos.logback.logback-core-1.0.7.jar;%scriptdir%\lib\ch.qos.logback.logback-classic-1.0.7.jar;%scriptdir%\lib\com.typesafe.akka.akka-actor_2.10-2.1.0.jar;%scriptdir%\lib\com.typesafe.akka.akka-slf4j_2.10-2.1.0.jar;%scriptdir%\lib\org.slf4j.slf4j-api-1.7.2.jar;%scriptdir%\lib\joda-time.joda-time-2.1.jar;%scriptdir%\lib\org.joda.joda-convert-1.2.jar;%scriptdir%\lib\org.apache.commons.commons-lang3-3.1.jar;%scriptdir%\lib\com.ning.async-http-client-1.7.6.jar;%scriptdir%\lib\oauth.signpost.signpost-core-1.2.1.2.jar;%scriptdir%\lib\commons-codec.commons-codec-1.3.jar;%scriptdir%\lib\oauth.signpost.signpost-commonshttp4-1.2.1.2.jar;%scriptdir%\lib\org.apache.httpcomponents.httpcore-4.0.1.jar;%scriptdir%\lib\org.apache.httpcomponents.httpclient-4.0.1.jar;%scriptdir%\lib\commons-logging.commons-logging-1.1.1.jar;%scriptdir%\lib\org.codehaus.jackson.jackson-core-asl-1.9.10.jar;%scriptdir%\lib\org.codehaus.jackson.jackson-mapper-asl-1.9.10.jar;%scriptdir%\lib\net.sf.ehcache.ehcache-core-2.6.0.jar;%scriptdir%\lib\javax.transaction.jta-1.1.jar;%scriptdir%\lib\org.scala-lang.scala-reflect-2.10.0.jar;%scriptdir%\lib\play.play-java_2.10-2.1.3.jar;%scriptdir%\lib\org.yaml.snakeyaml-1.10.jar;%scriptdir%\lib\org.hibernate.hibernate-validator-4.3.0.Final.jar;%scriptdir%\lib\javax.validation.validation-api-1.0.0.GA.jar;%scriptdir%\lib\org.jboss.logging.jboss-logging-3.1.0.CR2.jar;%scriptdir%\lib\org.springframework.spring-context-3.1.2.RELEASE.jar;%scriptdir%\lib\org.springframework.spring-core-3.1.2.RELEASE.jar;%scriptdir%\lib\org.springframework.spring-beans-3.1.2.RELEASE.jar;%scriptdir%\lib\org.reflections.reflections-0.9.8.jar;%scriptdir%\lib\com.google.guava.guava-13.0.1.jar;%scriptdir%\lib\com.google.code.findbugs.jsr305-2.0.1.jar;%scriptdir%\lib\javax.servlet.javax.servlet-api-3.0.1.jar;%scriptdir%\lib\play.play-java-jdbc_2.10-2.1.3.jar;%scriptdir%\lib\play.play-jdbc_2.10-2.1.3.jar;%scriptdir%\lib\com.jolbox.bonecp-0.7.1.RELEASE.jar;%scriptdir%\lib\com.h2database.h2-1.3.168.jar;%scriptdir%\lib\tyrex.tyrex-1.0.1.jar;%scriptdir%\lib\play.play-java-ebean_2.10-2.1.3.jar;%scriptdir%\lib\org.avaje.ebeanorm.avaje-ebeanorm-3.1.2.jar;%scriptdir%\lib\org.avaje.ebeanorm.avaje-ebeanorm-api-3.1.1.jar;%scriptdir%\lib\org.avaje.ebeanorm.avaje-ebeanorm-agent-3.2.2.jar;%scriptdir%\lib\org.avaje.ebeanorm.avaje-ebeanorm-server-3.1.2.jar;%scriptdir%\lib\org.hibernate.javax.persistence.hibernate-jpa-2.0-api-1.0.1.Final.jar;%scriptdir%\lib\inventory_2.10-1.0-SNAPSHOT.jar"
java %* -Dconfig.file=.\app.conf -Dhttp.port=9007 -cp %classpath% play.core.server.NettyServer
