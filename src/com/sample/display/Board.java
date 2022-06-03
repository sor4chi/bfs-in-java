package com.sample.display;

public class Board {
  public final int MAX_ROW = 37; // must be odd
  public final int MAX_COL = 37; // must be odd

  public Cell[][] cells;
  public int startX = 0;
  public int startY = 0;
  public int goalX = 0;
  public int goalY = 0;

  public Board() {
    cells = new Cell[MAX_ROW][MAX_COL];
    for (int i = 0; i < MAX_ROW; i++) {
      for (int j = 0; j < MAX_COL; j++) {
        cells[i][j] = new Cell(0);
      }
    }
    setWalls();
    setStartAndGoal();
    generateMaze();
  }

  public void display() {
    for (int i = 0; i < MAX_ROW; i++) {
      for (int j = 0; j < MAX_COL; j++) {
        System.out.print(cells[i][j].getIcon());
      }
      System.out.println();
    }
  }

  public void setWalls() {
    for (int i = 0; i < MAX_ROW; i++) {
      for (int j = 0; j < MAX_COL; j++) {
        if (i == 0 || i == MAX_ROW - 1 || j == 0 || j == MAX_COL - 1) {
          cells[i][j].setState(1);
        }
      }
    }
  }

  public void setStartAndGoal() {
    while ((startX * startY * goalX * goalY) % 2 == 0 || startX == goalX || startY == goalY) {
      startX = (int) (Math.random() * MAX_ROW);
      startY = (int) (Math.random() * MAX_COL);
      goalX = (int) (Math.random() * MAX_ROW);
      goalY = (int) (Math.random() * MAX_COL);
    }
    cells[startX][startY].setState(3);
    cells[goalX][goalY].setState(4);
  }

  public void generateMaze() {
    for (int i = 1; i < MAX_ROW - 1; i++) {
      for (int j = 1; j < MAX_COL - 1; j++) {
        if (i % 2 == 0 && j % 2 == 0) {
          boolean connectX = (boolean) (Math.random() < 0.5);
          boolean connectPositive = (boolean) (Math.random() < 0.5);
          cells[i][j].setState(1);
          if (connectX) {
            if (connectPositive) {
              cells[i][j + 1].setState(1);
            } else {
              cells[i][j - 1].setState(1);
            }
          } else {
            if (connectPositive) {
              cells[i + 1][j].setState(1);
            } else {
              cells[i - 1][j].setState(1);
            }
          }
        }
      }
    }
  }
}
