/**
 * Created by Paul on 9/7/16.
 */

def copyFile = { src,dst ->
    new File(dst).bytes = new File(src).bytes
    println "copied $src to $dst"
}

def basePath = '/Users/Paul/Documents/Source/E1C'
def pluginsPath = "$basePath/plugins-paul"
def ecModulesPath = "$pluginsPath/ec-modules"
def adminPath = "$basePath/elect-admin-paul"
def tomcatPath = '/Library/Tomcat'
def webappsPath = "$tomcatPath/webapps"
def centralScanningPath = "$basePath/central-scanning-paul"
def asyncPath = "$ecModulesPath/async"

def webModules = ['admin-ws',
                  'auth-ws',
                  'bal-ws',
                  'bridge-ws',
                  'ballot-builder-ws',
                  'ballot-proofing-tools-ws',
                  'ballotbox-ws',
                  'elect-reporting-ws',
                  'storage-ws',
                  'voter-mgmt-ws']

// This will deploy admin to my tomcat webapps

// Plugins

webModules.each {
    copyFile("$ecModulesPath/webmodule/${it}/target/${it}-development-SNAPSHOT.war", "$webappsPath/${it}.war")
}

// Now admin

copyFile("$adminPath/target/elect-admin-development-SNAPSHOT.war", "$webappsPath/elect-admin.war")

// Now central scanning - badly named!

copyFile("$centralScanningPath/batch-manager/batch-manager-ws/target/batchmanager-ws-development-SNAPSHOT.war", "$webappsPath/batchmanager-ws.war")
copyFile("$centralScanningPath/batch-manager/batch-manager-proxy-ws/target/batch-manager-proxy-ws-development-SNAPSHOT.war", "$webappsPath/batch-manager-proxy-ws.war")

// Now do Async
// Todo..  I think we can just run from target directory as long as options and config are setup and libraries done