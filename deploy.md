## [Come automatizzare il deploy su tomcat](https://mkyong.com/maven/how-to-deploy-maven-based-war-file-to-Tomcat/)

**Tomcat Authentication**

Add an user with roles `manager-gui` and `manager-script`.

```markup
<tomcat-users>

	<role rolename="manager-gui"/>
	<role rolename="manager-script"/>
	<user username="admin" password="password" roles="manager-gui,manager-script" />

</tomcat-users>
```

###### Maven download

[apache-maven-3.8.5-bin.zip](https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.zip)

###### Maven installation

Extract distribution archive in any directory

Add the `bin` directory of the created directory `apache-maven-3.8.5` to the `PATH` environment variable

Create a %MAVEN_PATH% environment variable pointing to the `bin` directory of the created directory `apache-maven-3.8.5`

Confirm with `mvn -v` in a new shell. The result should look similar to

```
Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)Maven home: /opt/apache-maven-3.8.5Java version: 1.8.0_45, vendor: Oracle CorporationJava home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jreDefault locale: en_US, platform encoding: UTF-8OS name: "mac os x", version: "10.8.5", arch: "x86_64", family: "mac"
```

**Maven Authentication**

Add above Tomcat’s user in the Maven setting file, later Maven will use this user to login Tomcat server

https://maven.apache.org/configure.html

*%MAVEN_PATH%/conf/settings.xml*

```bash
<?xml version="1.0" encoding="UTF-8"?>
<settings ...>
	<servers>
	   
		<server>
			<id>TomcatServer</id>
			<username>admin</username>
			<password>password</password>
		</server>

	</servers>
</settings>
```

**Tomcat7 Maven Plugin**

Declares a Maven Tomcat plugin.

*pom.xml*

```markup
	<plugin>
		<groupId>org.apache.tomcat.maven</groupId>
		<artifactId>tomcat7-maven-plugin</artifactId>
		<version>2.2</version>
		<configuration>
			<url>http://localhost:8080/manager/text</url>
			<server>TomcatServer</server>
			<path>/test</path>
		</configuration>
	</plugin>
```

Come funziona?
Durante il deploy, dice a Maven di distribuire il file WAR sul server Tomcat tramite "http://localhost:8080/manager/text", nel percorso "/test", utilizzando nome utente e password "TomcatServer" (in settings.xml) per autenticazione.

**Deploy to Tomcat**
Comandi per manipolare il file WAR su Tomcat

```bash
mvn tomcat7:deploy 
mvn tomcat7:undeploy 
mvn tomcat7:redeploy 
```

## Eclipse Run Configuration for Maven Goals

We can configure maven goals for our project. 

 1.  Click con tasto destro su pom.xml and poi Run Configuration.

 2.  Click su Maven build nel pannello sulla sinistra e vedrete la seguente schermata. 

 3.  Configure Name, Base directory, Goals etc. And then screen will look like 

    ![](https://www.concretepage.com/build-tools/maven/images/eclipse-run-configuration-for-maven-goals-1.jpg)



![](https://www.concretepage.com/build-tools/maven/images/eclipse-run-configuration-for-maven-goals-2.jpg)