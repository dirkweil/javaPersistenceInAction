package de.gedoplan.beantrial.jpia.openjpa;

import de.gedoplan.beantrial.jpia.entity.inherit.Car;
import de.gedoplan.beantrial.jpia.entity.inherit.Lorry;
import de.gedoplan.beantrial.jpia.entity.inherit.Ship;
import de.gedoplan.beantrial.jpia.entity.inherit.Vehicle;

import java.sql.SQLException;

import javax.persistence.PersistenceException;

import org.apache.openjpa.jdbc.kernel.JDBCStore;
import org.apache.openjpa.jdbc.meta.ClassMapping;
import org.apache.openjpa.jdbc.meta.strats.AbstractDiscriminatorStrategy;
import org.apache.openjpa.jdbc.schema.Column;
import org.apache.openjpa.jdbc.sql.Joins;
import org.apache.openjpa.jdbc.sql.Result;
import org.apache.openjpa.jdbc.sql.SQLBuffer;
import org.apache.openjpa.jdbc.sql.Select;

/**
 * Diskriminator-Strategie für {@link Vehicle}.
 * 
 * Diese OpenJPA-spezifische Klasse definiert, wie gelesene Einträge dem Typ {@link Vehicle} und den zugehörigen Subklassen
 * zugeordnet werden.
 * 
 * @author dw
 * 
 */
public class VehicleDiscriminatorStrategy extends AbstractDiscriminatorStrategy
{
  /**
   * {@inheritDoc}
   * 
   * @see org.apache.openjpa.jdbc.meta.strats.AbstractDiscriminatorStrategy#getClass(org.apache.openjpa.jdbc.kernel.JDBCStore,
   *      org.apache.openjpa.jdbc.meta.ClassMapping, org.apache.openjpa.jdbc.sql.Result)
   */
  @Override
  public Class<?> getClass(JDBCStore store, ClassMapping base, Result result) throws SQLException, ClassNotFoundException
  {
    String id = result.getString(base.getField("id").getIndex() + 1);
    if (id.startsWith(Car.ID_PREFIX))
    {
      return Car.class;
    }
    else if (id.startsWith(Ship.ID_PREFIX))
    {
      return Ship.class;
    }
    else if (id.startsWith(Lorry.ID_PREFIX))
    {
      return Lorry.class;
    }
    else
    {
      throw new PersistenceException("Illegal vehicle id");
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.apache.openjpa.jdbc.meta.strats.AbstractDiscriminatorStrategy#hasClassConditions(org.apache.openjpa.jdbc.meta.ClassMapping,
   *      boolean)
   */
  @Override
  public boolean hasClassConditions(ClassMapping base, boolean subs)
  {
    return !Vehicle.class.equals(base.getDescribedType());
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.apache.openjpa.jdbc.meta.strats.AbstractDiscriminatorStrategy#getClassConditions(org.apache.openjpa.jdbc.sql.Select,
   *      org.apache.openjpa.jdbc.sql.Joins, org.apache.openjpa.jdbc.meta.ClassMapping, boolean)
   */
  @Override
  public SQLBuffer getClassConditions(Select sel, Joins joins, ClassMapping base, boolean subs)
  {
    SQLBuffer sql = new SQLBuffer(sel.getConfiguration().getDBDictionaryInstance());

    Column idColumn = base.getFieldMapping("id").getColumns()[0];
    String idAlias = sel.getColumnAlias(idColumn, joins);
    String prefix = "left(" + idAlias + ",2)";

    Class<?> describedType = base.getDescribedType();
    if (Car.class.equals(describedType))
    {
      sql.append(prefix + " in ('" + Car.ID_PREFIX + "'");
      if (subs)
      {
        sql.append(",'" + Lorry.ID_PREFIX + "'");
      }
      sql.append(")");
      return sql;
    }
    else if (Lorry.class.equals(describedType))
    {
      sql.append(prefix + "='" + Lorry.ID_PREFIX + "'");
      return sql;
    }
    else if (Ship.class.equals(describedType))
    {
      sql.append(prefix + "='" + Ship.ID_PREFIX + "'");
      return sql;
    }
    return null;
  }

}
