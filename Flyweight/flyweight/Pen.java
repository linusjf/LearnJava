package flyweight;

public interface Pen 
{ 
  enum BrushSize {
    THIN, MEDIUM, THICK,
  }
  void setColor(String color);
  void draw(String content); 
}
