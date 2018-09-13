package model;

public class NoInterestsException extends RuntimeException	{
	public NoInterestsException() {
		super("There are no interests to use.");
	}
}
