public class UDPHeader {

    /**
     * to get the source port.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public String getSourcePort(byte data1, byte data2) {
        int decimalData1 = (data1 & 0xFF) << 8;
        int decimalData2 = (data2 & 0xFF);
        int sourcePort = decimalData1 | decimalData2;
        String toDisplay = "Source port = " + sourcePort;
        System.out.println("UDP\t\t" + toDisplay);
        return "" + sourcePort;
    }

    /**
     * to get the destination port.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public String getDestinationPort(byte data1, byte data2) {
        int decimalData1 = (data1 & 0xFF) << 8;
        int decimalData2 = (data2 & 0xFF);
        int destinationPort = decimalData1 | decimalData2;
        String toDisplay = "UDP:\tDestination port = " + destinationPort;
        System.out.println(toDisplay);
        return "" + destinationPort;
    }

    /**
     * to get the length in bytes of the UDP header
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public int getLength(int data1, int data2) {
        int decimalData1 = (data1 & 0xFF) << 8;
        int decimalData2 = (data2 & 0xFF);
        int length = decimalData1 | decimalData2;
        String toDisplay = "UDP:\tLength = " + length;
        System.out.println(toDisplay);
        return length;
    }

    /**
     * to the get checksum.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public String getChecksum(int data1, int data2) {
        int decimalData1 = (data1 & 0xFF) << 8;
        int decimalData2 = (data2 & 0xFF);
        int checkSum = decimalData1 | decimalData2;
        String hexData = String.format("%04x", checkSum);

        String checksumHex = hexData;
        String toDisplay = "UDP:\tChecksum = 0x" + checksumHex;
        System.out.println(toDisplay);
        return checksumHex;
    }
}
