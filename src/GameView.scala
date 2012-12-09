import java.awt.{Color, Graphics2D, Graphics}
import javax.swing.JPanel

object GameView
{
  val CELL_SIZE = 15
}

class GameView(game: GameOfLife, rows: Int, cols: Int) extends JPanel {

  import GameView._

  override def paintComponent(g: Graphics) {
    super.paintComponent(g)
    val g2 = g.asInstanceOf[Graphics2D]
    drawGrid(g2)
    drawGame(g2)
  }

  def drawGrid(g2: Graphics2D) {
    g2.setColor(Color.BLACK)
    g2.drawRect(0, 0, getWidth, getHeight)
    for ( x <- 0 until rows if x % 2 == 0) {
      g2.drawRect(x * CELL_SIZE, 0, CELL_SIZE, getHeight)
    }
    for ( y <- 0 until cols if y % 2 == 0) {
      g2.drawRect(0, y * CELL_SIZE, getWidth, CELL_SIZE)
    }

  }

  def drawGame(g2: Graphics2D) {
      g2.setColor(Color.BLACK)
      game.tick() foreach( x => drawCell(g2, x))
  }

  def drawCell(g2: Graphics2D, cell: (Int, Int))  {
    g2.fillRect(cell._1 * CELL_SIZE, cell._2 * CELL_SIZE, CELL_SIZE, CELL_SIZE)
  }

}
