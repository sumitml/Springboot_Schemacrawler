package com.example.demo;

import static us.fatehi.commandlineparser.CommandLineUtility.applyApplicationLogLevel;
import static us.fatehi.commandlineparser.CommandLineUtility.logSystemClasspath;
import static us.fatehi.commandlineparser.CommandLineUtility.logSystemProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schema.View;
import schemacrawler.schemacrawler.DatabaseConnectionOptions;
import schemacrawler.schemacrawler.RegularExpressionInclusionRule;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaCrawlerOptionsBuilder;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.utility.SchemaCrawlerUtility;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
		
		// Turn application logging on by applying the correct log level
	    applyApplicationLogLevel(Level.OFF);
	    // Log system properties and classpath
	    logSystemClasspath();
	    logSystemProperties();
	    
	    final SchemaCrawlerOptionsBuilder optionsBuilder = SchemaCrawlerOptionsBuilder
	    	      .builder()
	    	      // Set what details are required in the schema - this affects the
	    	      // time taken to crawl the schema
	    	      .withSchemaInfoLevel(SchemaInfoLevelBuilder.standard())
	    	      .includeAllRoutines();
	    
	    final SchemaCrawlerOptions options = optionsBuilder.toOptions();
	    // Get the schema definition
	    final Catalog catalog = SchemaCrawlerUtility.getCatalog(getConnection(),
	                                                            options);
	    
	    for (final Schema schema: catalog.getSchemas())
	    {
	      System.out.println(schema);
	      for (final Table table: catalog.getTables(schema))
	      {
	        System.out.print("o--> " + table);
	        if (table instanceof View)
	        {
	          System.out.println(" (VIEW)");
	        }
	        else
	        {
	          System.out.println();
	        }

	        for (final Column column: table.getColumns())
	        {
	          System.out.println("     o--> " + column + " ("
	                             + column.getColumnDataType() + ")");
	        }
	      }
	    }
	}
	
	private static Connection getConnection()
		    throws SchemaCrawlerException, SQLException
		  {
		    final String connectionUrl = "jdbc:h2:mem:testdb";
		    final DataSource dataSource = new DatabaseConnectionOptions(connectionUrl);
		    return dataSource.getConnection("sa", "");
		  }
}
