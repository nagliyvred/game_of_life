import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.{Timer, JFrame, SwingUtilities}
import Patterns._

object Main extends App {

  val NUMBER_OF_CELLS = 25
  val W = NUMBER_OF_CELLS * GameView.CELL_SIZE
  val H = W

  val initialSet = glider(0, 0) ++ toad(10, 10) ++ blinker(15, 0) ++ block(20, 13)

  val game = new GameOfLife(initialSet)

  SwingUtilities.invokeLater(new Runnable {
    def run() {
      prepareFrame
    }
  })

  def prepareFrame {
    val frame = new JFrame()
    frame.setSize(W, H)
    frame.setResizable(false)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val view = new GameView(game, NUMBER_OF_CELLS, NUMBER_OF_CELLS)
    frame.add(view)
    frame.setVisible(true)
    new Timer(500, new ActionListener {
      def actionPerformed(e: ActionEvent) {
        view.repaint()
      }
    }).start()
  }
}
