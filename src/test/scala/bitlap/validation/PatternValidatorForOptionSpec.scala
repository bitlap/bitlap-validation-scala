package bitlap.validation

import javax.validation.constraints.Pattern

import scala.annotation.meta.field

class PatternValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
    @(Pattern @field)(regexp = "abc")
    value: Option[String]
  )

  Seq(
    (TestBean(Some("abcd")), 1),
    (TestBean(Some("abc")), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
