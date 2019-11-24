package com.javacodegeeks.patterns.mementopattern;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class Originator {
  CareTaker careTaker;
  private double xCoord;
  private double yCoord;
  private String lastUndoSavepoint;

  public Originator(double xCoord, double yCoord, CareTaker careTaker) {
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.careTaker = careTaker;
    createSavepoint("INITIAL");
  }

  public double getX() {
    return xCoord;
  }

  public double getY() {
    return yCoord;
  }

  @SuppressWarnings("checkstyle:hiddenfield")
  public void setX(double xCoord) {
    this.xCoord = xCoord;
  }

  @SuppressWarnings("checkstyle:hiddenfield")
  public void setY(double yCoord) {
    this.yCoord = yCoord;
  }

  public void createSavepoint(String savepointName) {
    careTaker.saveMemento(new Memento(this.xCoord, this.yCoord), savepointName);
    lastUndoSavepoint = savepointName;
  }

  public void undo() {
    setOriginatorState(lastUndoSavepoint);
  }

  public void undo(String savepointName) {
    setOriginatorState(savepointName);
  }

  public void undoAll() {
    setOriginatorState("INITIAL");
    careTaker.clearSavepoints();
  }

  private void setOriginatorState(String savepointName) {
    Memento mem = careTaker.getMemento(savepointName);
    this.xCoord = mem.getX();
    this.yCoord = mem.getY();
  }

  @Override
  public String toString() {
    return "X: " + xCoord + ", Y: " + yCoord;
  }
}
