Sonar CAS Plgin with LDAP user and group import

1. INTRO
=============================

The previous Sonar CAS plugin is deprecated since SonarQube 3.3: http://docs.codehaus.org/display/SONAR/CAS+Plugin

This Sonar CAS Plgin works with Sonar 3.7+ and 4.0+ with the LDAP user and group import support. It does the following things:
	1.1. Authenticate the user with CAS.
	1.2. Import the user from ldap with user name, email and ldap group mapping.


2. HOW TO INSTALL CAS SERVER
=============================
* Download sources from http://www.jasig.org/cas
* Build with mvn install -DskipTests
* Deploy cas-server-webapp/target/cas.war to web server
* Any user with login equal to password is authenticated, for example login 'simon' and password 'simon'


3. HOW TO INSTALL PLUGIN
=============================
* Copy the plugin
* Add the following properties to conf/sonar.properties then restart the server


4. PLUGIN PROPERTIES
=============================
Set the following properties in SONARQUBE_HOME/conf/sonar.properties file:

#---------------------------------------------------------
# CAS
#---------------------------------------------------------

# this property must be set to true
sonar.authenticator.createUsers=true

# enable CAS plugin
sonar.security.realm=cas

# cas1, cas2 or saml11
sonar.cas.protocol=cas2

# Location of the CAS server login form, i.e. https://localhost:8443/cas/login
sonar.cas.casServerLoginUrl=http://localhost:8080/cas/login

# CAS server root URL, i.e. https://localhost:8443/cas
sonar.cas.casServerUrlPrefix=http://localhost:8080/cas

# Sonar server root URL, without ending slash
sonar.cas.sonarServerUrl=http://localhost:9000

# Optional CAS server logout URL. If set, sonar session will be deleted on CAS logout request.
#sonar.cas.casServerLogoutUrl=http://localhost:8080/cas/logout

# Specifies whether gateway=true should be sent to the CAS server. Default is false.
#sonar.cas.sendGateway=false

# The tolerance in milliseconds for drifting clocks when validating SAML 1.1 tickets.
# Note that 10 seconds should be more than enough for most environments that have NTP time synchronization.
# Default is 1000 milliseconds.
#sonar.cas.saml11.toleranceMilliseconds=1000

#---------------------------------------------------------
# LDAP
#---------------------------------------------------------
sonar.security.savePassword=true
ldap.url=ldap://myserver.mycompany.com
 
# User Configuration
ldap.user.baseDn=ou=Users,dc=mycompany,dc=com
ldap.user.request=(&(objectClass=inetOrgPerson)(uid={login}))
ldap.user.realNameAttribute=cn
ldap.user.emailAttribute=mail
 
# Group Configuration
ldap.group.baseDn=ou=Groups,dc=sonarsource,dc=com
ldap.group.request=(&(objectClass=posixGroup)(memberUid={uid}))


5. ADDITIONAL NOTES
=============================
	5.1. Please add the sonar-administrators and sonar-users groups in your ldap and assign the users to those groups accordingly.

	5.2. For more ldap properties settings please refer to http://docs.codehaus.org/display/SONAR/ldap+Plugin