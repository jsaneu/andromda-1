package org.andromda.cartridges.testsuite;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.andromda.core.common.AndroMDALogger;
import org.apache.log4j.Logger;

/**
 * <p>
 * This class is the main class of the cartridge test suite for AndroMDA. The
 * test checks for a list of expected files that a file with the same name and
 * the same package was generated by AndroMDA and that the APIs of the expected
 * file and the generated file are equal. <code>CartridgeTest</code> acts as
 * the test director which creates the list of files to be compared. The actual
 * API comparison is carried out by instances of {@link JavaSourceComparator}.
 * </p>
 * 
 * @author Ralf Wirdemann
 * @author Chad Brandon
 */
public class CartridgeTest
    extends TestCase
{

    private static final Logger logger = Logger.getLogger(CartridgeTest.class);

    /**
     * Points to the directory were the expected files are stored which will be
     * compared to the generated ones.
     */
    private static final String EXPECTED_DIRECTORY = "expected.dir";

    /**
     * Points to the directory were the generated files are located.
     */
    private static final String ACTUAL_DIRECTORY = "actual.dir";

    static
    {
        AndroMDALogger.configure();
    }

    private static File expectedDir = getDirectory(EXPECTED_DIRECTORY);
    private static File actualDir = getDirectory(ACTUAL_DIRECTORY);
    private static Collection binarySuffixes = getBinarySuffixes();

    public CartridgeTest(
        String name)
    {
        super(name);
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        addTests(suite);
        return suite;
    }

    /**
     * Adds tests which compare all actual generated files against the expected
     * files.
     * 
     * @param suite the test suite to which we'll add the tests.
     */
    private static void addTests(TestSuite suite)
    {
        List expectedFiles = new ArrayList();
        getAllFiles(expectedDir, expectedFiles);
        Iterator iterator = expectedFiles.iterator();
        logger.info(" --- Expecting " + expectedFiles.size()
            + " Generated Files --- ");
        logger.info("binary suffixes --> " + binarySuffixes);
        final List missingFiles = new ArrayList();
        for (int ctr = 1; iterator.hasNext(); ctr++)
        {
            File expectedFile = (File)iterator.next();
            File actualFile = getActualFile(expectedFile);
            if (!actualFile.exists())
            {
                missingFiles.add(actualFile);
            }
            boolean binary = isBinary(actualFile);
            StringBuffer header = new StringBuffer(ctr + ") binary = " + binary);
            logger.debug(header);
            logger.debug("expected --> '" + expectedFile + "'");
            logger.debug("actual   --> '" + actualFile + "'");
            suite.addTest(new FileComparator(
                "testEquals",
                expectedFile,
                actualFile,
                binary));
        }
        if (!missingFiles.isEmpty())
        {
            Collections.sort(missingFiles);
            StringBuffer failureMessage = new StringBuffer(
                "\n--- The following ");
            failureMessage.append(missingFiles.size());
            failureMessage.append(" expected files do not exist ----\n");
            Iterator missingFileIterator = missingFiles.iterator();
            for (int ctr = 1; missingFileIterator.hasNext(); ctr++)
            {
                failureMessage.append(ctr);
                failureMessage.append(")");
                failureMessage.append(" ");
                failureMessage.append(missingFileIterator.next());
                if (missingFileIterator.hasNext())
                {
                    failureMessage.append("\n");
                }
            }
            TestCase.fail(failureMessage.toString());
        }
    }

    /**
     * Contructs the expected file path from the <code>actualFile</code> and
     * the <code>expectedDir</code> path.
     * 
     * @param actualFile the actual generated file
     * @return the new expected file.
     */
    private static File getActualFile(File expectedFile)
    {
        String actualFile;
        String path = expectedFile.getPath();

        if (expectedFile.getPath().startsWith(actualDir.getPath()))
        {
            actualFile = path.substring(actualDir.getPath().length(), path
                .length());
            actualFile = expectedDir + expectedFile.toString();
        }
        else
        {
            actualFile = path.substring(expectedDir.getPath().length(), path
                .length());
            actualFile = actualDir + actualFile;
        }
        return new File(actualFile);
    }

    /**
     * Just gets the system property having the given <code>propertyKey</code>,
     * throwing a run time exception if none exists.
     * 
     * @param propertyKey the property key name.
     * @return the value of the system property
     */
    private static String getSystemProperty(String propertyKey)
    {
        String value = System.getProperty(propertyKey);
        if (value == null)
        {
            throw new RuntimeException("system property <" + propertyKey
                + "> not set");
        }
        return value;
    }

    private static String getDirectoryName(String propertyKey)
    {
        String dirName = getSystemProperty(propertyKey);

        // Replace the path-separator character in the given directory name
        // by the path-separator character used by the actual system
        char ch = dirName.indexOf('\\') != -1 ? '\\' : '/';
        dirName = dirName.replace(ch, File.separatorChar);
        return dirName;
    }

    /**
     * Gets the directory from the system property key.
     * 
     * @param propertyKey the system property key name.
     * @return the directory as a File instance.
     */
    private static File getDirectory(String propertyKey)
    {
        String dirName = getDirectoryName(propertyKey);
        File dir = new File(dirName);
        if (!dir.exists() || !dir.isDirectory())
        {
            throw new RuntimeException("directory <" + dirName
                + "> doesn't exist");
        }
        return dir;
    }

    /**
     * Checks whether or not the <code>file</code> is a binary file. Does this
     * by checking to see if the suffix is found in the list of binary suffixes.
     * 
     * @param file the file to check
     * @return true/false
     */
    private static boolean isBinary(File file)
    {
        String suffix = "";
        String fileName = file.getName();
        int dotIndex = fileName.indexOf('.');
        if (dotIndex != -1)
        {
            suffix = fileName.substring(dotIndex + 1, fileName.length());
        }
        return binarySuffixes.contains(suffix);
    }

    /**
     * Gets the binary suffixes for the <code>binary.suffixes</code> system
     * property. Returns an empty collection if none are found.
     * 
     * @return the Collection of binary suffixes. (ie. jpg, jar, zip, etc).
     */
    private static Collection getBinarySuffixes()
    {
        String suffixes = getSystemProperty("binary.suffixes");
        String[] suffixArray = suffixes.split("\\s*,\\s*");
        binarySuffixes = Arrays.asList(suffixArray);
        return binarySuffixes;
    }

    /**
     * Loads all files find in the <code>directory</code> and adds them to the
     * <code>fileList</code>.
     * 
     * @param directory the directory from which to load all files.
     * @param fileList the List of files to which we'll add the found files.
     */
    private static void getAllFiles(File directory, List fileList)
    {
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File file = files[i];
            if (!file.isDirectory())
            {
                fileList.add(file);
            }
            else
            {
                getAllFiles(file, fileList);
            }
        }
    }
}