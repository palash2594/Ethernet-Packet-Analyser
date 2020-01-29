public class IPHeader {

    /**
     * to append one zero if the length of the hex data is one to make it two.
     * @param mac the hex value
     * @return hex value
     */
    public String appendZeroesMac(String mac) {
        if (mac.length() < 2) {
            mac = "0" + mac;
        }
        return mac;
    }

    /**
     * to append zeroes to make the length as a multiple of eight.
     * @param binary the byte value
     * @return
     */
    public String appendZeroes(String binary) {
        int totalBytes = binary.length() / 9;
        if (binary.length() < 8 * (totalBytes + 1)) {
            while (binary.length() < 8 * (totalBytes + 1)) {
                binary = "0" + binary;
            }
        }
        return binary;
    }

    /**
     * to get the version of the IP.
     * @param data byte value in decimal
     * @return
     */
    public int getVersion(byte data) {
        int ipVersion = (byte) ((data & 0xFF) >>> 4);
        String toDisplay = "IP:\t\tVersion = " + ipVersion;
        System.out.println(toDisplay);
        return ipVersion;
    }

    /**
     * to get the header length
     * @param data the byte value in decimal
     * @return
     */
    public int getIHL(byte data) {
        int headerLength = (data & 0x0F) * 4;
        String toDisplay = "IP:\t\tHeader length = " + headerLength + " bytes";
        System.out.println(toDisplay);
        return headerLength;
    }

    /**
     * to get the header length
     * @param data the byte value in decimal
     * @return
     */
    public int getIHL1(byte data) {

        int headerLength = (data & 0x0F);
        System.out.println("header length " + headerLength);


//        byte b1 = (byte) -64;
//        String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
//        System.out.println("b1 " + s1);

        System.out.println(data & 0xFF); // 0101 1100
        int bitLeftShiftVal = (byte) ((data & 0xFF) << 4);
        System.out.println(bitLeftShiftVal); // 1010000
        int length = (byte) ((bitLeftShiftVal & 0xFF) >> 4);
        System.out.println(length);
        String binary = Integer.toBinaryString(data);
        binary = appendZeroes(binary);
        String ihl = binary.substring(4);
        int ihlInBytes = Integer.parseInt(ihl, 2) * 4;
        String toDisplay = "IP:\t\tHeader length = " + ihlInBytes + " bytes";
        System.out.println(toDisplay);
        return ihlInBytes;
    }

    /**
     * to get the type of service.
     * @param data the byte value in decimal.
     * @return
     */
    public String[] getTypeOfService(byte data) {
        String[] toDisplay = new String[5];
        toDisplay[0] = "Type of service = 0x" + String.format("%02X", data);
        int precedence = (data & 0xE0) >> 5;
        toDisplay[1] = "\txxx. .... = " + precedence;
        toDisplay[2] = "\t...";
        if (((data >>> 4) & 1) != 1) {
            toDisplay[2] += "0 .... = normal delay";
        } else {
            toDisplay[2] += "1 .... = low delay";
        }
        toDisplay[3] = "\t.... ";
        if (((data >>> 3) & 1) != 1) {
            toDisplay[3] += "0... = normal throughput";
        } else {
            toDisplay[3] += "1... = high throughput";
        }
        toDisplay[4] = "\t.... .";
        if (((data >>> 2) & 1) != 1) {
            toDisplay[4] += "0.. = normal reliability";
        } else {
            toDisplay[4] += "1.. = high reliability";
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("IP:\t\t" + toDisplay[i]);
        }

        return toDisplay;
    }

    /**
     * to get the total length of the datagram in bytes.
     * @param data1 first byte in decimal.
     * @param data2 second byte in decimal.
     * @return
     */
    public int getTotalLength(byte data1, byte data2) {

        String hex1 = String.format("%02X", data1);
        String hex2 = String.format("%02X", data2);

        String totalLengthHex = hex1 + hex2;
        int totalLenthDecimal = Integer.parseInt(totalLengthHex, 16);
        String toDisplay = "IP:\t\tTotal length = " + totalLenthDecimal + " bytes";
        System.out.println(toDisplay);
        return totalLenthDecimal;
    }

    /**
     * to get the identification value.
     * @param data1 first byte in decimal.
     * @param data2 second byte in decimal.
     * @return
     */
    public int getIdentification(byte data1, byte data2) {
        String hex1 = String.format("%02X", data1);
        String hex2 = String.format("%02X", data2);
        String identificationHex = hex1 + hex2;
        int identificationToDecimal = Integer.parseInt(identificationHex, 16);
        String toDisplay = "IP:\t\tIdentification = " + identificationToDecimal;
        System.out.println(toDisplay);
        return identificationToDecimal;
    }

    public String getFlag(byte data) {
        // take first 3 bits
        String[] toDisplay = new String[3];
        int flagVal = (data & 0xff) >> 5;
        String hexData = String.format("%02x", flagVal);
        toDisplay[0] = "IP:\t\tFlags = 0x" + hexData;
        toDisplay[1] = "IP:\t\t\t.";
        if (((flagVal >> 1) & 1) != 1) {
            toDisplay[1] += "0.. .... = OK to fragment";
        } else {
            toDisplay[1] += "1.. .... = Not fragment";
        }
        toDisplay[2] = "IP:\t\t\t..";
        if ((flagVal & 1) != 1) {
            toDisplay[2] += "0. .... = last fragment";
        } else {
            toDisplay[2] += "1. .... = more fragments";
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(toDisplay[i]);
        }

        return "IP:\t\t0x" + flagVal;
    }

    /**
     * to get the control flags.
     * @param data byte value in decimal.
     * @return
     */
    public String getFlag1(byte data) {
        // take first 3 bits
        String[] toDisplay = new String[3];
        int flagVal = (data & 0xff);
        String binary = Integer.toBinaryString(flagVal);
        binary = appendZeroes(binary).substring(0, 3);
        int flag = Integer.parseInt(binary, 2);
        String flagInHex = appendZeroesMac(Integer.toHexString(flag));
        toDisplay[0] = "IP:\t\tFlags = 0x" + flagInHex;
        toDisplay[1] = "IP:\t\t\t." + binary.charAt(1) + ".. .... = ";
        if (binary.charAt(1) == '0') {
            toDisplay[1] += "OK to fragment";
        } else {
            toDisplay[1] += "Not fragment";
        }
        toDisplay[2] = "IP:\t\t\t.." + binary.charAt(2) + ". .... = ";
        if (binary.charAt(2) == '0') {
            toDisplay[2] += "last fragment";
        } else {
            toDisplay[2] += "more fragments";
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(toDisplay[i]);
        }

        return "IP:\t\t0x" + flagInHex;
    }

    /**
     * to get the fragment offset
     * @param data1
     * @param data2
     * @return
     */
    public int getFragmentOffset(byte data1, byte data2) {
        // last 13 bits from 16 bits
        int fragmentOffset = (data1 & 0x1f) << 8;
        fragmentOffset = fragmentOffset | (data2 & 0xff);
        String toDisplay = "IP:\t\tFragment offset = " + fragmentOffset + " bytes";
        System.out.println(toDisplay);
        return fragmentOffset;
    }

    /**
     * to get the time to live.
     * @param data byte value in decimal.
     * @return
     */
    public int getTTL(byte data) {
        int ttl = (data & 0xff);
        String toDisplay = "IP:\t\tTime to live = " + ttl + " seconds/hops";
        System.out.println(toDisplay);
        return data;
    }

    /**
     * to get the next level protocol used in the data portion.
     * @param data byte value in decimal.
     * @return
     */
    public String getProtocol(byte data) {
        int protocolNumber = (data & 0xff);
        String toDisplay = "Protocol = " + protocolNumber;
        String protocol = "";
        if (protocolNumber == 1) {
            toDisplay += " (ICMP)";
            protocol = "ICMP";
        } else if (protocolNumber == 6) {
            toDisplay += " (TCP)";
            protocol = "TCP";
        } else if (protocolNumber == 17) {
            toDisplay += " (UDP)";
            protocol = "UDP";
        }
        System.out.println("IP:\t\t" + toDisplay);
        return protocol;
    }

    /**
     * to get the checksum on the header.
     * @param data1 first byte in decimal.
     * @param data2 second byte in decimal.
     * @return
     */
    public String getHeaderCheckSum(byte data1, byte data2) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);

        String checkSum = hexData1 + hexData2;
        String toDisplay = "IP:\t\tHeader checksum = 0x" + checkSum;
        System.out.println(toDisplay);
        return "0x" + checkSum;
    }

    /**
     * to get the source address.
     * @param data1 first byte in decimal.
     * @param data2 second byte in decimal.
     * @param data3 third byte in decimal.
     * @param data4 fourth byte in decimal.
     * @return
     */
    public String getSourceAddress(byte data1, byte data2, byte data3, byte data4) {
        int a = (data1 & 0xFF);
        int b = (data2 & 0xFF);
        int c = (data3 & 0xFF);
        int d = (data4 & 0xFF);
        String ip = a + "." + b + "." + c + "." + d;
        String toDisplay = "IP:\t\tSource address = " + ip;
        System.out.println(toDisplay);
        return ip;
    }

    /**
     * to get the destination address.
     * @param data1 first byte in decimal.
     * @param data2 second byte in decimal.
     * @param data3 third byte in decimal.
     * @param data4 fourth byte in decimal.
     * @return
     */
    public String getDestinationAddress(byte data1, byte data2, byte data3, byte data4) {
        int a = (data1 & 0xFF);
        int b = (data2 & 0xFF);
        int c = (data3 & 0xFF);
        int d = (data4 & 0xFF);
        String ip = a + "." + b + "." + c + "." + d;
        String toDisplay = "IP:\t\tDestination address = " + ip;
        System.out.println(toDisplay);
        return ip;
    }

}
