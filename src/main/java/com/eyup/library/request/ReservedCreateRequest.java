package com.eyup.library.request;

import lombok.Data;

@Data
public class ReservedCreateRequest {

	private Long userId;
	private Long bookId;
}
