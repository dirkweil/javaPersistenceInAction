package de.gedoplan.baselibs.utils.util;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Helferklasse mit Object-bezogenen Operationen.
 * 
 * @author dw
 */
public final class ObjectUtil
{
  /**
   * Serialisierbares Objekt erzeugen.
   * 
   * Dies geschieht durch die Erzeugung eines dynamischen Proxies um das Original-Objekt herum. Dazu muss das Objekt das Interface
   * E (=mainInterface) implementieren.
   * 
   * @param object Original-Objekt
   * @param mainInterface Interface-Klasse dazu
   * @return Wrapper, der intf und Serializable implementiert
   */
  @SuppressWarnings("unchecked")
  public static <E> E createSerializableObject(E object, Class<E> mainInterface)
  {
    ClassLoader classLoader = object.getClass().getClassLoader();
    Class<?>[] interfaces = { mainInterface, Serializable.class };
    SerializableObjectInvocationHandler<E> handler = new SerializableObjectInvocationHandler<E>(object, mainInterface.getSimpleName());
    return (E) Proxy.newProxyInstance(classLoader, interfaces, handler);
  }

  private static class SerializableObjectInvocationHandler<E> implements InvocationHandler, Serializable
  {
    private transient E delegate;
    private String      name;

    public SerializableObjectInvocationHandler(E object, String name)
    {
      this.delegate = object;
      this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
      if (this.delegate == null)
      {
        throw new IllegalStateException(this.name + " is null. This is probably because the session/conversation has been discarded or replicated");
      }

      return method.invoke(this.delegate, args);
    }
  }

  private ObjectUtil()
  {
  }
}
