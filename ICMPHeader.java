public class ICMPHeader {

    /**
     * to get format of the ICMP header.
     * @param data byte value in decimal.
     * @return
     */
    public int getType(byte data) {
        int type = (data & 0xff);
        String description = "";
        if (type == 0) {
            description = "Echo reply";
        } else if (type == 3) {
            description = "Destination unreachable";
        } else if (type == 4) {
            description = "Source quench";
        } else if (type == 5) {
            description = "Redirect";
        } else if (type == 6) {
            description = "Alternate host address";
        } else if (type == 8) {
            description = "Echo request";
        } else if (type == 9) {
            description = "Router advertisement";
        } else if (type == 10) {
            description = "Router Solicitation";
        } else if (type == 11) {
            description = "Time exceeded";
        } else if (type == 12) {
            description = "Parameter problem";
        } else if (type == 13) {
            description = "Timestamp request";
        } else if (type == 14) {
            description = "Timestamp reply";
        } else if (type == 15) {
            description = "Information request";
        } else if (type == 16) {
            description = "Information reply";
        } else if (type == 17) {
            description = "Address mask request";
        } else if (type == 18) {
            description = "Address mask reply";
        } else if (type >= 19 && type <=29) {
            description = "reserved";
        } else if (type == 30) {
            description = "Traceroute";
        } else if (type == 31) {
            description = "Conversion error";
        } else if (type == 32) {
            description = "Mobile host redirect";
        } else if (type == 33) {
            description = "IPv6 Where-Are-you";
        } else if (type == 34) {
            description = "IPv6 I-Am-Here";
        } else if (type == 35) {
            description = "Mobile registration request";
        } else if (type == 36) {
            description = "Mobile registration reply";
        } else if (type == 37) {
            description = "Domain name request";
        } else if (type == 38) {
            description = "Domain name reply";
        } else if (type == 39) {
            description = "SKIP Algorithm Dicovery Protocol";
        } else if (type == 40) {
            description = "Photuris, Security failures";
        } else if (type == 41) {
            description = "Experimental mobility protocol";
        }

        String toDisplay = "ICMP:\tType = " + type + "(" + description + ")";
        System.out.println(toDisplay);
        return type;
    }

    /**
     * to get the code.
     * @param data byte value in decimal.
     * @return
     */
    public int getCode(byte data) {
        int code = (data & 0xff);
        String toDisplay = "ICMP:\tCode = " + code;
        System.out.println(toDisplay);
        return data;
    }

    /**
     * to get the checksum.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public String getChecksum(byte data1, byte data2) {
        int decimalData1 = (data1 & 0xFF) << 8;
        int decimalData2 = (data2 & 0xFF);
        int checkSum = decimalData1 | decimalData2;
        String hexData = String.format("%04x", checkSum);

        String toDisplay = "ICMP:\tChecksum = 0x" + hexData;
        System.out.println(toDisplay);
        return hexData;
    }

}
