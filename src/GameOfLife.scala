
class GameOfLife(set: Set[(Int,Int)]) {

  def neighbours(point: (Int, Int)) : Set[(Int, Int)] = {
    Set( (point._1 - 1 , point._2 - 1), (point._1 - 1 , point._2),  (point._1 - 1 ,point._2 + 1),
         (point._1 ,     point._2 - 1),                             (point._1,     point._2 + 1),
         (point._1 + 1 , point._2 - 1), (point._1 + 1 , point._2),  (point._1  +1 ,point._2 + 1))
  }

  def aliveNeighboursCount(point: (Int,Int)) : Int = {
    neighbours(point).intersect(set).size
  }

  def kill(survivingRule: ((Int, Int)) => Boolean) = {
    set filter { (x) => survivingRule(x) }
  }

  def reproduce(reproductionRule: ((Int, Int)) => Boolean) = {
    set flatMap { x => neighbours(x) } filter { x => reproductionRule(x) }
  }

  def tick() = {

    def reproducingRule (p: (Int, Int)) : Boolean = {
      aliveNeighboursCount(p) == 3
    }

    def survivingRule (p: (Int, Int)) : Boolean = {
      val aliveNeighbours = aliveNeighboursCount(p)
      aliveNeighbours == 2 || aliveNeighbours == 3
    }

    val survivors = kill(survivingRule)
    val newborn = reproduce(reproducingRule)
    survivors ++ newborn
  }

}
