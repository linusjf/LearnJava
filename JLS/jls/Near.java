package jls;

@SuppressWarnings("all")
class Near {
  int it;

  int getItNear() {
    return it;
  }

  int chooseNear(int i) {
    switch (i) {
      case 0:
        return 0;
      case 1:
        return 1;
      case 2:
        return 2;
      default:
        return -1;
    }
  }
}

@SuppressWarnings("all")
class Far extends Near {
  int getItFar() {
    return super.getItNear();
  }

  int chooseFar(int i) {
    switch (i) {
      case -100:
        return -1;
      case 0:
        return 0;
      case 100:
        return 1;
      default:
        return -1;
    }
  }
}
