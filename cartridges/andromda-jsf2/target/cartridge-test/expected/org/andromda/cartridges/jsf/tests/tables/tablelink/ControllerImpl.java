// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\ControllerImpl.java.vsl) on 07/30/2011 09:32:41-0300
package org.andromda.cartridges.jsf.tests.tables.tablelink;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @see org.andromda.cartridges.jsf.tests.tables.tablelink.Controller
 */
public class ControllerImpl
    extends Controller
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 5823332326247845093L;

    /**
     * @see org.andromda.cartridges.jsf.tests.tables.tablelink.Controller#loadTableData(java.util.Collection tableDatas, String[] multiboxThings)
     */
    @Override
    public void loadTableData(LoadTableDataForm form)
    {
        // populating the table with a dummy list
        form.setTableDatas(tableDatas);
        // populating the table with a dummy list
        form.setMultiboxThings(multiboxThings);
    }

    /**
     * This dummy variable is used to populate the "tableDatas" table.
     * You may delete it when you add you own code in this controller.
     */
    private static final List tableDatas =
        Arrays.asList(new Object[] {
            new TableDatas("first-1", "second-1", "third-1", "fourth-1"),
            new TableDatas("first-2", "second-2", "third-2", "fourth-2"),
            new TableDatas("first-3", "second-3", "third-3", "fourth-3"),
            new TableDatas("first-4", "second-4", "third-4", "fourth-4"),
            new TableDatas("first-5", "second-5", "third-5", "fourth-5"),
            new TableDatas("first-6", "second-6", "third-6", "fourth-6"),
            new TableDatas("first-7", "second-7", "third-7", "fourth-7"),
            new TableDatas("first-8", "second-8", "third-8", "fourth-8"),
            new TableDatas("first-9", "second-9", "third-9", "fourth-9"),
            new TableDatas("first-10", "second-10", "third-10", "fourth-10"),
            new TableDatas("first-11", "second-11", "third-11", "fourth-11"),
            new TableDatas("first-12", "second-12", "third-12", "fourth-12"),
            new TableDatas("first-13", "second-13", "third-13", "fourth-13"),
            new TableDatas("first-14", "second-14", "third-14", "fourth-14"),
            new TableDatas("first-15", "second-15", "third-15", "fourth-15"),
            new TableDatas("first-16", "second-16", "third-16", "fourth-16"),
            new TableDatas("first-17", "second-17", "third-17", "fourth-17"),
            new TableDatas("first-18", "second-18", "third-18", "fourth-18"),
            new TableDatas("first-19", "second-19", "third-19", "fourth-19"),
            new TableDatas("first-20", "second-20", "third-20", "fourth-20")
        });

    /**
     * This inner class is used in the dummy implementation in order to get the web application
     * running without any manual programming.
     * You may delete this class when you add you own code in this controller.
     */
    public static final class TableDatas implements Serializable
    {
        private String first = null;
        private String second = null;
        private String third = null;
        private String fourth = null;

        public TableDatas(String first, String second, String third, String fourth)
        {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
        }

        public void setFirst(String first)
        {
            this.first = first;
        }

        public String getFirst()
        {
            return this.first;
        }

        public void setSecond(String second)
        {
            this.second = second;
        }

        public String getSecond()
        {
            return this.second;
        }

        public void setThird(String third)
        {
            this.third = third;
        }

        public String getThird()
        {
            return this.third;
        }

        public void setFourth(String fourth)
        {
            this.fourth = fourth;
        }

        public String getFourth()
        {
            return this.fourth;
        }

    }
    /**
     * This dummy variable is used to populate the "multiboxThings" table.
     * You may delete it when you add you own code in this controller.
     */
    private static final String[] multiboxThings =
        new String[]
        {
            new String(),
            new String(),
            new String(),
            new String(),
            new String()
        };
    /**
     * This dummy variable is used to populate the "tableDataDefaultExportTypes" table.
     * You may delete it when you add you own code in this controller.
     */
    private static final List tableDataDefaultExportTypes =
        Arrays.asList(new Object[] {
            new TableDataDefaultExportTypes("one-1", "two-1", "three-1"),
            new TableDataDefaultExportTypes("one-2", "two-2", "three-2"),
            new TableDataDefaultExportTypes("one-3", "two-3", "three-3"),
            new TableDataDefaultExportTypes("one-4", "two-4", "three-4"),
            new TableDataDefaultExportTypes("one-5", "two-5", "three-5"),
            new TableDataDefaultExportTypes("one-6", "two-6", "three-6"),
            new TableDataDefaultExportTypes("one-7", "two-7", "three-7"),
            new TableDataDefaultExportTypes("one-8", "two-8", "three-8"),
            new TableDataDefaultExportTypes("one-9", "two-9", "three-9"),
            new TableDataDefaultExportTypes("one-10", "two-10", "three-10"),
            new TableDataDefaultExportTypes("one-11", "two-11", "three-11"),
            new TableDataDefaultExportTypes("one-12", "two-12", "three-12"),
            new TableDataDefaultExportTypes("one-13", "two-13", "three-13"),
            new TableDataDefaultExportTypes("one-14", "two-14", "three-14"),
            new TableDataDefaultExportTypes("one-15", "two-15", "three-15"),
            new TableDataDefaultExportTypes("one-16", "two-16", "three-16"),
            new TableDataDefaultExportTypes("one-17", "two-17", "three-17"),
            new TableDataDefaultExportTypes("one-18", "two-18", "three-18"),
            new TableDataDefaultExportTypes("one-19", "two-19", "three-19"),
            new TableDataDefaultExportTypes("one-20", "two-20", "three-20")
        });

    /**
     * This inner class is used in the dummy implementation in order to get the web application
     * running without any manual programming.
     * You may delete this class when you add you own code in this controller.
     */
    public static final class TableDataDefaultExportTypes implements Serializable
    {
        private String one = null;
        private String two = null;
        private String three = null;

        public TableDataDefaultExportTypes(String one, String two, String three)
        {
            this.one = one;
            this.two = two;
            this.three = three;
        }

        public void setOne(String one)
        {
            this.one = one;
        }

        public String getOne()
        {
            return this.one;
        }

        public void setTwo(String two)
        {
            this.two = two;
        }

        public String getTwo()
        {
            return this.two;
        }

        public void setThree(String three)
        {
            this.three = three;
        }

        public String getThree()
        {
            return this.three;
        }

    }
    /**
     * This dummy variable is used to populate the "tableDataNoExportTypes" table.
     * You may delete it when you add you own code in this controller.
     */
    private static final List tableDataNoExportTypes =
        Arrays.asList(new Object[] {
            new TableDataNoExportTypes("one-1", "two-1", "three-1"),
            new TableDataNoExportTypes("one-2", "two-2", "three-2"),
            new TableDataNoExportTypes("one-3", "two-3", "three-3"),
            new TableDataNoExportTypes("one-4", "two-4", "three-4"),
            new TableDataNoExportTypes("one-5", "two-5", "three-5"),
            new TableDataNoExportTypes("one-6", "two-6", "three-6"),
            new TableDataNoExportTypes("one-7", "two-7", "three-7"),
            new TableDataNoExportTypes("one-8", "two-8", "three-8"),
            new TableDataNoExportTypes("one-9", "two-9", "three-9"),
            new TableDataNoExportTypes("one-10", "two-10", "three-10"),
            new TableDataNoExportTypes("one-11", "two-11", "three-11"),
            new TableDataNoExportTypes("one-12", "two-12", "three-12"),
            new TableDataNoExportTypes("one-13", "two-13", "three-13"),
            new TableDataNoExportTypes("one-14", "two-14", "three-14"),
            new TableDataNoExportTypes("one-15", "two-15", "three-15"),
            new TableDataNoExportTypes("one-16", "two-16", "three-16"),
            new TableDataNoExportTypes("one-17", "two-17", "three-17"),
            new TableDataNoExportTypes("one-18", "two-18", "three-18"),
            new TableDataNoExportTypes("one-19", "two-19", "three-19"),
            new TableDataNoExportTypes("one-20", "two-20", "three-20")
        });

    /**
     * This inner class is used in the dummy implementation in order to get the web application
     * running without any manual programming.
     * You may delete this class when you add you own code in this controller.
     */
    public static final class TableDataNoExportTypes implements Serializable
    {
        private String one = null;
        private String two = null;
        private String three = null;

        public TableDataNoExportTypes(String one, String two, String three)
        {
            this.one = one;
            this.two = two;
            this.three = three;
        }

        public void setOne(String one)
        {
            this.one = one;
        }

        public String getOne()
        {
            return this.one;
        }

        public void setTwo(String two)
        {
            this.two = two;
        }

        public String getTwo()
        {
            return this.two;
        }

        public void setThree(String three)
        {
            this.three = three;
        }

        public String getThree()
        {
            return this.three;
        }

    }
    /**
     * This dummy variable is used to populate the "tableDataNotSortables" table.
     * You may delete it when you add you own code in this controller.
     */
    private static final List tableDataNotSortables =
        Arrays.asList(new Object[] {
            new TableDataNotSortables("one-1", "two-1", "three-1"),
            new TableDataNotSortables("one-2", "two-2", "three-2"),
            new TableDataNotSortables("one-3", "two-3", "three-3"),
            new TableDataNotSortables("one-4", "two-4", "three-4"),
            new TableDataNotSortables("one-5", "two-5", "three-5"),
            new TableDataNotSortables("one-6", "two-6", "three-6"),
            new TableDataNotSortables("one-7", "two-7", "three-7"),
            new TableDataNotSortables("one-8", "two-8", "three-8"),
            new TableDataNotSortables("one-9", "two-9", "three-9"),
            new TableDataNotSortables("one-10", "two-10", "three-10"),
            new TableDataNotSortables("one-11", "two-11", "three-11"),
            new TableDataNotSortables("one-12", "two-12", "three-12"),
            new TableDataNotSortables("one-13", "two-13", "three-13"),
            new TableDataNotSortables("one-14", "two-14", "three-14"),
            new TableDataNotSortables("one-15", "two-15", "three-15"),
            new TableDataNotSortables("one-16", "two-16", "three-16"),
            new TableDataNotSortables("one-17", "two-17", "three-17"),
            new TableDataNotSortables("one-18", "two-18", "three-18"),
            new TableDataNotSortables("one-19", "two-19", "three-19"),
            new TableDataNotSortables("one-20", "two-20", "three-20")
        });

    /**
     * This inner class is used in the dummy implementation in order to get the web application
     * running without any manual programming.
     * You may delete this class when you add you own code in this controller.
     */
    public static final class TableDataNotSortables implements Serializable
    {
        private String one = null;
        private String two = null;
        private String three = null;

        public TableDataNotSortables(String one, String two, String three)
        {
            this.one = one;
            this.two = two;
            this.three = three;
        }

        public void setOne(String one)
        {
            this.one = one;
        }

        public String getOne()
        {
            return this.one;
        }

        public void setTwo(String two)
        {
            this.two = two;
        }

        public String getTwo()
        {
            return this.two;
        }

        public void setThree(String three)
        {
            this.three = three;
        }

        public String getThree()
        {
            return this.three;
        }

    }
}