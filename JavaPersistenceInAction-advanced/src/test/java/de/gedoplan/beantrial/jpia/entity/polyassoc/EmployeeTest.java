package de.gedoplan.beantrial.jpia.entity.polyassoc;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

public class EmployeeTest extends PolyAssocTestBase
{
  @Test
  @Ignore
  public void showEmployees()
  {
    System.out.println("----- showEmployees -----");
    for (Employee employee : this.entityManager.createQuery("select x from Employee x", Employee.class).getResultList())
    {
      System.out.println(employee);
      System.out.println("  " + employee.getContact());
    }
  }

  @Test
  // @Ignore
  public void testContactTypes()
  {
    for (Employee testEmployee : testEmployees)
    {
      Employee employee = this.entityManager.createQuery("select x from Employee x where x.name=:name", Employee.class).setParameter("name", testEmployee.getName()).getSingleResult();
      Contact testContact = testEmployee.getContact();
      Contact contact = employee.getContact();
      Assert.assertEquals("Contact falsch", testContact, contact);
    }
  }
}
