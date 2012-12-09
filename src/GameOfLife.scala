
class GameOfLife(set: Set[(Int,Int)]) {

  def neighbours(point: (Int, Int)) : Set[(Int, Int)] = {
    Set( (point._1 - 1 , point._2 - 1), (point._1 - 1 , point._2),  (point._1 - 1 ,point._2 + 1),
         (point._1 ,     point._2 - 1),                             (point._1,     point._2 + 1),
         (point._1 + 1 , point._2 - 1), (point._1 + 1 , point._2),  (point._1  +1 ,point._2 + 1))
  }

  def aliveNeighboursCount(point: (Int,Int)) : Int = {
    neighbours(point).intersect(set).size
  }

  def kill(killingRule: ((Int, Int)) => Boolean) = {
    set filter { (x) => killingRule(x) }
  }

  def reproduce(reincarnationRule: ((Int, Int)) => Boolean) = {
    set flatMap { x => neighbours(x) } filter { x => reincarnationRule(x) }
  }

  def tick() = {

    def reproducingRule (p: (Int, Int)) : Boolean = {
      aliveNeighboursCount(p) == 3
    }

    def killingRule (p: (Int, Int)) : Boolean = {
      val aliveNeighbours = aliveNeighboursCount(p)
      aliveNeighbours == 2 || aliveNeighbours == 3
    }

    val survivors = kill(killingRule)
    val newborn = reproduce(reproducingRule)
    survivors ++ newborn
  }

}
