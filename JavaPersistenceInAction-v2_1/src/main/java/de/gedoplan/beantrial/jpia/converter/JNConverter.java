package de.gedoplan.beantrial.jpia.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JNConverter implements AttributeConverter<Boolean, String>
{

  @Override
  public String convertToDatabaseColumn(Boolean attributeValue)
  {
    if (attributeValue == null)
    {
      return null;
    }
    return attributeValue ? "J" : "N";
  }

  @Override
  public Boolean convertToEntityAttribute(String columnValue)
  {
    if (columnValue == null)
    {
      return null;
    }
    return columnValue.equals("J");
  }

}
