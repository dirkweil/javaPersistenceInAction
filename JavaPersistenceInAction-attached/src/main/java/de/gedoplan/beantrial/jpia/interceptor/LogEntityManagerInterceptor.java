package de.gedoplan.beantrial.jpia.interceptor;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Interceptor
@LogEntityManager
public class LogEntityManagerInterceptor implements Serializable
{
  @Inject
  EntityManager                             entityManager;

  private static final Log                  LOG                    = LogFactory.getLog(LogEntityManagerInterceptor.class);

  private static Set<WeakReference<Object>> entityManagerDelegates = new HashSet<>();

  @AroundInvoke
  public Object aroundInvoke(InvocationContext invocationContext) throws Exception
  {
    entityManagerDelegates.add(new WeakReference<Object>(this.entityManager.getDelegate()));

    return invocationContext.proceed();
  }

  public static void logEntityManagers(boolean removeClosed)
  {
    /*
     * Achtung: Dieser GC-Aufruf hätte in einem 'normalen' Programm nichts zu suchen. Hier dient er nur dazu, die ggf. bereits
     * freigegebenen Referenzen in entityManagerDelegates zu loeschen, damit nicht unnötig viele EM-Delegates angezeigt werden.
     */
    System.gc();

    if (LOG.isDebugEnabled())
    {
      LOG.debug("EntityManager Delegates:");
      Iterator<WeakReference<Object>> iterator = entityManagerDelegates.iterator();
      while (iterator.hasNext())
      {
        WeakReference<Object> delegateReference = iterator.next();
        Object delegate = delegateReference.get();
        if (delegate != null)
        {
          boolean open = isOpen(delegate);
          LOG.debug("  " + delegate + " (open=" + open + ")");

          if (removeClosed && !open)
          {
            iterator.remove();
          }
        }
      }
    }
  }

  public static Object getAttachedEntityManager(Object entity)
  {
    for (WeakReference<Object> delegateReference : entityManagerDelegates)
    {
      Object delegate = delegateReference.get();
      if (delegate != null)
      {
        if (isOpen(delegate) && contains(delegate, entity))
        {
          return delegate;
        }
      }
    }
    return null;
  }

  private static boolean isOpen(Object delegate)
  {
    if (delegate instanceof EntityManager)
    {
      return ((EntityManager) delegate).isOpen();
    }

    try
    {
      Method method = delegate.getClass().getMethod("isOpen", (Class<?>[]) null);
      return (boolean) method.invoke(delegate, (Object[]) null);
    }
    catch (Exception e)
    {
      LOG.warn("Cannot determine state of em delegate " + delegate, e);
    }

    return false;
  }

  private static boolean contains(Object delegate, Object entity)
  {
    if (delegate instanceof EntityManager)
    {
      return ((EntityManager) delegate).contains(entity);
    }

    try
    {
      Method method = delegate.getClass().getMethod("contains", (Class<?>) Object.class);
      return (boolean) method.invoke(delegate, entity);
    }
    catch (Exception e)
    {
      LOG.warn("Cannot determine state of em delegate " + delegate, e);
    }

    return false;
  }
}
