package org.seasar.teeda.core.unit;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import junit.extensions.TestSetup;
import junit.framework.Test;

import org.apache.maven.BuildFailureException;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.cli.ConsoleDownloadMonitor;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.embedder.MavenEmbedderConsoleLogger;
import org.apache.maven.embedder.MavenEmbedderException;
import org.apache.maven.embedder.MavenEmbedderLogger;
import org.apache.maven.embedder.PlexusLoggerAdapter;
import org.apache.maven.lifecycle.LifecycleExecutionException;
import org.apache.maven.monitor.event.DefaultEventMonitor;
import org.apache.maven.monitor.event.EventMonitor;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingException;
import org.codehaus.plexus.util.dag.CycleDetectedException;

public class WebApplicationTestSetup extends TestSetup {

    private File pomFile_;

    public WebApplicationTestSetup(Test test) {
        super(test);
    }

    protected void setUp() throws Exception {
        super.setUp();
        buildWebapp();
    }

    protected void buildWebapp() throws MavenEmbedderException,
            ProjectBuildingException, ArtifactResolutionException,
            ArtifactNotFoundException, CycleDetectedException,
            LifecycleExecutionException, BuildFailureException {

        MavenEmbedder maven = new MavenEmbedder();
        maven.setClassLoader(Thread.currentThread().getContextClassLoader());
        MavenEmbedderLogger mavenLogger = new MavenEmbedderConsoleLogger();
        mavenLogger.setThreshold(MavenEmbedderLogger.LEVEL_ERROR);
        maven.setLogger(mavenLogger);
        maven.start();

        final File pomFile = getProjectPomFile();
        System.out.println("pomFile:" + pomFile);
        final File projectDirectory = pomFile.getParentFile();

        MavenProject mavenProject = maven.readProjectWithDependencies(pomFile);
        EventMonitor eventMonitor = new DefaultEventMonitor(
                new PlexusLoggerAdapter(mavenLogger));

        Properties prop = new Properties();
        prop.put("maven.test.skip", "true");

        maven.execute(mavenProject, Arrays.asList(new String[] {
                "resources:resources", "compiler:compile",
                "resources:testResources", "compiler:testCompile",
                "war:exploded" }), eventMonitor, new ConsoleDownloadMonitor(),
                prop, projectDirectory);
        maven.stop();
    }

    private File getProjectPomFile() {
        if (pomFile_ == null) {
            throw new NullPointerException("pomFile is null");
        }
        return pomFile_;
    }

    public void setPomFile(File pomFile) {
        pomFile_ = pomFile;
    }

}
