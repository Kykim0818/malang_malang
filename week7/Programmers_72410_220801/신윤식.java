class Solution {
  public String solution(String new_id) {
    String checkId = new_id;
    checkId = firstCondition(checkId);
    checkId = secondCondtion(checkId);
    checkId = thirdCondition(checkId);
    checkId = fourthCondition(checkId);
    checkId = fifthCondition(checkId);
    checkId = sixthCondition(checkId);
    checkId = seventhCondition(checkId);
    return checkId;
  }

  private String seventhCondition(String checkId) {
    StringBuilder checkedId = new StringBuilder(checkId);
    if(checkId.length() <= 2){
      while(checkedId.length() < 3){
        checkedId.append(checkId.charAt(checkId.length() - 1));
      }
    }
    return checkedId.toString();
  }

  private String sixthCondition(String checkId) {
    StringBuilder checkedId = new StringBuilder();
    for(int i=0; i<checkId.length(); i++){
      if(i >= 15) continue;
      if(i == 14 && checkId.charAt(i) == '.') continue;
      checkedId.append(checkId.charAt(i));
    }
    return checkedId.toString();
  }

  private String fifthCondition(String checkId){
    return checkId.length() == 0 ? "a" : checkId;
  }


  private String fourthCondition(String checkId) {
    StringBuilder checkedId = new StringBuilder();
    for(int i=0; i<checkId.length(); i++){
      if(i == 0 && checkId.charAt(0) == '.') continue;
      else if (i == checkId.length() - 1 && checkId.charAt(checkId.length() - 1) == '.') continue;
      checkedId.append(checkId.charAt(i));
    }
    return checkedId.toString();
  }

  private String thirdCondition(String checkId) {
    while(checkId.indexOf("..") > -1) {
      checkId = checkId.replace("..", ".");
    }
    return checkId;
  }

  private String secondCondtion(String checkId) {
    StringBuilder checkedId = new StringBuilder();
    for(int i=0; i<checkId.length(); i++){
      char ch = checkId.charAt(i);
      if(ch == '-' || ch == '_' || ch == '.' || (97 <= Integer.valueOf(ch) && Integer.valueOf(ch) <= 122)) checkedId.append(ch);
      if(Integer.valueOf(ch) >= 48 && Integer.valueOf(ch) <= 57) checkedId.append(ch);
    }
    return checkedId.toString();
  }

  private String firstCondition(String checkId) {
    return checkId.toLowerCase();
  }
}