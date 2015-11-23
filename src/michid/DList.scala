package michid

/**
  * Alternative approach at the DList
  */
class DList[T](val xs: List[T] => List[T]) {
  def toList: List[T] =
    xs(Nil)

  def ++(d: DList[T]): DList[T] =
    new DList(x => xs(d.xs(x)))
}

object DList {
  def apply[T](xs: List[T]) =
    new DList[T](xs ++ _)

  def main(args: Array[String]) {
    val xs = DList(1::2::3::Nil)
    val ys = DList(4::5::6::Nil)
    val zs = DList(7::8::9::Nil)

    val all = xs ++ ys ++ zs
    val list = all.toList

    println(list)
  }
}
