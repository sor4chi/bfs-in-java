package com.sample.utils;

import com.sample.display.Board;

public class Solver {
  public static void solve(Board board) {
    int LONGEST_DISTANCE = board.MAX_ROW * board.MAX_COL;
    Queue<int[]> queue = new Queue<>(LONGEST_DISTANCE);
    int[][] visited = new int[board.MAX_ROW][board.MAX_COL];
    for (int i = 0; i < board.MAX_ROW; i++) {
      for (int j = 0; j < board.MAX_COL; j++) {
        visited[i][j] = LONGEST_DISTANCE;
      }
    }
    int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    int[] start = { board.startX, board.startY };
    int[] goal = { board.goalX, board.goalY };
    queue.enqueue(start);
    visited[start[0]][start[1]] = 0;
    while (!queue.isEmpty()) {
      int[] current = queue.dequeue();
      if (current[0] == goal[0] && current[1] == goal[1]) {
        board.cells[current[0]][current[1]].setState(4);
        break;
      }
      for (int i = 0; i < directions.length; i++) {
        int[] direction = directions[i];
        int[] next = { current[0] + direction[0], current[1] + direction[1] };
        if (next[0] < 0 || next[0] >= board.MAX_ROW || next[1] < 0 || next[1] >= board.MAX_COL) {
          continue;
        }
        if (visited[next[0]][next[1]] < LONGEST_DISTANCE) {
          continue;
        }
        if (board.cells[next[0]][next[1]].getState() == 1) {
          continue;
        }
        queue.enqueue(next);
        visited[next[0]][next[1]] = visited[current[0]][current[1]] + 1;
        board.cells[next[0]][next[1]].setState(2);
      }
    }

    int currentX = board.goalX;
    int currentY = board.goalY;
    int currentDistance = visited[currentX][currentY];
    while (currentDistance > 0) {
      for (int i = 0; i < 4; i++) {
        int[] next = { currentX + directions[i][0], currentY + directions[i][1] };
        if (next[0] < 0 || next[0] >= board.MAX_ROW || next[1] < 0 || next[1] >= board.MAX_COL) {
          continue;
        }
        if (visited[next[0]][next[1]] != currentDistance - 1) {
          continue;
        }
        board.cells[next[0]][next[1]].setState(5);
        currentX = next[0];
        currentY = next[1];
        currentDistance--;
      }
    }
    board.cells[board.startX][board.startY].setState(3);
  }
}
