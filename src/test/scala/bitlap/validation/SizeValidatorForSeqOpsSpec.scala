package bitlap.validation

import javax.validation.constraints.Size

import scala.annotation.meta.field

class SizeValidatorForSeqOpsSpec extends BaseSpec {

  private[this] case class TestBeanWithSeq(
    @(Size @field)(min = 1)
    name: Seq[String]
  )

  private[this] case class TestBeanWithList(
    @(Size @field)(min = 1)
    name: List[String]
  )

  private[this] case class TestBeanWithVector(
    @(Size @field)(min = 1)
    name: Vector[String]
  )

  private[this] case class TestBeanWithArray(
    @(Size @field)(min = 1)
    name: Array[String]
  )

  Seq(
    (TestBeanWithSeq(Seq()), 1),
    (TestBeanWithSeq(Seq("1")), 0),
    (TestBeanWithList(List()), 1),
    (TestBeanWithList(List("1")), 0),
    (TestBeanWithVector(Vector()), 1),
    (TestBeanWithVector(Vector("1")), 0),
    (TestBeanWithArray(Array()), 1),
    (TestBeanWithArray(Array("1")), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
