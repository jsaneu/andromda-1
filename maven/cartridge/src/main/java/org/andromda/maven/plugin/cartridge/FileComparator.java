package org.andromda.maven.plugin.cartridge;

import java.io.File;
import java.util.List;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Compares two files. It checks if both file do exist and if the contents of
 * both files are equal.
 *
 * @author Chad Brandon
 * @author Bob Fields
 */
public class FileComparator
        extends TestCase
{
    private File expectedFile;
    private File actualFile;
    private boolean binary;
    private boolean ignoreWhitespace = false;
    private List<String> ignoreLinesWithStrings = null;

    /**
     * Constructs a new instance of the FileComparator.
     *
     * @param testName     the name of the test to run
     * @param expectedFile the location of the expected file
     * @param actualFile   the location of the actual file.
     * @param binary       whether or not the file is binary, if it is binary contents
     *                     of the binary are not compared as Strings but as binary files.
     */
    public FileComparator(
            String testName,
            File expectedFile,
            File actualFile,
            boolean binary)
    {
        super();
        this.setName(testName);
        this.expectedFile = expectedFile;
        this.actualFile = actualFile;
        this.binary = binary;
    }

    /**
     * Constructs a new instance of the FileComparator.
     *
     * @param testName the name of the test to run
     * @param expectedFile the location of the expected file
     * @param actualFile the location of the actual file.
     * @param binary whether or not the file is binary, if it is binary contents
     *        of the binary are not compared as Strings but as binary files.
     * @param ignoreWhitespace Ignore whitespace in the comparison
     * @param ignoreLinesWithStrings Ignore lines containing the strings in the comparison
     */
    public FileComparator(
        String testName,
        File expectedFile,
        File actualFile,
        boolean binary,
        boolean ignoreWhitespace,
        List<String> ignoreLinesWithStrings)
    {
        this(testName, expectedFile, actualFile, binary);
        this.ignoreWhitespace = ignoreWhitespace;
        this.ignoreLinesWithStrings = ignoreLinesWithStrings;
    }

    /**
     * Test if actual and expected file contents are equal.
     */
    public void testEquals()
    {
        assertTrue(
                "expected file <" + expectedFile.getPath() + "> doesn't exist",
                expectedFile.exists());
        assertTrue(
                "actual file <" + actualFile.getPath() + "> doesn't exist",
                actualFile.exists());
        this.testContentsEqual();
    }

    /**
     * Loads both the <code>actual</code> and <code>expected</code> files
     * and tests the contents for equality.
     */
    protected void testContentsEqual()
    {
        try
        {
            String message = "actual file <" + actualFile + "> does not match\n";
            if (this.binary)
            {
                assertTrue(
                        message,
                        FileUtils.contentEquals(
                                expectedFile,
                                actualFile));
            }
            else
            {
                String actualContents = FileUtils.readFileToString(actualFile);
                String expectedContents = FileUtils.readFileToString(expectedFile);
                //Ignore 'generated on ' date comments different between expected/actual
                // Sometimes it says 'Autogenerated on 04/22/2010 14:49:09-0400 by AndroMDA', sometimes it says 'Generated by ' XX cartridge
                // Remove the rest of the line from the comparison.
                if (this.ignoreLinesWithStrings != null && !this.ignoreLinesWithStrings.isEmpty())
                {
                    expectedContents = removeLinesWithStrings(expectedContents, ignoreLinesWithStrings);
                    actualContents = removeLinesWithStrings(actualContents, ignoreLinesWithStrings);
                }
                if (this.ignoreWhitespace)
                {
                    expectedContents = StringUtils.remove(expectedContents, ' ');
                    expectedContents = StringUtils.remove(expectedContents, '\t');
                    actualContents = StringUtils.remove(actualContents, ' ');
                    actualContents = StringUtils.remove(actualContents, '\t');
                }
                // TODO Tell me the line number where the difference is located.
                assertEquals(
                        message,
                        expectedContents.trim(),
                        actualContents.trim());
            }
        }
        catch (final Throwable throwable)
        {
            fail(throwable.toString());
        }
    }

    /**
     * @param input
     * @param patternList
     * @return String input with the lines containing the string removed
     */
    public String removeLinesWithStrings(String input, List<String> patternList)
    {
        String tempString = input;
        for (String pattern : patternList)
        {
            int patternIndex = tempString.indexOf(pattern);
            while (patternIndex > 0)
            {
                String temp = tempString.substring(patternIndex, tempString.length());
                temp = temp.substring(temp.indexOf('\n'));
                tempString = tempString.substring(0, patternIndex) + temp;
                patternIndex = tempString.indexOf(pattern);
            }
        }
        return tempString;
    }
    
    /**
     * Gets the actual file being compared.
     *
     * @return the file being compared.
     */
    public File getActualFile()
    {
        return this.actualFile;
    }

    /**
     * Gets the file expected file (i.e. the file that
     * the actual file is compared against).
     *
     * @return the expected file.
     */
    public File getExpectedFile()
    {
        return this.expectedFile;
    }
}