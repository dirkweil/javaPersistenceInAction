package de.gedoplan.beantrial.jpia.eclipselink;

import de.gedoplan.beantrial.jpia.entity.inherit.Car;
import de.gedoplan.beantrial.jpia.entity.inherit.Lorry;
import de.gedoplan.beantrial.jpia.entity.inherit.Ship;
import de.gedoplan.beantrial.jpia.entity.inherit.Vehicle;

import javax.persistence.PersistenceException;

import org.eclipse.persistence.descriptors.ClassExtractor;
import org.eclipse.persistence.sessions.Record;
import org.eclipse.persistence.sessions.Session;

/**
 * Class extractor für {@link Vehicle}.
 * 
 * Diese EclipseLink-spezifische Klasse ermittelt aus einer gelesenen DB-Zeile den zugehörigen Java-Typ.
 * 
 * @author dw
 * 
 */
public class VehicleClassExtractor extends ClassExtractor
{
  @Override
  public Class<? extends Vehicle> extractClassFromRow(Record databaseRow, Session session)
  {
    String id = (String) databaseRow.get("ID");
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

}
