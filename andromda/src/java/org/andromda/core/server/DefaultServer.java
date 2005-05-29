package org.andromda.core.server;

import org.andromda.core.configuration.Configuration;
import org.andromda.core.engine.Engine;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


/**
 * The default AndroMDA {@link Server instance}.
 *
 * @author Chad Brandon
 */
public class DefaultServer
    implements Server
{
    /**
     * The logger instance.
     */
    private static final Logger logger = Logger.getLogger(DefaultServer.class);

    /**
     * The message sent to the client when AndroMDA processing has completed.
     */
    private static final String COMPLETE = "complete";

    /**
     * The server listener.
     */
    private ServerSocket listener = null;

    /**
     * The AndroMDA Engine instance.
     */
    private Engine engine = Engine.newInstance();

    /**
     * @see org.andromda.core.server.Server#start(org.andromda.core.configuration.Server)
     */
    public void start(final Configuration configuration)
    {
        engine.initialize();
        if (configuration != null)
        {
            final org.andromda.core.configuration.Server serverConfiguration = configuration.getServer();
            if (serverConfiguration != null)
            {
                try
                {
                    try
                    {
                        this.listener = new ServerSocket(serverConfiguration.getPort());
                        this.listener.setSoTimeout(serverConfiguration.getModelLoadInterval());
                    }
                    catch (final IOException exception)
                    {
                        throw new ServerException(
                            "Could not listen on port '" + serverConfiguration.getPort() +
                            "', change the port in your configuration");
                    }
                    while (true)
                    {
                        try
                        {
                            final Socket client = this.listener.accept();
                            if (client != null)
                            {
                                //final PrintWriter serverOutput = new PrintWriter(client.getOutputStream(), true);
                                final ObjectOutputStream serverOutput =
                                    new ObjectOutputStream(client.getOutputStream());
                                final ObjectInputStream objectInput =
                                    new ObjectInputStream(new DataInputStream(client.getInputStream()));
                                try
                                {
                                    this.engine.run((Configuration)objectInput.readObject());
                                }
                                catch (final Throwable throwable)
                                {
                                    logger.error(throwable);

                                    // pass the exception to the client
                                    serverOutput.writeObject(throwable);
                                }

                                // signal to the client, it can stop waiting
                                serverOutput.writeObject(COMPLETE);
                                serverOutput.flush();
                                serverOutput.close();
                                objectInput.close();
                                client.close();
                            }
                        }
                        catch (final SocketTimeoutException exception)
                        {
                            this.engine.loadModelsIfNecessary(configuration);
                        }
                    }
                }
                catch (final Throwable throwable)
                {
                    throw new ServerException(throwable);
                }
            }
        }
    }

    /**
     * @see org.andromda.core.server.Server#shutdown()
     */
    public void shutdown()
    {
        try
        {
            this.listener.close();
        }
        catch (final IOException exception)
        {
            // ignore exception
        }
        this.listener = null;
        this.engine.shutdown();
    }
}