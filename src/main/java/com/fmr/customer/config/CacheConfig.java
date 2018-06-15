package com.fmr.customer.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fmr.customer.model.Customer;




@Configuration
public class CacheConfig {
	 @Bean
	 public Ignite igniteInstance() {
	        IgniteConfiguration cfg = new IgniteConfiguration();
	        // Setting some custom name for the node.
	        cfg.setIgniteInstanceName("springDataNode");
	        // Enabling peer-class loading feature.
	       // cfg.setPeerClassLoadingEnabled(true);
	        // Defining and creating a new cache to be used by Ignite Spring Data
	        // repository.
	        CacheConfiguration ccfgCustomer = new CacheConfiguration("CustomerCache");
	       
	        // Setting SQL schema for the cache.
	        ccfgCustomer.setIndexedTypes(Long.class,Customer.class);
	       
	 
	        cfg.setCacheConfiguration(new CacheConfiguration[]{ccfgCustomer});
	 
	        return Ignition.start(cfg);
	    }
}
