package de.gedoplan.beantrial.jpia.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.EnhancedUserType;

/**
 * A type that maps between {@link java.sql.Types#CHAR CHAR(1)} and {@link Boolean} (using 'J' and 'N')
 * 
 * @author Gavin King
 * @author Steve Ebersole
 * @author dw
 */
public class JNType implements EnhancedUserType
{

  @Override
  public int[] sqlTypes()
  {
    return new int[] { Types.VARCHAR };
  }

  @Override
  public Class<?> returnedClass()
  {
    return Boolean.class;
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException
  {
    if (x == null)
    {
      return y == null;
    }
    else
    {
      return x.equals(y);
    }
  }

  @Override
  public int hashCode(Object x) throws HibernateException
  {
    return x == null ? 0 : x.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException
  {
    String colVal = rs.getString(names[0]);
    if (rs.wasNull())
    {
      return null;
    }
    return "J".equals(colVal);
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException
  {
    if (value == null)
    {
      st.setNull(index, Types.VARCHAR);
    }
    else
    {
      st.setString(index, ((Boolean) value) ? "J" : "N");
    }

  }

  @Override
  public Object deepCopy(Object value) throws HibernateException
  {
    return Boolean.valueOf((Boolean) value);
  }

  @Override
  public boolean isMutable()
  {
    return false;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException
  {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException
  {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException
  {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public String objectToSQLString(Object value)
  {
    if (value == null)
    {
      return null;
    }

    return ((Boolean) value) ? "'J'" : "'N'";
  }

  @Override
  public String toXMLString(Object value)
  {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public Object fromXMLString(String xmlValue)
  {
    throw new RuntimeException("Not implemented");
  }
}
