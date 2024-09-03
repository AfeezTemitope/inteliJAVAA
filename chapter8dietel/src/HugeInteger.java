public class HugeInteger {
    private static final int MAX_DIGITS = 40;
    private int[] digits = new int[MAX_DIGITS];

    public HugeInteger(String value) {
        int length = value.length();
        if (length > MAX_DIGITS) throw new IllegalArgumentException("Number is too large");

        for (int i = 0; i < MAX_DIGITS; i++) {
            digits[i] = 0;
        }
        for (int i = 0; i < length; i++) {
            digits[MAX_DIGITS - length + i] = value.charAt(i) - '0';
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (int digit : digits) {
            if (digit != 0 || !leadingZero) {
                sb.append(digit);
                leadingZero = false;
            }
        }
        return sb.length() > 0 ? sb.toString() : "0";
    }
}
