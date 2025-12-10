package com.ikeda.presentation.form;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberForm {
	private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String zip;

    private String address;

    private String phone;

    private String password;

    private String status;  // 借りている総数 or 状態

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    // getter / setter 略
 // 確認用（DBには保存しない）
    private String passwordConfirm;
}
