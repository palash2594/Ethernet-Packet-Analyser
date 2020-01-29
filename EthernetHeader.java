public class EthernetHeader {

    /**
     * to append one zero if the length of the hex data is one to make it two.
     * @param mac the hex value
     * @return hex value
     */
    public String appendZeroes(String mac) {
        if (mac.length() < 2) {
            mac = "0" + mac;
        }
        return mac;
    }

    public void test(int num) {
        System.out.println(num);
    }

    /**
     * to get the destination address.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @param data3 third byte value in decimal.
     * @param data4 fourth byte value in decimal.
     * @param data5 fifth byte value in decimal.
     * @param data6 sixth byte value in decimal.
     */
    public void getDestination(byte data1, byte data2, byte data3, byte data4, byte data5, byte data6) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);
        String hexData3 = String.format("%02x", data3);
        String hexData4 = String.format("%02x", data4);
        String hexData5 = String.format("%02x", data5);
        String hexData6 = String.format("%02x", data6);

        String macAddress = "" + hexData1 + ":" + hexData2 + ":" +
                hexData3 + ":" + hexData4 + ":" + hexData5 + ":" + hexData6;

        String toDisplay = "ETHER:\tDestination = " + macAddress + ",";
        System.out.println(toDisplay);
    }

    public void test(byte data1, byte data2) {

    }

    /**
     * to get the source address.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @param data3 third byte value in decimal.
     * @param data4 fourth byte value in decimal.
     * @param data5 fifth byte value in decimal.
     * @param data6 sixth byte value in decimal.
     */
    public void getSource(byte data1, byte data2, byte data3, byte data4, byte data5, byte data6) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);
        String hexData3 = String.format("%02x", data3);
        String hexData4 = String.format("%02x", data4);
        String hexData5 = String.format("%02x", data5);
        String hexData6 = String.format("%02x", data6);

        String macAddress = "" + hexData1 + ":" + hexData2 + ":" +
                hexData3 + ":" + hexData4 + ":" + hexData5 + ":" + hexData6;

        String toDisplay = "ETHER:\tSource = " + macAddress + ",";
        System.out.println(toDisplay);
    }

    /**
     * to get the ethernet type.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     */
    public boolean getEtherType(byte data1, byte data2) {
        int decimalData1 = (data1 & 0xFF) << 8;
        int decimalData2 = (data2 & 0xFF);
        int checkSum = decimalData1 | decimalData2;
        String etherType = String.format("%04x", checkSum);
        if (etherType.equals("0800")) {
            String toDisplay = "ETHER:\tEthertype = " + etherType + " (IP)";
            System.out.println(toDisplay);
            return true;
        }
        else {
            System.out.println("IP header not present.");
            return false;
        }
    }

}
