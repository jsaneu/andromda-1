package org.andromda.cartridges.testsuite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is the main class of the cartridge test suite for AndroMDA. The
 * test checks for a list of expected files that a file with the same name and
 * the same package was generated by AndroMDA and that the APIs of the expected
 * file and the generated file are equal. <code>CartridgeTest</code> acts as
 * the test director which creates the list of files to be compared. The actual
 * API comparison is carried out by instances of {@link JavaSourceComparator}.
 * 
 * @author Ralf Wirdemann
 * @author Chad Brandon
 */
public class CartridgeTest
    extends TestCase
{

    /**
     * Points to the directory were the expected files are stored which will be
     * compared to the generated ones.
     */
    public static final String EXPECTED_DIRECTORY = "expected.dir";

    /**
     * Points to the directory were the generated files are located.
     */
    public static final String OUTPUT_DIRECTORY = "output.dir";

    private static File expectedDir = getDirectory(EXPECTED_DIRECTORY);
    private static File outputDir = getDirectory(OUTPUT_DIRECTORY);

    public CartridgeTest(
        String name)
    {
        super(name);
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        // Add the tests which compares all existing expected files against the
        // generated ones. This makes sure that for each expected an appropriate
        // files was generated.
        int noOfTests = addTests(getDirectory(EXPECTED_DIRECTORY), suite);

        List expectedFiles = new ArrayList();
        createListOfExpectedFiles(outputDir, expectedFiles);

        if (noOfTests < expectedFiles.size())
        {
            // The generator has generated more files than expected do exist.
            // In order to find out which one was additionally generated
            // compare each generated file to the appropriate complement in the
            // expected directory. The testcase will report if the expected
            // doesn't exist
            addTests(outputDir, suite);
        }

        return suite;
    }

    private static int addTests(File dir, TestSuite suite)
    {
        int noOfAddedTests = 0;
        List expectedFiles = new ArrayList();
        createListOfExpectedFiles(dir, expectedFiles);
        Iterator iterator = expectedFiles.iterator();
        while (iterator.hasNext())
        {
            File expectedFile = (File)iterator.next();
            File actualFile = getComplementFile(expectedFile);
            if (expectedFile.getName().endsWith(".java"))
            {
                suite.addTest(new JavaSourceComparator(
                    "testAPIEquals",
                    expectedFile,
                    actualFile));
            }
            else if (expectedFile.getName().endsWith(".xml"))
            {
                suite.addTest(new XMLComparator(
                    "testXMLEquals",
                    expectedFile,
                    actualFile));
            }
            noOfAddedTests++;
        }
        return noOfAddedTests;
    }

    private static File getComplementFile(File file)
    {
        String complement;
        String path = file.getPath();

        if (file.getPath().startsWith(expectedDir.getPath()))
        {
            complement = path.substring(expectedDir.getPath().length(), path
                .length());
            complement = outputDir + complement;
        }
        else
        {
            complement = path.substring(outputDir.getPath().length(), path
                .length());
            complement = expectedDir + complement;
        }
        return new File(complement);
    }

    private static String getDirectoryName(String propertyKey)
    {
        String dirName = System.getProperty(propertyKey);
        if (dirName == null)
        {
            throw new RuntimeException("system property <" + propertyKey
                + "> not set");
        }

        // Replace the path-separator character in the given directory name
        // by the path-separator character used by the actual system
        char ch = dirName.indexOf('\\') != -1 ? '\\' : '/';
        dirName = dirName.replace(ch, File.separatorChar);
        return dirName;
    }

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

    private static void createListOfExpectedFiles(File dir, List fileList)
    {
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File file = files[i];
            if (!file.isDirectory())
            {
                if (file.getName().endsWith(".java")
                    || file.getName().endsWith(".xml"))
                {
                    fileList.add(file);
                }
            }
            else
            {
                createListOfExpectedFiles(file, fileList);
            }
        }
    }
}