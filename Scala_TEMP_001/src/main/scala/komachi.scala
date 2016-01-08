/**
  * Created by AT11-長岡 on 2016/01/08.
  */
//
// komachi.scala : パズル「小町算」の解法
//
//
object komachi {
  // 式の計算
  def calcExpr(a: Int, xs: List[String]): Int =
    xs match {
      case Nil => a
      case _::Nil => throw new Exception("komachi.calcExpr error")
      case op::n::ys => op match {
        case "+" => calcExpr(a + n.toInt, ys)
        case "-" => calcExpr(a - n.toInt, ys)
        case _ => throw new Exception("komachi.calcExpr op error")
      }
    }

  // 式の表示
  def printExpr(xs: List[String]) = {
    for (x <- xs) print(x)
    println("=100")
  }

  // 式の生成
  def makeExpr(n: Int, expr: List[String]): Unit = {
    if (n == 10) {
      val xs = expr.reverse
      if (calcExpr(xs.head.toInt, xs.tail) == 100) printExpr(xs)
    } else {
      val s = n.toString
      makeExpr(n + 1, s :: "+" :: expr)
      makeExpr(n + 1, s :: "-" :: expr)
      makeExpr(n + 1, expr.head + s :: expr.tail)
    }
  }

  def main(args: Array[String]) {
    makeExpr(2, List("1"))
  }
}
