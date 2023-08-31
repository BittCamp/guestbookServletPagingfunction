package guestbook.bean;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestbookDTO {
	private int seq;
	private String name;
	private String email;
	private String homepage;
	private String subject;
	private String content;
	private Date logtime;
	
}
