package com.app.entities.attributes.converters;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, Integer>{
	private static final Integer trueI  = Integer.valueOf(1);
	//private static final Integer falseI = Integer.valueOf(0);
	@Override
	public Integer convertToDatabaseColumn(Boolean attribute) {
		if(Objects.isNull(attribute))
			return Integer.valueOf(0);
		
		return attribute.booleanValue()? Integer.valueOf(1): Integer.valueOf(0);
	}

	@Override
	public Boolean convertToEntityAttribute(Integer dbData) {
		return trueI.equals(dbData);
	}
}
