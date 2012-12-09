import org.scalatest.FlatSpec

class GameOfLifeTest extends FlatSpec {



  def dummy(p: (Int, Int)) : Boolean = { true }

  it should "create a world" in {

    val world = new GameOfLife(Set())


    assert(Set() == world.kill(dummy))
  }

  it should "create a world with one alive cell" in {

    val world = new GameOfLife(Set((1,2)))

    assert(Set((1,2)) == world.kill(dummy))
  }

  it should "keep the same cells if they are stable" in {

    val stableSet = Set((1,1), (1,2), (2,1),(2,2))
    val world = new GameOfLife(stableSet)

    assert(stableSet == world.kill(dummy))

  }

  it should "return neighbours" in {

    val stableSet = Set((1,1), (1,2), (2,1),(2,2))
    val world = new GameOfLife(stableSet)
    assert(Set((1,0), (1,2), (2,0), (2,1), (0,0), (2,2), (0,1), (0,2)) == world.neighbours((1,1)))

  }

  it should "number of live cells is correct" in {
    val stableSet = Set((1,1), (1,2), (2,1),(2,2))
    val world = new GameOfLife(stableSet)
    assert(3 == world.aliveNeighboursCount((1,1)))
  }

  it should "kill cells during the kill action" in {
    val blinker = Set((0,1),(1,1), (2,1))
    val world = new GameOfLife((blinker))


    def rule (p: (Int, Int)) : Boolean = {
      val aliveNbrs = world.aliveNeighboursCount(p)
      aliveNbrs == 2 || aliveNbrs == 3
    }

    assert(world.kill(rule)  == Set((1,1)))
  }

  it should "create cells after reproduce()" in {
    val blinker = Set((0,1),(1,1), (2,1))
    val world = new GameOfLife((blinker))


    def rule (p: (Int, Int)) : Boolean = {
      val aliveNbrs = world.aliveNeighboursCount(p)
      aliveNbrs == 3
    }

    assert(world.reproduce(rule)  == Set((1,0), (1,2)))
  }

  it should "update world for blinker" in {
    val blinker = Set((0,1),(1,1), (2,1))
    val world = new GameOfLife((blinker))

    assert(world.tick  == Set((1,0), (1,1), (1,2)))
  }

  it should "update world for block" in {
    val block = Set((1,2),(1,1), (2,1), (2,2))
    val world = new GameOfLife((block))

    assert(world.tick  == block)
  }

  it should "show us the toad!" in {
    val toad = Set((1,1),(1,2), (2,3), (3,0), (4,1), (4,2))
    val world = new GameOfLife((toad))

    assert(world.tick  == Set((1,2), (2,1), (2,2),(3,1), (3,2), (4,1)))
  }
}
