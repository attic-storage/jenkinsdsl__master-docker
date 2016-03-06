import jenkins.model.*
import org.jenkinsci.main.modules.sshd.*

println "Set 16042 as a SSH fixed port"

def sshdExtension = Jenkins.instance.getExtensionList(SSHD.class)[0]

sshdExtension.setPort(16042)

sshdExtension.save()