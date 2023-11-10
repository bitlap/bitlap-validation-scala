package bitlap.validation

import javax.validation.constraints.AssertFalse

import scala.annotation.meta.field

class AssertFalseValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
    @(AssertFalse @field)
    hasSomething: Option[Boolean]
  )

  Seq(
    (TestBean(Some(true)), 1),
    (TestBean(Some(false)), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
