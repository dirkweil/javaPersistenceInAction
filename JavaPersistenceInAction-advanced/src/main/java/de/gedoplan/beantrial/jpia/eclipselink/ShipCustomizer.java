package de.gedoplan.beantrial.jpia.eclipselink;

import de.gedoplan.beantrial.jpia.entity.inherit.Ship;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

public class ShipCustomizer implements DescriptorCustomizer
{
  private static final String ID_PREFIX = Ship.ID_PREFIX;

  @Override
  public void customize(ClassDescriptor descriptor) throws Exception
  {
    ExpressionBuilder account = new ExpressionBuilder();
    Expression expression = account.getField("ID").substring(0, ID_PREFIX.length()).equal(ID_PREFIX);
    descriptor.getInheritancePolicy().setOnlyInstancesExpression(expression);
  }

}
