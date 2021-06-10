package com.app.entities.attributes.ids.generators;

import java.io.Serializable;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.app.entities.BasicEntityAcccessContract;

public class CustomStringIdGenerator implements IdentifierGenerator, Configurable{
	private final Logger logger =Logger.getLogger(getClass().getName());
	
	public static final String SEQUENCE_PREFIX = "SEQUENCE_PREFIX";
	public static final String SEPARATOR_REQUIRED = "SEPARATOR_REQUIRED";
	public static final String SEQUENCE_PREFIX_SEPARATOR = "SEQUENCE_PREFIX_SEPARATOR";
	public static final String SEQUENCE_SUFFIX_SEPARATOR = "SEQUENCE_SUFFIX_SEPARATOR";
	public static final String SEQUENCE_PREFIX_SEPARATOR_REQUIRED = "SEQUENCE_PREFIX_SEPARATOR_REQUIRED";
	public static final String SEQUENCE_SUFFIX_SEPARATOR_REQUIRED = "SEQUENCE_PREFIX_SEPARATOR_REQUIRED";
	public static final String SEQUENCE_SUFFIX = "SEQUENCE_SUFFIX";
	public static final String SEQUENCE_FORMAT = "SEQUENCE_FORMAT";
	
	private String sequencePrefix, sequenceSuffix, sequencePrefixSeparator, sequenceSuffixSeparator;
	private boolean separatorRequired, prefixSeparatorRequired, suffixSeparatorRequired;
	
	private String sequenceSyntaxCall;
	private String sequenceFormat;
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		//get the jdbc environment instance from the session's service registry!
		final JdbcEnvironment jdbcEnv = serviceRegistry.getService(JdbcEnvironment.class);
		//get the configuration service instance from the session's service registry!
		final ConfigurationService configService = serviceRegistry.getService(ConfigurationService.class);
		//get the current context's dialect from the jdbc environment instance
		final Dialect dialect  = jdbcEnv.getDialect();
		
		prefixSeparatorRequired = ConfigurationHelper.getBoolean(SEQUENCE_PREFIX_SEPARATOR_REQUIRED, params, false);
		sequencePrefixSeparator = ConfigurationHelper.getString(SEQUENCE_PREFIX_SEPARATOR, params, "");
		
		suffixSeparatorRequired = ConfigurationHelper.getBoolean(SEQUENCE_SUFFIX_SEPARATOR_REQUIRED, params, false);
		sequenceSuffixSeparator = ConfigurationHelper.getString(SEQUENCE_SUFFIX_SEPARATOR, params, "");
		
		//get the default prefix information from the application properties file! If not configured there, it this variable will be having "SEQ_" as prefix by default!
		final String globalIdPrefix = configService.getSetting("global.entity.id.prefix", String.class, "");
		//getting the sequence prefix from the caller annotation!, If not provided there, then the global Id prefix will be used!
		sequencePrefix = ConfigurationHelper.getString(SEQUENCE_PREFIX, params, globalIdPrefix);
		
		//get the default suffix information from the application properties file! If not configured there, it this variable will be having "" as suffix by default!
		final String globalIdsuffix = configService.getSetting("global.entity.id.suffix", String.class, "");
		//getting the sequence suffix from the caller annotation!, If not provided there, then the global Id suffix will be used!
		sequenceSuffix = ConfigurationHelper.getString(SEQUENCE_SUFFIX, params, globalIdsuffix);
		
		//get the sequence format from the annotation!
		String sequenceItemFormat = ConfigurationHelper.getString(SEQUENCE_FORMAT, params, "");
		
		separatorRequired 		= ConfigurationHelper.getBoolean(SEPARATOR_REQUIRED, params, false);
		prefixSeparatorRequired = ConfigurationHelper.getBoolean(SEQUENCE_PREFIX_SEPARATOR_REQUIRED, params, false);
		suffixSeparatorRequired = ConfigurationHelper.getBoolean(SEQUENCE_SUFFIX_SEPARATOR_REQUIRED, params, false);
		
		sequenceFormat = new String();
		if(separatorRequired)//if separator is true, then we will be apllying the separator format!
		{
			sequenceFormat = 
					sequenceFormat
					.concat((prefixSeparatorRequired && !sequencePrefix.trim().isEmpty())?"%1$s".concat(sequencePrefixSeparator):"")
					.concat("%2$".concat(sequenceItemFormat))
					.concat((suffixSeparatorRequired && !sequenceSuffix.trim().isEmpty())?sequenceSuffixSeparator.concat("%3$s"):"");
		}
		else
		{
			sequenceFormat = "%1$s%2$".concat(sequenceItemFormat).concat("%3$s");
		}
		
		logger.info("sequence format:"+sequenceFormat);
		
		final String defaultSequenceName = ConfigurationHelper.getString(SequenceStyleGenerator.DEF_SEQUENCE_NAME, params, "DEFAULT_SEQUENCE");
		
		sequenceSyntaxCall = dialect.getSequenceNextValString(ConfigurationHelper.getString(SequenceStyleGenerator.SEQUENCE_PARAM, params, defaultSequenceName));
		logger.info("sequenceSyntaxCall:"+sequenceSyntaxCall);
	}
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		if(object instanceof BasicEntityAcccessContract)
		{
			BasicEntityAcccessContract<String> accessObject = (BasicEntityAcccessContract<String>) object;
			
			Serializable data = accessObject.getId();
			
			//If the call is for the update operation, then we should not proceed!
			if(!Objects.isNull(data))
				return data;
		}
		
		long sequenceNumber = ((Number)session.createNativeQuery(sequenceSyntaxCall).uniqueResult()).longValue();
		
		return String.format(sequenceFormat, sequencePrefix, sequenceNumber, sequenceSuffix);
	}
}
