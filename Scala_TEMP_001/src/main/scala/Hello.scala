/**
  * Created by AT11-長岡 on 2015/12/28.
  */

object Hello {
  def main(args: Array[String])
  {
    println("HelloWorld");

    var i,j = 0

    i = i + 3
    println(i)

    // マップ生成
    for (i <- 0 to 10)
      {
        for(j <- 0 to 25)
          {
            print("#")
          }
        print("\n")
      }
  }
}
