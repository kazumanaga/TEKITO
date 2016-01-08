/**
  * Created by AT11-長岡 on 2016/01/08.
  * コンソールゲーム
  */
object framework {
  def waitInput(c: Char): Unit = {
    c match {
      case 'q' => /* 終了処理 */
      case _ => waitInput(readChar()) /* 他のキーだったら再び入力待ち */
    }

    waitInput(readChar)
  }
    def GameLoop(frame: Int): Unit = {
      waitInput(readChar)
    }
    def main(args: Array[String]) {
      GameLoop(0)
    }

}
