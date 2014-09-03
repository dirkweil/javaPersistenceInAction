package de.gedoplan.beantrial.jpia.producer;

import de.gedoplan.baselibs.utils.util.ObjectUtil;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Producer für einen konversationsgebundenen Entity Manager.
 *
 * @author dw
 */
@ApplicationScoped
public class EntityManagerProducer
{
  @PersistenceUnit(unitName = "conference")
  private EntityManagerFactory entityManagerFactory;

  private static final Log     LOG = LogFactory.getLog(EntityManagerProducer.class);

  /**
   * Konversationsgebundenen EntityManager erstellen.
   *
   * @return Entity Manager
   */
  @Produces
  @ConversationScoped
  public EntityManager createEntityManager()
  {
    EntityManager entityManager = this.entityManagerFactory.createEntityManager();

    // TALKABOUT A9) Nicht alle EM sind serializable; falls nicht, mit Proxy serialisierbar machen

    if (!(entityManager instanceof Serializable))
    {
      if (LOG.isTraceEnabled())
      {
        LOG.trace("EntityManager is not serializable; creating serializable wrapper");
      }

      entityManager = ObjectUtil.createSerializableObject(entityManager, EntityManager.class);
    }

    if (LOG.isTraceEnabled())
    {
      LOG.trace("createEntityManager: " + entityManager + " (flushMode=" + entityManager.getFlushMode() + ", properties=" + entityManager.getProperties() + ")");
    }

    return entityManager;
  }

  /**
   * Entity Manager nach Gebrauch entsorgen.
   *
   * @param entityManager
   */
  public void disposeEntityManager(@Disposes @Any EntityManager entityManager)
  {
    if (LOG.isTraceEnabled())
    {
      LOG.trace("disposeEntityManager: " + entityManager);
    }

    if (entityManager.isOpen())
    {
      entityManager.close();
    }
  }
}
