// See https://github.com/batmat/jez/tree/master/jenkins-master/init_scripts
// Inspired by https://wiki.jenkins-ci.org/display/JENKINS/Add+a+Maven+Installation%2C+Tool+Installation%2C+Modify+System+Config
import jenkins.model.*
import hudson.tools.*
import static hudson.tasks.Maven.*

println "Adding an auto installer for Maven 3.3.9"

def mavenPluginExtension = Jenkins.instance.getExtensionList(DescriptorImpl.class)[0]

def asList = (mavenPluginExtension.installations as List)
asList.add(new MavenInstallation('Maven 3.x', null, [new InstallSourceProperty([new MavenInstaller("3.3.9")])]))

mavenPluginExtension.installations = asList

mavenPluginExtension.save()

println "OK - Maven auto-installer (from Apache) added for 3.3.9"