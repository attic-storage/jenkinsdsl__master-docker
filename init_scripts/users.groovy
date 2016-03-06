// See https://github.com/batmat/jez/tree/master/jenkins-master/init_scripts
// Thanks https://gist.github.com/hayderimran7/50cb1244cc1e856873a4
import jenkins.model.*
import hudson.model.*
import hudson.security.*
import org.jenkinsci.main.modules.cli.auth.ssh.*

def instance = Jenkins.getInstance()

def adminUserName = System.getenv("ADMIN_USERNAME")
def adminPassword = System.getenv("ADMIN_PASSWORD")

assert adminUserName != null : "No ADMIN_USERNAME env var provided, but required"
assert adminPassword != null : "No ADMIN_PASSWORD env var provided, but required"

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
println "Creating the '$adminUserName' admin user with provided password (using env var 'ADMIN_PASSWORD')"

if(adminPassword.equals(System.getenv("DEFAULT_ADMIN_PASSWORD"))) {
    println("WARNING: You didn't change the default image password, there may be a security risk")
    println("WARNING: Pass the value using 'docker run -e ADMIN_PASSWORD=theOneYouWant ...'")
}
User admin = hudsonRealm.createAccount("admin", System.getenv("ADMIN_PASSWORD"))
String sshPublickey = new File('/var/jenkins_publickeys/id_rsa.pub').getText('UTF-8')
UserPropertyImpl sshPublickeyProperty = new UserPropertyImpl(sshPublickey)
admin.addProperty(sshPublickeyProperty)

instance.setSecurityRealm(hudsonRealm)

def strategy = new ProjectMatrixAuthorizationStrategy()
strategy.add(Jenkins.ADMINISTER, "admin")
strategy.add(Jenkins.READ, "anonymous")
instance.setAuthorizationStrategy(strategy)

instance.save()