package com.example.thread.callable;

import java.util.concurrent.Callable;

public class CallableAdder implements Callable<Integer> {
  Integer operand1;
  Integer operand2;

  CallableAdder(Integer operand1, Integer operand2) {
    this.operand1 = operand1;
    this.operand2 = operand2;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public Integer call() throws Exception {
    System.out.println(
        Thread.currentThread().getName()
            + " says : partial Sum for "
            + operand1
            + " and "
            + operand2
            + " is "
            + (operand1 + operand2));
    return operand1 + operand2;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "CallableAdder(operand1=" + this.operand1 + ", operand2=" + this.operand2 + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof CallableAdder)) return false;
    CallableAdder other = (CallableAdder) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$operand1 = this.operand1;
    Object other$operand1 = other.operand1;
    if (this$operand1 == null ? other$operand1 != null : !this$operand1.equals(other$operand1))
      return false;
    Object this$operand2 = this.operand2;
    Object other$operand2 = other.operand2;
    if (this$operand2 == null ? other$operand2 != null : !this$operand2.equals(other$operand2))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof CallableAdder;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $operand1 = this.operand1;
    result = result * PRIME + ($operand1 == null ? 43 : $operand1.hashCode());
    Object $operand2 = this.operand2;
    result = result * PRIME + ($operand2 == null ? 43 : $operand2.hashCode());
    return result;
  }
}
