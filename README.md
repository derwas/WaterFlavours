# Water Flavours Application

This application allows to retrieve metaphors, footprints and costs of water to give other applications other flavours of water quantities!

![WaterFlavours](https://gitlab.insight-centre.org/uploads/waternomics/waterflavours/94af88d1ad/WaterFlavours.png)


# Requirements
These are the following requirements:
* Play Framework 2.5.*
* Play requires Java 1.8.
To check that you have the latest JDK, please run:

>$java -version

To check the installed java versions on the machine, please run:

>$ls /usr/lib/jvm/

To change the active java version, please run:

>$export JAVA_HOME=/usr/lib/jvm/[your java version]/

>$export PATH=${JAVA_HOME}/bin:${PATH}


# Start and Stop the application
### Start
The application is play framework app to start it use the following command:

>$cd [location of the app]

>$./target/universal/stage/bin/water-flavours -Dplay.evolutions.db.default.autoApply=true -Dhttp.port=8012 -Dplay.crypto.secret=waternomics &> logger.log &

### Stop
The application runs in the background using port 8012.
Stopping the application can be done by killing the process id using that port.

To stop this application use the following command:

>$sudo netstat -tunlp | grep 8012

>$sudo kill -9 [Process ID]

### Compile

>$./activator clean

>$./activator compile

>$./activator stage
