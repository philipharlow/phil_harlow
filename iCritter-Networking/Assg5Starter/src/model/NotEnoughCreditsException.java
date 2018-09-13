package model;

public class NotEnoughCreditsException extends RuntimeException	{
	public NotEnoughCreditsException(int x, int y) {
		super("Attempted to remove " + x + " credits, but only had " + y + " credits.");
	}
}
