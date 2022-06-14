package model;

import java.awt.Color;

/**
 * A class that represents the plashape.
 */
public class PlusShape implements IShape {
  private final String id;
  private Color color;
  private float height;
  private float width;
  private Position centerPosition;
  private IShape vertical_rect;
  private IShape horizontal_rect;

  /**
   * Public constructor for plusshape.
   * @param id name
   * @param c color
   * @param height height
   * @param width width
   * @param centerPosition position
   */
  public PlusShape(String id, Color c, float height, float width, Position centerPosition) {
    if (id == null || c == null || c == null || centerPosition == null) {
      throw new IllegalArgumentException("The input is null");
    }

    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("The input is invalid");
    }

    this.id = id;
    ShapeType type = ShapeType.PlusShape;
    this.color = c;
    this.height = height;
    this.width = width;
    this.centerPosition = centerPosition;
    this.vertical_rect = new Rectangle("vertical_" + this.getID(),
            new Position(centerPosition.getX() + this.width / 4,centerPosition.getY())
            , color, height, width / 2);
    this.horizontal_rect = new Rectangle("horizontal" + this.getID(),
            new Position(centerPosition.getX(),centerPosition.getY()
                    + this.height / 4),color,height / 2, width);
  }

  @Override
  public void setHeight(float height) {
    this.height = height;
    this.vertical_rect = new Rectangle("vertical",
            centerPosition, color, height, width / 2);
    this.horizontal_rect = new Rectangle("horizontal",
            centerPosition,color,height / 2, width);

  }

  @Override
  public void setWidth(float width) {
    this.width = width;
    this.vertical_rect = new Rectangle("vertical",
            centerPosition, color, height, width / 2);
    this.horizontal_rect = new Rectangle("horizontal",
            centerPosition,color,height / 2, width);
  }

  @Override
  public void setSize(float height, float width) {
    this.height = height;
    this.width = width;
    this.vertical_rect = new Rectangle("vertical",
            centerPosition, color, height, width / 2);
    this.horizontal_rect = new Rectangle("horizontal",
            centerPosition,color,height / 2, width);
  }

  @Override
  public void setColorRGB(int r, int g, int b) {
    this.color = new Color(r,g,b);
    this.vertical_rect = new Rectangle("vertical",
            centerPosition, color, height, width / 2);
    this.horizontal_rect = new Rectangle("horizontal",
            centerPosition,color,height / 2, width);
  }

  @Override
  public void setColor(Color newColor) {
    this.color = newColor;
    this.vertical_rect = new Rectangle("vertical",
            centerPosition, color, height, width / 2);
    this.horizontal_rect = new Rectangle("horizontal",
            centerPosition,color,height / 2, width);

  }

  @Override
  public float getHeight() {
    return this.height;
  }

  @Override
  public float getWidth() {
    return this.width;
  }

  @Override
  public void setPosition(Position newPosition) {
    this.centerPosition = newPosition;
    this.vertical_rect = new Rectangle("vertical"
            , centerPosition, color, height, width / 2);
    this.horizontal_rect = new Rectangle("horizontal"
            ,centerPosition,color,height / 2, width);

  }

  @Override
  public void setPosition(float x, float y) {
    this.centerPosition = new Position(x,y);
    this.vertical_rect = new Rectangle("vertical"
            , centerPosition, color, height, width / 2);
    this.horizontal_rect = new Rectangle("horizontal"
            ,centerPosition,color,height / 2, width);

  }

  @Override
  public Position getPosition() {
    return this.centerPosition;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String getID() {
    return this.id;
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.PlusShape;
  }

  @Override
  public String createSVGString(int tempo, boolean loop) {
    throw new UnsupportedOperationException("There is no svg expression for PlusSign");
  }

  @Override
  public IShape getVerticalRectangle() {
    return this.vertical_rect;
  }

  @Override
  public IShape getHorizontalRectangle() {
    return this.horizontal_rect;
  }

  @Override
  public String toString() {
    return this.getPosition().toString()
            + " " + Float.toString(this.getWidth())
            + " " + Float.toString(this.getHeight())
            + " " + Integer.toString(this.getColor().getRed())
            + " " + Integer.toString(this.getColor().getGreen())
            + " " + Integer.toString(this.getColor().getBlue());
  }


}


