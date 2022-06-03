package com.sample.process;

import com.sample.display.Board;
import com.sample.utils.Solver;

public class Maze {
  public static void main(String[] args) {
    Board board = new Board();

    Solver.solve(board);

    board.display();
  }
}
