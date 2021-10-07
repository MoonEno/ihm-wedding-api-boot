package kr.co.ihm.wedding.api.wd.guest.model;

import lombok.Data;

@Data
public class GuestDTO {
    private int guestSrno;
    private int userSrno;
    private int guestGrpNo;
    private String guestName;
    private int guestAge;
    private String guestPhone;
    private String guestFile;
    private String userName;
    private String userRelate;
    private String userBankName;
    private String userBankAccount;
}
