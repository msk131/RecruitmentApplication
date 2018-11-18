package com.app.recruitment.exception;

/**
 * @author maNi
 *
 */
public class RecruitmentException extends RuntimeException {
	
	private static final long serialVersionUID = 4586010631395728292L;
	public RecruitmentException(String id) {
        super(String.format(id));
    }

}
