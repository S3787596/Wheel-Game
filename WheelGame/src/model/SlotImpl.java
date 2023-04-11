package model;

import java.util.Objects;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot
{

	private int position;
	private Color color;
	private int number;

	public SlotImpl(int position, Color color, int number)
	{
		this.position = position;
		this.color = color;
		this.number = number;
	}

	@Override
	public int getPosition()
	{
		return position;
	}

	@Override
	public int getNumber()
	{
		return number;
	}

	@Override
	public Color getColor()
	{
		return color;
	}

	@Override
	public boolean equals(Slot slot)
	{

		
//		if (slot.getPosition() == this.getPosition() && 
//			slot.getColor() == this.getColor() && 
//			slot.getNumber() == this.getNumber())
//		{
//			return true;
//		}
//		return false;
//	}
		if (this == slot) {
            return true;
        }

        if (!(slot instanceof Slot) || slot == null) {
            return false;
        }

        if (this.color == null) {
            if (slot.getColor() != null) {
                return false;
            }
        } else if (!this.color.equals(slot.getColor())) {
            return false;
        }

        if (this.number != slot.getNumber()) {
            return false;
        }

        return true;
	}
	
	public int hashCode() {
        return Objects.hash(this.color, this.number); // Generate a hash code base on the color and number
    }

	public String toString()
	{
		return String.format("Position: %s, Color: %s, Number: %d", this.position, this.color.name().charAt(0) + this.color.name().substring(1).toLowerCase(), this.number);
	}

}
