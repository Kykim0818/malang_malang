class Solution {

  public int solution(int n, int k) {
    String convertedNumber = convertNtoK(n, k);
    int result = getAnswer(convertedNumber);

    return result;
  }

  private int getAnswer(String convertedNumber) {
    int ret = 0;
    StringBuilder P = new StringBuilder();
    for(int i=0; i<=convertedNumber.length(); i++){
      if(i < convertedNumber.length() && convertedNumber.charAt(i) != '0') P.append(convertedNumber.charAt(i));
      else{
        if(P.length() != 0){
          if(isPrimeNumber(P)) ret++;
          P.setLength(0);
        }
      }
    }
    return ret;
  }

  private boolean isPrimeNumber(StringBuilder p) {
    boolean check = true;
    long decimal = Long.parseLong(p.toString());

    if(decimal == 1) return false;
    for(int i=2; i*i<=decimal; i++){
      if(decimal % i == 0) return false;
    }
    return check;
  }

  private static String convertNtoK(int n, int k) {
    StringBuilder ret = new StringBuilder();
    while(n > 0){
      ret.append(n % k);
      n = n / k;
    }
    ret.reverse();
    return ret.toString();
  }
}