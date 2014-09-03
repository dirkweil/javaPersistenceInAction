package de.gedoplan.beantrial.jpia.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Producer für einen transaktionsgebundenen Entity Manager.
 *
 * @author dw
 */
@ApplicationScoped
public class EntityManagerProducer
{
  @PersistenceContext(unitName = "conference")
  @Produces
  EntityManager entityManager;
}
