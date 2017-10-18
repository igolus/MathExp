package com.jenetics.mathexp.util;

public class ComplexEvalException extends Exception {

	public enum ComplexEvalExceptionType {
		
		MISSING_OPEN_BRACKET("MISSING_OPEN_BRACKET"), 
		MISSING_CLOSING_BRACKET("MISSING_OPEN_BRACKET"),
		PREVIOUS_SHOULD_BE_COMPLEX("PREVIOUS_SHOULD_BE_COMPLEX"),
		MISSING_COMMA("MISSING_COMMA"),
		WRONG_NUMERIC_FORMAT("WRONG_NUMERIC_FORMAT"),
		INVALID_CHAR("INVALID_CHAR");
		
		private final String text;

	    /**
	     * @param text
	     */
	    private ComplexEvalExceptionType(final String text) {
	        this.text = text;
	    }

	    /* (non-Javadoc)
	     * @see java.lang.Enum#toString()
	     */
	    @Override
	    public String toString() {
	        return text;
	    }
	}

	private ComplexEvalExceptionType complexEvalExceptionType;

	

	public ComplexEvalExceptionType getComplexEvalExceptionType() {
		return complexEvalExceptionType;
	}

	public ComplexEvalException(ComplexEvalExceptionType complexEvalExceptionType) {
		super();
		this.complexEvalExceptionType = complexEvalExceptionType;
	}

	public ComplexEvalException(ComplexEvalExceptionType complexEvalExceptionType, String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(complexEvalExceptionType.toString() + "/" + message, cause, enableSuppression, writableStackTrace);
		this.complexEvalExceptionType = complexEvalExceptionType;
	}

	public ComplexEvalException(ComplexEvalExceptionType complexEvalExceptionType, String message, Throwable cause) {
		super(complexEvalExceptionType.toString() + "/" + message, cause);
		this.complexEvalExceptionType = complexEvalExceptionType;
	}

	public ComplexEvalException(ComplexEvalExceptionType complexEvalExceptionType, String message) {
		super(complexEvalExceptionType.toString() + "/" + message);
		this.complexEvalExceptionType = complexEvalExceptionType;
	}

	public ComplexEvalException(ComplexEvalExceptionType complexEvalExceptionType, Throwable cause) {
		super(cause);
		this.complexEvalExceptionType = complexEvalExceptionType;
	}

}
