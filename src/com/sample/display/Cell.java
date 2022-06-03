package com.sample.display;

public class Cell {
  int state = 0; // 0: empty, 1: wall, 2: visited, 3: start, 4: goal, 5: path

  Cell(int state) {
    this.state = state;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getIcon() {
    switch (state) {
      case 0:
        return "　";
      case 1:
        return "鬱";
      case 2:
        return "・";
      case 3:
        return "Ｓ";
      case 4:
        return "Ｇ";
      case 5:
        return "Ｏ";
      default:
        return "　";
    }
  }
}
