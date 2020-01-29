public class TCPHeader {

    /**
     * to get the source port.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public String getSourcePort(byte data1, byte data2) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);
        String sourcePortHex = hexData1 + "" + hexData2;
        int sourcePortDecimal = Integer.parseInt(sourcePortHex, 16);
        String toDisplay = "Source port = " + sourcePortDecimal;
        System.out.println("TCP:\t" + toDisplay);
        return "" + sourcePortDecimal;
    }

    /**
     * to get the destination port.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public String getDestinationPort(byte data1, byte data2) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);
        String destinationPortHex = hexData1 + "" + hexData2;
        int destinationPortDecimal = Integer.parseInt(destinationPortHex, 16);
        String toDisplay = "TCP:\tDestination port = " + destinationPortDecimal;
        System.out.println(toDisplay);
        return "" + destinationPortDecimal;
    }

    /**
     * to get the sequence number.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @param data3 third byte value in decimal.
     * @param data4 fourth byte value in decimal.
     * @return
     */
    public long getSequenceNumber(byte data1, byte data2, byte data3, byte data4) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);
        String hexData3 = String.format("%02x", data3);
        String hexData4 = String.format("%02x", data4);
        String sequenceHex = hexData1 + "" + hexData2 + "" + hexData3 + "" + hexData4;
        long sequenceNumberDecimal = Long.parseLong(sequenceHex, 16);
        String toDisplay = "TCP:\tSequence number = " + sequenceNumberDecimal;
        System.out.println(toDisplay);
        return sequenceNumberDecimal;
    }

    /**
     * to get the acknowledgement number.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @param data3 third byte value in decimal.
     * @param data4 fourth byte value in decimal.
     * @return
     */
    public long getAckowledgementNumber(byte data1, byte data2, byte data3, byte data4) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);
        String hexData3 = String.format("%02x", data3);
        String hexData4 = String.format("%02x", data4);
        String ackowledgementHex = hexData1 + "" + hexData2 + "" + hexData3 + "" + hexData4;
        long ackowledgementNumberDecimal = Long.parseLong(ackowledgementHex, 16);
        String toDisplay = "TCP:\tAcknowledgement number = " + ackowledgementNumberDecimal;
        System.out.println(toDisplay);
        return ackowledgementNumberDecimal;
    }

    /**
     * to get the data offset.
     * @param data byte value in decimal.
     * @return
     */
    public int getDataOffset(byte data) {
        // first four bits
        int offsetInBytes = (byte) ((data & 0xFF) >>> 4) * 4;

        String toDisplay = "TCP:\tData offset = " + offsetInBytes + " bytes";
        System.out.println(toDisplay);
        return offsetInBytes;
    }

    /**
     * to get the control bits.
     * @param data byte value in decimal.
     * @return set of flags values.
     */
    public String[] getFlags(byte data) {
        String[] toDisplay = new String[7];
        String hexData = String.format("%02x", data);
        toDisplay[0] = "TCP:\tFlags = 0x" + hexData;

        toDisplay[1] = "TCP:\t\t..";
        if (((data >> 5) & 1) != 1) {
            toDisplay[1] += "0. .... = No urgent pointer";
        } else {
            toDisplay[1] += "1. .... = Urgent pointer";
        }

        toDisplay[2] = "TCP:\t\t...";
        if (((data >> 4) & 1) != 1) {
            toDisplay[2] += "0 .... = No acknowledgement";
        } else {
            toDisplay[2] += "1 .... = Acknowledgement";
        }

        toDisplay[3] = "TCP:\t\t.... ";
        if (((data >> 3) & 1) != 1) {
            toDisplay[3] += "0... = No push";
        } else {
            toDisplay[3] += "1... = Push";
        }

        toDisplay[4] = "TCP:\t\t.... .";
        if (((data >> 2) & 1) != 1) {
            toDisplay[4] += "0.. = No reset";
        } else {
            toDisplay[4] += "1.. = Reset";
        }

        toDisplay[5] = "TCP:\t\t.... ..";
        if (((data >> 1) & 1) != 1) {
            toDisplay[5] += "0. = No syn";
        } else {
            toDisplay[5] += "1. = Syn";
        }

        toDisplay[6] = "TCP:\t\t.... ...";
        if (((data) & 1) != 1) {
            toDisplay[6] += "0 = No fin";
        } else {
            toDisplay[6] += "1 = Fin";
        }

        for (int i = 0; i < 7; i++) {
            System.out.println(toDisplay[i]);
        }

        return toDisplay;
    }

    /**
     * to get the size of the window.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public int getWindow(byte data1, byte data2) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);
        String windowHex = hexData1 + "" + hexData2;
        int windowDecimal = Integer.parseInt(windowHex, 16);

        String toDisplay = "TCP:\tWindow = " + windowDecimal;
        System.out.println(toDisplay);
        return windowDecimal;
    }

    /**
     * to get the checksum.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public String getChecksum(byte data1, byte data2) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);

        String checksumHex = hexData1 + "" + hexData2;
        String toDisplay = "TCP:\tChecksum = 0x" + checksumHex;
        System.out.println(toDisplay);
        return checksumHex;
    }

    /**
     * to get the urgent pointer.
     * @param data1 first byte value in decimal.
     * @param data2 second byte value in decimal.
     * @return
     */
    public int getUrgentPointer(byte data1, byte data2) {
        String hexData1 = String.format("%02x", data1);
        String hexData2 = String.format("%02x", data2);
        String urgentPointerHex = hexData1 + "" + hexData2;
        int urgentPointerDecimal = Integer.parseInt(urgentPointerHex, 16);

        String toDisplay = "TCP:\tUrgent pointer = " + urgentPointerDecimal;
        System.out.println(toDisplay);
        return urgentPointerDecimal;
    }
}



