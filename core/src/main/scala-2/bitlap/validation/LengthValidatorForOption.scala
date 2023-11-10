package bitlap.validation

import javax.validation.{ ConstraintValidator, ConstraintValidatorContext }

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator

/**
 * Check that the wrapped character sequence length is between min and max.
 */
class LengthValidatorForOption extends ConstraintValidator[Length, Option[_]] {
  private var constraintAnnotation: Length = _

  override def initialize(constraintAnnotation: Length): Unit =
    this.constraintAnnotation = constraintAnnotation

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean =
    value match {
      case Some(x: CharSequence) =>
        val v = new LengthValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None                  =>
        true
      case Some(_)               =>
        throw new IllegalStateException("oops.")
    }
}
