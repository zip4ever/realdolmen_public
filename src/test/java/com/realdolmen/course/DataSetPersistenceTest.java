package com.realdolmen.course;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.junit.Before;
import org.slf4j.Logger;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by KDAAU95 on 10/09/2014.
 */
public class DataSetPersistenceTest extends PersistenceTest {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(DataSetPersistenceTest.class);

    @Before
    public void loadDataSet() throws Exception {
        logger.info("Loading dataset with dbUnit");
        // create connection
        Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3307/test", "root", "password");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // get dataset
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("C:\\jee610-software\\workspace\\jpa-standalone\\src\\test\\resources\\data.xml"));

        // clean existing and insert dataset
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        logger.info("... done");
    }
}
