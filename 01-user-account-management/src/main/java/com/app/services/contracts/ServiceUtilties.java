package com.app.services.contracts;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public interface ServiceUtilties {
	default ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return modelMapper;
	}
}
