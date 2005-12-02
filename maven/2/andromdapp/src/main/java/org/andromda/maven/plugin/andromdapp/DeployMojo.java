package org.andromda.maven.plugin.andromdapp;

import java.io.File;

import org.apache.maven.model.Build;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.FileUtils;


/**
 * Provides the deployment of applications to a given directory.
 *
 * @goal deploy
 * @phase install
 * @author Chad Brandon
 */
public class DeployMojo
    extends AppManagementMojo
{
    /**
     * Indicates whether or not this plugin should perform the deploy.
     *
     * @parameter expression="${deploy}"
     */
    private String deploy;

    /**
     * The string indicating whether or not the deploy should be exploded or not.
     */
    private static final String EXPLODED = "exploded";

    /**
     * @see org.apache.maven.plugin.AbstractMojo#execute()
     */
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        File artifactFile = this.project.getArtifact().getFile();

        // - if we're deploying within a phase then deploy has to be set, otherwise
        //   its not needed (we know we're not deploying in a phase when the artifactFile is null).
        if (this.deploy != null || artifactFile == null)
        {
            final Build build = this.project.getBuild();
            if (EXPLODED.equalsIgnoreCase(this.deploy))
            {
                artifactFile = new File(
                        build.getDirectory(),
                        build.getFinalName());
            }
            else if (artifactFile == null)
            {
                artifactFile = new File(
                        build.getDirectory(),
                        build.getFinalName() + '.' + this.getPackaging());
            }
            if (artifactFile.exists())
            {
                final File deployDirectory = new File(this.deployLocation);
                if (deployDirectory.exists() && deployDirectory.isDirectory())
                {
                    try
                    {
                        if (EXPLODED.equalsIgnoreCase(this.deploy))
                        {
                            final File destintation = this.getDeployFile();
                            this.getLog().info("Deploying exploded " + artifactFile + " to " + destintation);
                            FileUtils.copyDirectoryStructure(
                                artifactFile,
                                destintation);
                        }
                        else
                        {
                            this.getLog().info("Deploying " + artifactFile + " to " + deployDirectory);
                            FileUtils.copyFileToDirectory(
                                artifactFile,
                                deployDirectory);
                        }
                    }
                    catch (final Throwable throwable)
                    {
                        throw new MojoExecutionException("An error occurred while attempting to deploy artifact",
                            throwable);
                    }
                }
                else
                {
                    this.getLog().error(
                        "Deploy did not occur because the specified deployLocation '" + deployLocation +
                        "' does not exist, or is not a directory");
                }
            }
            else
            {
                this.getLog().warn(
                    "Deploy did not occur because file '" +  artifactFile + "' does not exist");
            }
        }
    }
}