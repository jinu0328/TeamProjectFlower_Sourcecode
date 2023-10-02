package org.flower.commons.validators;

public interface CellPhoneValidator {
    default boolean checkCellPhoneNumber(String cellphone){
        // 문자열에서 숫자를 제외한 다른 문자를 모두 제거하는 코드
        cellphone = cellphone.replaceAll("\\D", "");
        String pattern = "^01[016]\\d{3,4}\\d{4}$";
        return cellphone.matches(pattern);
    }
}
