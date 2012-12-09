
object Patterns {

  def toad(x: Int, y: Int) = {
    Set((x + 1, y + 1), (x + 1, y + 2), (x + 2, y + 3), (x + 3, y), (x + 4, y + 1), (x + 4, y + 2))
  }

  def blinker(x: Int, y: Int) = {
    Set((x, y + 1),(x + 1, y + 1), (x + 2, y + 1))
  }

  def block(x: Int, y: Int) = {
    Set((x + 1, y + 2),(x + 1, y + 1), (x + 2, y + 1), (x + 2, y + 2))
  }

  def glider(x: Int, y: Int) = {
    Set((0,2), (1,3),(2,1),(2,2),(2,3))
  }
}
