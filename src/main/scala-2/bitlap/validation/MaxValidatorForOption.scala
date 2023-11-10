package bitlap.validation

import javax.validation.{ ConstraintValidator, ConstraintValidatorContext }
import javax.validation.constraints.Max

import org.hibernate.validator.internal.constraintvalidators.bv.{ MaxValidatorForCharSequence, MaxValidatorForNumber }

/**
 * Check that the wrapped character sequence (e.g. Option[String]) and the number validated represents a number, and has
 * a value less than or equal to the maximum value specified.
 */
class MaxValidatorForOption extends ConstraintValidator[Max, Option[_]] {
  private var constraintAnnotation: Max = _

  override def initialize(constraintAnnotation: Max): Unit =
    this.constraintAnnotation = constraintAnnotation

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean =
    value match {
      case Some(x: CharSequence) =>
        val v = new MaxValidatorForCharSequence
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: Number)       =>
        val v = new MaxValidatorForNumber
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None                  =>
        true
      case Some(_)               =>
        throw new IllegalStateException("oops.")
    }
}
