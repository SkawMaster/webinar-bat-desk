# bat-desk

Este **proyecto modular** se corresponde con una **aplicación web** que dispone de los siguientes **módulos** :

| Módulo | Descripción |
| ------------- | ------------- |
| `bat-desk-common`  | Módulo de elementos comunes al resto de módulos de la aplicación |
| `bat-villain-core`  | Módulo encargado de la operativa de gestión de villanos|
| `bat-desk-web`  | Módulo encargado de todo lo relacionado con la aplicación web|

Este proyecto **requiere** para su funcionamiento el **uso de la librerías** de arquitectura proporcionadas por **Batman Inc.** (Batman Incorporated) a todo su **sistema de franquicias**

- **bat-architecture-testing**
- **bat-architecture-common**


## Módulo bat-desk-common

Esta módulo destaca por :
- Definir una excepción de negocio **BatDeskException** basada en **BatException**

Debería de ser el lugar donde **ubicar** aquellos **elementos** que deberían de ser **comunes** a los diferentes **módulos** de la aplicación como pueden ser constantes , clases de utilidades, validaciones comunes, etc.

**NO** incluye ninguna librería de terceros

## Módulo bat-villain-core

Esta módulo destaca por :
+ Definir toda la **operativa** relacionada con la **gestión de villanos** (Constantes, Entidades, Factorias, Validadores, Repositorios, Servicios, etc)
+ **Preparar** un **entorno de testing** operativo para desarrollo basado en la definición de un profile "development" con el que montar y configurar una base de datos en memoria H2 con la estructura y datos definidos previamente con Liquibase

_**Nota:** Al montar este entorno de testing , podremos ser capaces de probar muchas de las operativas de acceso a datos con datos útiles y fiables. Además podremos ser capaces de arrancar la aplicación totalmente desacoplada de algunos sistemas (como puede ser la base de datos, etc.) facilitando por tanto el desarrollo y las pruebas_

Las librerías de terceros que incluye son :

| Librería de Terceros | Versionado | Descripción |
| ---------- | -----| ------------------------- |
| `spring-boot-starter`         | 1.3.3.RELEASE | Librería inicializadora de Spring Boot |
| `spring-test`   |4.2.5.RELEASE | Testing de Spring |
| `spring-orm`   | 4.2.5.RELEASE | Mapeo Objeto Relacional de Spring (spring-jdbc, spring-tx, etc.) |
| `spring-aspects`   |4.2.5.RELEASE | Aspectos |
| `mybatis`   |3.3.0 | Motor de persistencia |
| `mybatis-spring`   |1.2.3 | Integración Mybatis con Spring|
| `liquibase`   |3.4.2 | Control de cambios en base de datos|
| `h2`   |1.4.191 | Base de datos relacional en memoria|

**Importante :** Se incorporan muchas más librerías debido a la transitividad de las dependencias

Este proyecto utiliza la librería **bat-architecture-testing** para poder realizar las pruebas 

## Módulo bat-desk-web

Esta módulo destaca por :
- Definir todos los **elementos** relacionados con la **aplicación web** (páginas, imágenes, css, javascript, etc.)
- Se encarga de la **integración** con el **resto de modulos**
- Generación del **"site"**
- Arranque y configuración del **servidor web embebido**
- Etc.

Las librerías de terceros que incluye son :

| Librería de Terceros | Versionado | Descripción |
| ---------- | -----| ------------------------- |
| `spring-boot-starter-web`         | 1.3.3.RELEASE  | Librería inicializadora de Spring Boot en lo relacionado con aplicaciones web |
| `spring-boot-starter-actuator`   | 1.3.3.RELEASE | Integración con actuaadores|
| `xml-apis`   |2.0.2 | Relacionada con el tratamiento de estructuras XML |
| `javax.servlet-api`   |3.1.0 | Integración con Servlets |
| `jsp-api`   |2.2 | Integración con JSPs |
| `jstl`   |1.2.3 | Integración con JSTL|
| `taglibs`   |3.4.2 | Integración con Custom Tags|
| `tomcat-embed-jasper`   |1.4.191 | Integración con un Tomcat Embebido|
| `selenium-java`   |2.53.0 | Integración con Selenium|
| `fluentlenium-assertj`   |0.13.0 | Integración con Selenium|
| `phantomjsdriver`   |1.2.0 |  Navegador por consola basado en WebKit|

**Nota:**: Se incorporan muchas más librerías debido a la transitividad de las dependencias

Este proyecto utiliza la librería **bat-architecture-testing** para poder realizar las pruebas 

## Empezando

Estas instrucciones facilitan el poder disponer de una copia de proyecto totalmente funcional en la máquina local para fines de desarrollo y pruebas. Consulta la información de despliegue para ver como "utilizarlo" :-)

### Pre requisitos

Se definen que elementos se necesitan para instalar el software

```
Tener instalado Java 8 (Se requiere versión 1.5+)
Tener instalado Maven (Se aconseja que sea 3+)
Tener instalado PhantomJS (Se ha probado con la version 2.1)
```

Se requiere configurar la siguiente propiedad del fichero **POM.xml ** "Padre" del proyecto (ubicado en el directorio raiz de bat-desk )
- Cambiar la ruta de instalación a la que cada uno tenga en su maquina local

```xml
<properties>
		...
		<phantomjs.path>E:\\software\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe</phantomjs.path>
		...
	</properties>
```

**Nota : Se requiere tener generado inicialmente los artefacto bat-architecture-testing y bat-architecture-common**

### Instalación

Pasos a seguir para poder disponer de un entorno de desarrollo

```
Arrancar la consola
Situarse en el PATH de instalación (el lugar donde se encuentra el proyecto )
- En este caso sería :  bat-desk>
Verificar que se encuentra disponible el fichero "pom.xml"
Ejecutar el comando : mvn clean install
```
**Nota :** Con esta ejecución se lanzarán TODOS los test de cada uno de los módulos (unitarios / integración / funcionales)

El resultado será la generación de un artefacto en tu repositorio maven

## Ejecución de Test

Este proyecto dispone de diferentes tipos de test según el módulo 

### Módulo bat-desk-common

| Test | Descripción |
| ------------- | ------------- |
| `BatDeskExceptionTest`  | Prueba la generación de la excepción BatDeskException|

### Módulo bat-villain-core

| Test | Descripción |
| ------------- | ------------- |
| `VillainConstantTest`  | Prueba el acceso a las constantes relacionadas con los villanos|
| `VillainTest`  | Prueba el uso de la entidad que representa a los villanos y sus posibles operaciones de : equals(), hashCode() y toString() |
| `VillainStatusEnumTest`  | Prueba el acceso a los valores de un tipo enumerado VillainStatusEnum con los estados de los villanos|
| `VillainTypeExceptionEnumTest`  | Prueba el acceso a los valores de un tipo enumerado VillainTypeExceptionEnum con la tipología de excepciones que se puede producir al tratar villanos|
| `VillainDataFactoryTest`  | Prueba la validez de los objetos creados por la factoría VillainDataFactory|
| `VillainMapperTest`  | Prueba El acceso a datos mediante el uso de un mapper de Mybatis (requiere integración con spring para hacer uso de H2 y Liquibase)|
| `VillainServiceTest`  | Prueba la capa de servicios relacionada con el CRUD de la entidad Villanos (Incluye mocking con el acceso a datos)|
| `VillainOperationServiceTest`  | Prueba la capa de servicios operativos con los villanos que NO son operaciones de CRUD (Incluye mocking con el acceso a datos)|
| `VillainValidationTest`  | Prueba las validaciones comunes a la entidad villanos|

### Módulo bat-desk-web

| Test | Descripción |
| ------------- | ------------- |
| `WebConfigTest`  | Prueba la carga de configuración por clase que se hace de ciertos elementos de Spring como pueden ser : ViewResolver, MessageSource, etc  |
| `ParameterConstantTest`  | Prueba el acceso a constantes que representan los elementos que se intercambian controlador y página |
| `ViewConstantTest`  | Prueba el acceso a constantes que representan los nombres de la páginas de la aplicación |
| `WebConfigConstantTest`  | Prueba el acceso a constantes que representan a elementos que se utilizan en la configuración por clase |
| `NavigationControllerTest`  | Prueba el controlador encargado de redirigir a cada una de las paginas |
| `VillainControllerTest`  | Prueba el controlador encargado de controlar toda la operativa de gestión de villanos en lo relacionado a : funciones a realizar, página a la que ir, tratamiento de error, etc. |
| `HomeSeleniumIT`  | Prueba funcional con selenium que acede a la home de la aplicación -> Ejemplo sencillo de funcionamiento de Selenium  |
| `DetainSeleniumIT`  | Prueba funcional con selenium que se encarga de probar el servicio de detención pero a traves de la web |

_**Nota :** Los test que incluyen el sufijo "Test" se corresponen con test unitarios / integración en cambio los test que incluyen el sufijo "IT" se corresponden con test funcionales_

_**Nota 2:** Los test funcionales "apuntan" a elementos especificos de la web (como pueden ser IDs de elementos) además estan asociados a datos precargados en la base de datos H2 con lo que dichos datos de prueba tienen aplicados cierta lógica de negocio (como es el disponer de unos estados específicos). Por lo tanto : Si se cambia algo de los datos o bien se cambia algo de la estructura de la página puede que el test no funcione_

### Perfiles de ejecución de test

En el fichero **POM.xml "Padre"** se definen los perfiles de ejecución :

```xml
<properties>
		..
		<skip.integration.tests>true</skip.integration.tests>
		<skip.unit.tests>false</skip.unit.tests>
		...
	</properties>
	
	<profiles>
        <profile>
            <id>all-tests</id>
            <properties>
                <build.profile.id>all-tests</build.profile.id>
                <skip.integration.tests>false</skip.integration.tests>
                <skip.unit.tests>false</skip.unit.tests>
            </properties>
        </profile>
        <profile>
            <id>integration-tests</id>
            <properties>
                <build.profile.id>integration-test</build.profile.id>
                <skip.integration.tests>false</skip.integration.tests>
                <skip.unit.tests>true</skip.unit.tests>
            </properties>
        </profile>
    </profiles>
	...
	<!-- Plugin used to build lifecycle to execute unit test (test phase) -->
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-surefire-plugin</artifactId>
					<configuration>
						<skipTests>${skip.unit.tests}</skipTests>
					</configuration>
			<executions>
				<execution>
							<id>it-test</id>
							<phase>integration-test</phase>
							<goals>
								<goal>test</goal>
							</goals>
							<configuration>
								<includes>
									<include>**/*IT.class</include>
								</includes>
								<argLine>-Dphantomjs.binary.path=${phantomjs.path}</argLine>
								<skipTests>${skip.integration.tests}</skipTests>
							</configuration>
				</execution>
			</executions>
		</plugin>	
```

Se han generado 2 perfiles de ejecución de los test :
- **all-test :** Perfil que únicamente ejecuta los test considerados como unitarios / integración (No incluye los test considerados funcionales)
- **integration-tests :**  Perfil que únicamente ejecuta los test considerados funcionales (No incluye los test considerados como unitarios / integración)


## Despliegue

### Arrancar la aplicación

Para poder desplegar la aplicación hay que hacer las siguientes tareas :
```
Arrancar la consola
Situarse en el PATH de instalación (el lugar donde se encuentra el proyecto )
- En este caso sería :  bat-desk-web>
Verificar que se encuentra disponible el fichero "pom.xml"
Ejecutar el comando : mvn spring-boot:run
```
**Ejemplo ejecución :** bat-desk> bat-desk-web> mvn spring-boot:run

Una vez finalizado, se debería de haber desplegado la aplicación y debería de poder ser accesible desde la URL de un navegador

**URL :** http://localhost:8080/bat-desk

De esta forma la aplicación debería de poder estar disponible para su uso

### Reporting de Cobertura

Para poder generar el reporting de cobertura en "site" hay que hacer las siguientes tareas :
```
Arrancar la consola
Situarse en el PATH de instalación (el lugar donde se encuentra el proyecto )
- En este caso sería :  bat-desk>
Verificar que se encuentra disponible el fichero "pom.xml"
Ejecutar el comando :  mvn clean site -Pintegration-tests
```
**Ejemplo ejecución :** bat-desk> mvn clean site -Pintegration-tests

En este caso, se generar un directorio target con el que se podrá consultar el informe mediante una página en HTML.

Lo más importante de este caso es que se mostrára TODO el código accedido por los test funcionales de la aplicación

## Construir con

* [Maven](https://maven.apache.org/) - Gestión de dependencias
* [PhantomJS](http://phantomjs.org/) - Navegador sin interfaz gráfica basado en WebKit


## Versionado

**Nota :** Se utiliza [SemVer](http://semver.org/) para el versionado. 
Para ver las versiones disponibles acceder a los tags del repositorio

## Autores

* **Víctor Madrid** - *Arranque Inicial* 

## Agradecimientos

* [atSistemas](http://atsistemas.com/)
* A mis compañeros
* A todos los seguidores de batman (que somos unos cuantos ;-) )

