package de.gedoplan.beantrial.jpia.eclipselink;

import de.gedoplan.beantrial.jpia.entity.inherit.Car;
import de.gedoplan.beantrial.jpia.entity.inherit.Lorry;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

public class CarCustomizer implements DescriptorCustomizer
{
  @Override
  public void customize(ClassDescriptor descriptor) throws Exception
  {
    ExpressionBuilder account = new ExpressionBuilder();
    Expression idField = account.getField("ID");

    Expression carInstancesExpression = idField.substring(0, Car.ID_PREFIX.length()).equal(Car.ID_PREFIX);
    descriptor.getInheritancePolicy().setOnlyInstancesExpression(carInstancesExpression);

    Expression lorryInstancesExpression = idField.substring(0, Lorry.ID_PREFIX.length()).equal(Lorry.ID_PREFIX);
    descriptor.getInheritancePolicy().setWithAllSubclassesExpression(carInstancesExpression.or(lorryInstancesExpression));
  }

}
